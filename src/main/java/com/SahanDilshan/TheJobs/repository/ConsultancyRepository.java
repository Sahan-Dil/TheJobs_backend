package com.SahanDilshan.TheJobs.repository;

import com.SahanDilshan.TheJobs.models.Consultancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConsultancyRepository extends JpaRepository<Consultancy, Integer> {
    Consultancy findByUserId(Integer userId);
    @Transactional
    @Modifying
    @Query("DELETE FROM Consultancy c WHERE c.userId = :userId")
    void deleteByUserId(Integer userId);
}
