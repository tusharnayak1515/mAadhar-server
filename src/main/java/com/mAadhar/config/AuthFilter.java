package com.mAadhar.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mAadhar.services.CustomUserDetailsService;
import com.mAadhar.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    	Cookie[] jwtCookies = request.getCookies();
    	String token = null;
        if(jwtCookies != null) {
            for(Cookie cookie:jwtCookies) {
                 if (cookie.getName().equals("authorization")) {
//                     cookie.setValue(null);
//                     cookie.setMaxAge(0);
//                     cookie.setPath("/");
//                     response.addCookie(cookie);
                     token = cookie.getValue();		 
                    //  System.out.println(token);
                 }
            }
        }
        String mobile = null;

        if(token != null) {
            try {
                mobile = this.jwtUtil.extractUsername(token);
                // System.out.println("mobile: "+mobile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(mobile != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(mobile);
                if(jwtUtil.validateToken(token, userDetails)) {
                	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);                	
                }
            }
        }
        else {
            System.out.println("Invalid Token");
        }

        filterChain.doFilter(request, response);
        
    }
    
}
