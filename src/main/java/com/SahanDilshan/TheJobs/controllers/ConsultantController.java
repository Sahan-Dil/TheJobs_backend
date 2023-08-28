package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.Consultancy;
import com.SahanDilshan.TheJobs.models.ConsultancyDTO;
import com.SahanDilshan.TheJobs.services.ConsultancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultant")
@CrossOrigin("*")
public class ConsultantController {

    @Autowired
    private ConsultancyService consultancyService;

    @GetMapping("/")
    public String helloAdminController(){
        return "Consultant";
    }

    @PostMapping("/createConsultancy")
    public Consultancy createConsultancy(
            @RequestBody ConsultancyDTO consultancyDTO

    ) {
        return consultancyService.createConsultancy(consultancyDTO);
    }

    @PutMapping("/updateConsultancy/{consultancyId}")
    public Consultancy updateConsultancy(
            @PathVariable Integer consultancyId,
            @RequestBody ConsultancyDTO consultancyDTO
    ) {
        return consultancyService.updateConsultancy(consultancyId, consultancyDTO);
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

    @DeleteMapping("/deleteConsultancy/{userId}")
    public String deleteConsultancyByUserId(@PathVariable Integer userId) {
        return consultancyService.deleteConsultancyByUserId(userId);
    }


}
