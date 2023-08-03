package com.mAadhar.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mAadhar.dto.JwtResponse;
import com.mAadhar.dto.LoginRequest;
import com.mAadhar.dto.RegisterRequest;
import com.mAadhar.dto.UpdateUserRequest;
import com.mAadhar.dto.UserResponse;
import com.mAadhar.entities.Aadhar;
import com.mAadhar.entities.User;
import com.mAadhar.services.AadharService;
import com.mAadhar.services.CustomUserDetailsService;
import com.mAadhar.utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;
    private final AadharService aadharService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(CustomUserDetailsService customUserDetailsService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil, AadharService aadharService) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.aadharService = aadharService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest jwtRequest, HttpServletResponse response)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getMobile(), jwtRequest.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User account is disabled");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getMobile());
        final String token = jwtUtil.generateToken(userDetails);

        Cookie jwtCookie = new Cookie("authorization", token);
        jwtCookie.setMaxAge(86400000);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        JwtResponse myresponse = new JwtResponse();
        User user = customUserDetailsService.findOne(jwtRequest.getMobile());
        UserResponse userResponse = new UserResponse(user.getCitizenId(), user.getName(), user.getEmail(),
                user.getMobile(), user.getDob(), user.getAddress(), user.getGender(), user.getRole(), user.getDp());
        myresponse.setSuccess(true);
        myresponse.setUser(userResponse);
        myresponse.setToken(token);
        return ResponseEntity.ok(myresponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request, HttpServletResponse response)
            throws Exception {
        User isUser = this.customUserDetailsService.findOne(request.getMobile());
        if (isUser != null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("This email is already taken");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }
        User newuser = new User();
        newuser.setName(request.getName());
        newuser.setEmail(request.getEmail());
        newuser.setMobile(request.getMobile());
        newuser.setAddress(request.getAddress());
        newuser.setDob(request.getDob());
        newuser.setGender(request.getGender());
        newuser.setPassword(passwordEncoder.encode(request.getPassword()));
        newuser.setRole("user");
        User user = this.customUserDetailsService.createUser(newuser);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getMobile(), request.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User account is disabled");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(user.getMobile());
        String token = this.jwtUtil.generateToken(userDetails);

        Cookie jwtCookie = new Cookie("authorization", token);
        jwtCookie.setMaxAge(86400000);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        JwtResponse myresponse = new JwtResponse();
        UserResponse userResponse = new UserResponse(user.getCitizenId(), user.getName(), user.getEmail(),
                user.getMobile(), user.getDob(), user.getAddress(), user.getGender(), user.getRole(), user.getDp());
        myresponse.setSuccess(true);
        myresponse.setUser(userResponse);
        myresponse.setToken(token);
        return ResponseEntity.ok(myresponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<JwtResponse> profile(HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);
        JwtResponse myresponse = new JwtResponse();
        UserResponse userResponse = new UserResponse(user.getCitizenId(), user.getName(), user.getEmail(),
                user.getMobile(), user.getDob(), user.getAddress(), user.getGender(), user.getRole(), user.getDp());
        myresponse.setSuccess(true);
        myresponse.setUser(userResponse);
        return ResponseEntity.ok(myresponse);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateUserRequest request, HttpServletResponse response)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);
        user.setAddress(request.getAddress());
        user.setMobile(request.getMobile());
        user.setDob(request.getDob());

        user = this.customUserDetailsService.update(user);

        Aadhar aadhar = this.aadharService.findAadharByCitizenId(user.getCitizenId());

        aadhar.setStatus("applied");
        this.aadharService.saveAadhar(aadhar);
        JwtResponse myresponse = new JwtResponse();
        UserResponse userResponse = new UserResponse(user.getCitizenId(), user.getName(), user.getEmail(),
                user.getMobile(), user.getDob(), user.getAddress(), user.getGender(), user.getRole(), user.getDp());
        myresponse.setSuccess(true);
        myresponse.setUser(userResponse);
        return ResponseEntity.ok(myresponse);
    }

    @PutMapping("/upload")
    public ResponseEntity<?> uploadProfilePicture(@RequestParam("dp") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);
        String rootDirectory = System.getProperty("user.dir");
        Path uploadsDir = Paths.get(rootDirectory, "uploads");
        if (!Files.exists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }
        
        String originalFileName = file.getOriginalFilename();
        String fileName = user.getCitizenId() + "_" + System.currentTimeMillis() + "_" + originalFileName;
        
        Path destFile = uploadsDir.resolve(fileName);
        file.transferTo(destFile.toFile());
        String fileUrl = "http://localhost:9000/uploads/" + fileName;
        user.setDp(fileUrl);
        user = this.customUserDetailsService.update(user);

        JwtResponse myresponse = new JwtResponse();
        UserResponse userResponse = new UserResponse(user.getCitizenId(), user.getName(), user.getEmail(),
                user.getMobile(), user.getDob(), user.getAddress(), user.getGender(), user.getRole(), user.getDp());
        myresponse.setSuccess(true);
        myresponse.setUser(userResponse);
        return ResponseEntity.ok(myresponse);
    }
}
