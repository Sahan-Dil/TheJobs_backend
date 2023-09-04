package com.SahanDilshan.TheJobs.controllers;

// WelcomeMessageController.java
import com.SahanDilshan.TheJobs.services.WelcomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class WelcomeMessageController {

    @Autowired
    private WelcomeMessageService welcomeMessageService;

    @GetMapping("/getwelcomemsg")
    public ResponseEntity<String> getWelcomeMessage() {
        String message = welcomeMessageService.getWelcomeMessage();
        return ResponseEntity.ok(message);
    }

    @PostMapping("/createwelcomemsg")
    public ResponseEntity<String> createWelcomeMessage(@RequestBody String newMessage) {
        welcomeMessageService.setWelcomeMessage(newMessage);
        return ResponseEntity.ok("Welcome message updated successfully.");
    }
}
