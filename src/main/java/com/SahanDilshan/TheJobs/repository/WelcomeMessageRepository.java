package com.SahanDilshan.TheJobs.repository;

// WelcomeMessageRepository.java
import com.SahanDilshan.TheJobs.models.WelcomeMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WelcomeMessageRepository extends JpaRepository<WelcomeMessageEntity, Long> {
}
