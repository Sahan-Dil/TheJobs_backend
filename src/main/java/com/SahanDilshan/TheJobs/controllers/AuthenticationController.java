package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.*;
import com.SahanDilshan.TheJobs.services.AuthenticationService;
import com.SahanDilshan.TheJobs.services.ConsultancyService;
import com.SahanDilshan.TheJobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConsultancyService consultancyService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(),body.getPersonName(), body.getPhone(),"USER");
    }

    @PostMapping("/login")
    public LoginResponseDTO  loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @GetMapping("/users")
    public List<UserDetailsDTO> getUsersByRole(@RequestParam("role") String role) {
        // Call the UserService to fetch users by role
        return userService.getUsersByRole(role);
    }

    @GetMapping("/getConsultancy/{userId}")
    public ResponseEntity<?> getConsultancyByUserId(@PathVariable Integer userId) {
        Consultancy consultancy = consultancyService.getConsultancyByUserId(userId);

        if (consultancy != null) {
            return ResponseEntity.ok(consultancy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
