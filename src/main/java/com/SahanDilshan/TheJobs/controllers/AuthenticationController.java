package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.LoginResponseDTO;
import com.SahanDilshan.TheJobs.models.RegistrationDTO;
import com.SahanDilshan.TheJobs.models.UserDetailsDTO;
import com.SahanDilshan.TheJobs.services.AuthenticationService;
import com.SahanDilshan.TheJobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
