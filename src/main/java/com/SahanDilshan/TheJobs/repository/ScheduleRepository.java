package com.SahanDilshan.TheJobs.repository;

import com.SahanDilshan.TheJobs.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByConsultantId(Integer consultantId);

    void deleteByUserIdAndConsultantIdAndDateAndTimeAndUserName(
            Integer userId, Integer consultantId, String date, String time, String userName
    );
}
