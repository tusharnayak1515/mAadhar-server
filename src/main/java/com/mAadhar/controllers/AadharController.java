package com.mAadhar.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mAadhar.dto.AadharList;
import com.mAadhar.dto.AadharRequest;
import com.mAadhar.dto.AadharResponse;
import com.mAadhar.dto.AadharUser;
import com.mAadhar.dto.ApproveRequest;
import com.mAadhar.dto.JwtResponse;
import com.mAadhar.entities.Aadhar;
import com.mAadhar.entities.User;
import com.mAadhar.services.AadharService;
import com.mAadhar.services.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/aadhar")
public class AadharController {

    private final AadharService aadharService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AadharController(AadharService aadharService, CustomUserDetailsService customUserDetailsService) {
        this.aadharService = aadharService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAadhars(HttpServletRequest request)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        if (!user.getRole().equalsIgnoreCase("admin")) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        List<Aadhar> aadharList = this.aadharService.findAll();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar aadhar : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(aadhar.getUser().getCitizenId());
            aadharUser.setName(aadhar.getUser().getName());
            aadharUser.setEmail(aadhar.getUser().getEmail());
            aadharUser.setMobile(aadhar.getUser().getMobile());
            aadharUser.setAddress(aadhar.getUser().getAddress());
            aadharUser.setDob(aadhar.getUser().getDob());
            aadharUser.setGender(aadhar.getUser().getGender());
            aadharUser.setDp(aadhar.getUser().getDp());
            aadharUser.setId(aadhar.getId());
            aadharUser.setAadharNumber(aadhar.getAadharNumber());
            aadharUser.setDuplicates(aadhar.getDuplicates());
            aadharUser.setStatus(aadhar.getStatus());
            aadharUser.setIssueDate(aadhar.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/applied")
    public ResponseEntity<?> getAadharRequests(HttpServletRequest request)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        if (!user.getRole().equalsIgnoreCase("admin")) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        List<Aadhar> aadharList = this.aadharService.getAllAppliedAadharCardRequests();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar aadhar : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(aadhar.getUser().getCitizenId());
            aadharUser.setName(aadhar.getUser().getName());
            aadharUser.setEmail(aadhar.getUser().getEmail());
            aadharUser.setMobile(aadhar.getUser().getMobile());
            aadharUser.setAddress(aadhar.getUser().getAddress());
            aadharUser.setDob(aadhar.getUser().getDob());
            aadharUser.setGender(aadhar.getUser().getGender());
            aadharUser.setDp(aadhar.getUser().getDp());
            aadharUser.setId(aadhar.getId());
            aadharUser.setAadharNumber(aadhar.getAadharNumber());
            aadharUser.setDuplicates(aadhar.getDuplicates());
            aadharUser.setStatus(aadhar.getStatus());
            aadharUser.setIssueDate(aadhar.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/duplicate")
    public ResponseEntity<?> getDuplicateAadharRequests(HttpServletRequest request)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        if (!user.getRole().equalsIgnoreCase("admin")) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        List<Aadhar> aadharList = this.aadharService.getAllDuplicateAadharCardRequests();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar aadhar : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(aadhar.getUser().getCitizenId());
            aadharUser.setName(aadhar.getUser().getName());
            aadharUser.setEmail(aadhar.getUser().getEmail());
            aadharUser.setMobile(aadhar.getUser().getMobile());
            aadharUser.setAddress(aadhar.getUser().getAddress());
            aadharUser.setDob(aadhar.getUser().getDob());
            aadharUser.setGender(aadhar.getUser().getGender());
            aadharUser.setDp(aadhar.getUser().getDp());
            aadharUser.setId(aadhar.getId());
            aadharUser.setAadharNumber(aadhar.getAadharNumber());
            aadharUser.setDuplicates(aadhar.getDuplicates());
            aadharUser.setStatus(aadhar.getStatus());
            aadharUser.setIssueDate(aadhar.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    @PutMapping("/approve")
    public ResponseEntity<?> approveRequest(@RequestBody ApproveRequest jwtRequest, HttpServletResponse response)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        if (!user.getRole().equalsIgnoreCase("admin")) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        Aadhar aadhar = this.aadharService.findById(jwtRequest.getAadharId());
        if (aadhar == null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Aadhar card does not exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        aadhar.setStatus("approved");

        this.aadharService.saveAadhar(aadhar);

        List<Aadhar> aadharList = this.aadharService.getAllAppliedAadharCardRequests();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar item : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(aadhar.getUser().getCitizenId());
            aadharUser.setName(aadhar.getUser().getName());
            aadharUser.setEmail(aadhar.getUser().getEmail());
            aadharUser.setMobile(aadhar.getUser().getMobile());
            aadharUser.setAddress(aadhar.getUser().getAddress());
            aadharUser.setDob(aadhar.getUser().getDob());
            aadharUser.setGender(aadhar.getUser().getGender());
            aadharUser.setDp(aadhar.getUser().getDp());
            aadharUser.setId(item.getId());
            aadharUser.setAadharNumber(item.getAadharNumber());
            aadharUser.setDuplicates(item.getDuplicates());
            aadharUser.setStatus(item.getStatus());
            aadharUser.setIssueDate(item.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    @PutMapping("/approve-duplicate")
    public ResponseEntity<?> approveDuplicateRequest(@RequestBody ApproveRequest jwtRequest,
            HttpServletResponse response)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        if (!user.getRole().equalsIgnoreCase("admin")) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        Aadhar aadhar = this.aadharService.findById(jwtRequest.getAadharId());
        if (aadhar == null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Aadhar card does not exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        aadhar.setDuplicates(aadhar.getDuplicates() + 1);
        aadhar.setStatus("approved");

        this.aadharService.saveAadhar(aadhar);

        List<Aadhar> aadharList = this.aadharService.getAllDuplicateAadharCardRequests();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar item : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(aadhar.getUser().getCitizenId());
            aadharUser.setName(aadhar.getUser().getName());
            aadharUser.setEmail(aadhar.getUser().getEmail());
            aadharUser.setMobile(aadhar.getUser().getMobile());
            aadharUser.setAddress(aadhar.getUser().getAddress());
            aadharUser.setDob(aadhar.getUser().getDob());
            aadharUser.setGender(aadhar.getUser().getGender());
            aadharUser.setDp(aadhar.getUser().getDp());
            aadharUser.setId(item.getId());
            aadharUser.setAadharNumber(item.getAadharNumber());
            aadharUser.setDuplicates(item.getDuplicates());
            aadharUser.setStatus(item.getStatus());
            aadharUser.setIssueDate(item.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/myaadhar")
    public ResponseEntity<?> getMyAadharCard(HttpServletRequest request)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        Aadhar aadhar = this.aadharService.findAadharByCitizenId(user.getCitizenId());
        if (aadhar == null) {
            AadharResponse myresponse = new AadharResponse();
            myresponse.setSuccess(true);
            myresponse.setError(null);
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(user.getCitizenId());
            aadharUser.setName(user.getName());
            aadharUser.setEmail(user.getEmail());
            aadharUser.setMobile(user.getMobile());
            aadharUser.setAddress(user.getAddress());
            aadharUser.setDob(user.getDob());
            aadharUser.setGender(user.getGender());
            aadharUser.setDp(user.getDp());
            myresponse.setAadhar(aadharUser);
            return ResponseEntity.ok(myresponse);
        }
        else {
            AadharResponse myresponse = new AadharResponse();
            myresponse.setSuccess(true);
            myresponse.setError(null);
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(user.getCitizenId());
            aadharUser.setName(user.getName());
            aadharUser.setEmail(user.getEmail());
            aadharUser.setMobile(user.getMobile());
            aadharUser.setAddress(user.getAddress());
            aadharUser.setDob(user.getDob());
            aadharUser.setGender(user.getGender());
            aadharUser.setDp(user.getDp());
            aadharUser.setId(aadhar.getId());
            aadharUser.setAadharNumber(aadhar.getAadharNumber());
            aadharUser.setDuplicates(aadhar.getDuplicates());
            aadharUser.setStatus(aadhar.getStatus());
            aadharUser.setIssueDate(aadhar.getIssueDate());
            myresponse.setAadhar(aadharUser);
            return ResponseEntity.ok(myresponse);
        }
    }

    @PostMapping("/myaadhar")
    public ResponseEntity<?> applyAadharCard(@RequestBody AadharRequest jwtRequest, HttpServletResponse response)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        Aadhar isAadhar = this.aadharService.findAadharByCitizenId(user.getCitizenId());
        if (isAadhar != null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("One person can have only one aadhar. If lost, apply for a duplicate one.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        String aadharNumber = generateRandomAadharNumber();

        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(aadharNumber);
        aadhar.setUser(user);
        aadhar.setDuplicates(0);
        aadhar.setStatus("applied");
        aadhar.setIssueDate(jwtRequest.getIssueDate());

        this.aadharService.saveAadhar(aadhar);

        AadharResponse myresponse = new AadharResponse();
        myresponse.setSuccess(true);
        myresponse.setError(null);
        AadharUser aadharUser = new AadharUser();
        aadharUser.setCitizenId(user.getCitizenId());
        aadharUser.setName(user.getName());
        aadharUser.setEmail(user.getEmail());
        aadharUser.setMobile(user.getMobile());
        aadharUser.setAddress(user.getAddress());
        aadharUser.setDob(user.getDob());
        aadharUser.setGender(user.getGender());
        aadharUser.setDp(user.getDp());
        aadharUser.setId(aadhar.getId());
        aadharUser.setAadharNumber(aadhar.getAadharNumber());
        aadharUser.setDuplicates(aadhar.getDuplicates());
        aadharUser.setStatus(aadhar.getStatus());
        aadharUser.setIssueDate(aadhar.getIssueDate());
        myresponse.setAadhar(aadharUser);
        return ResponseEntity.ok(myresponse);
    }

    @PutMapping("/myaadhar")
    public ResponseEntity<?> applyDuplicateAadharCard(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.customUserDetailsService.findOne(email);

        Aadhar aadhar = this.aadharService.findAadharByCitizenId(user.getCitizenId());
        if (aadhar == null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Aadhar card does not exist. Apply for a new one.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        aadhar.setStatus("duplicate");
        this.aadharService.saveAadhar(aadhar);

        AadharResponse myresponse = new AadharResponse();
        myresponse.setSuccess(true);
        myresponse.setError(null);
        AadharUser aadharUser = new AadharUser();
        aadharUser.setCitizenId(user.getCitizenId());
        aadharUser.setName(user.getName());
        aadharUser.setEmail(user.getEmail());
        aadharUser.setMobile(user.getMobile());
        aadharUser.setAddress(user.getAddress());
        aadharUser.setDob(user.getDob());
        aadharUser.setGender(user.getGender());
        aadharUser.setDp(user.getDp());
        aadharUser.setId(aadhar.getId());
        aadharUser.setAadharNumber(aadhar.getAadharNumber());
        aadharUser.setDuplicates(aadhar.getDuplicates());
        aadharUser.setStatus(aadhar.getStatus());
        aadharUser.setIssueDate(aadhar.getIssueDate());
        myresponse.setAadhar(aadharUser);
        return ResponseEntity.ok(myresponse);
    }

    @DeleteMapping("/{aadharId}")
    public ResponseEntity<?> deleteAadhar(@PathVariable Long aadharId) {
        Aadhar aadhar = aadharService.findById(aadharId);
        if (aadhar == null) {
            JwtResponse myResponse = new JwtResponse();
            myResponse.setSuccess(false);
            myResponse.setError("Aadhar card does not exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
        }

        this.aadharService.deleteAadhar(aadharId);
        List<Aadhar> aadharList = this.aadharService.findAll();

        AadharList list = new AadharList();

        list.setSuccess(true);
        list.setError(null);
        for (Aadhar item : aadharList) {
            AadharUser aadharUser = new AadharUser();
            aadharUser.setCitizenId(item.getUser().getCitizenId());
            aadharUser.setName(item.getUser().getName());
            aadharUser.setEmail(item.getUser().getEmail());
            aadharUser.setMobile(item.getUser().getMobile());
            aadharUser.setAddress(item.getUser().getAddress());
            aadharUser.setDob(item.getUser().getDob());
            aadharUser.setGender(item.getUser().getGender());
            aadharUser.setDp(item.getUser().getDp());
            aadharUser.setId(item.getId());
            aadharUser.setAadharNumber(item.getAadharNumber());
            aadharUser.setDuplicates(item.getDuplicates());
            aadharUser.setStatus(item.getStatus());
            aadharUser.setIssueDate(item.getIssueDate());
            list.addAadhar(aadharUser);
        }

        return ResponseEntity.ok(list);
    }

    public static String generateRandomAadharNumber() {
        Random random = new Random();
        StringBuilder aadharNumberBuilder = new StringBuilder("1");

        for (int i = 0; i < 11; i++) {
            int digit = random.nextInt(10);
            aadharNumberBuilder.append(digit);
        }

        return aadharNumberBuilder.toString();
    }

}
