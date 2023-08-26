package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.RegistrationDTO;
import com.SahanDilshan.TheJobs.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String helloAdminController(){
        return "Admin";
    }


    @PostMapping("/registerConsultant")
    public ApplicationUser registerConsultant(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(
                body.getUsername(),
                body.getPassword(),
                body.getPersonName(),
                body.getPhone(),
                "CONSULTANT"
        );
    }
}
