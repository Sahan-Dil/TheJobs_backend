package com.SahanDilshan.TheJobs.services;

// WelcomeMessageService.java
import com.SahanDilshan.TheJobs.models.WelcomeMessageEntity;
import com.SahanDilshan.TheJobs.repository.WelcomeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WelcomeMessageService {

    @Autowired
    private WelcomeMessageRepository welcomeMessageRepository;

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
