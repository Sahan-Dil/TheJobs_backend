package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.WelcomeMessageEntity;
import com.SahanDilshan.TheJobs.repository.WelcomeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WelcomeMessageService {

    @Autowired
    private WelcomeMessageRepository welcomeMessageRepository;

    // Step 2: Create a private static instance variable
    private static WelcomeMessageService instance;

    // Step 1: Private constructor to prevent external instantiation
    private WelcomeMessageService() {
    }

    // Step 3: Provide a public static method to access the single instance
    public static synchronized WelcomeMessageService getInstance() {
        if (instance == null) {
            instance = new WelcomeMessageService();
        }
        return instance;
    }

    public String getWelcomeMessage() {
        Optional<WelcomeMessageEntity> optionalMessage = welcomeMessageRepository.findById(1L); // Assuming message ID is 1
        return optionalMessage.map(WelcomeMessageEntity::getMessage).orElse("");
    }

    public void setWelcomeMessage(String newMessage) {
        WelcomeMessageEntity welcomeMessage = new WelcomeMessageEntity();
        welcomeMessage.setId(1L); // Assuming message ID is 1
        welcomeMessage.setMessage(newMessage);
        welcomeMessageRepository.save(welcomeMessage);
    }
}
