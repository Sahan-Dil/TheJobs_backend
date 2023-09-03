package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.Schedule;
import com.SahanDilshan.TheJobs.models.ScheduleDTO;
import com.SahanDilshan.TheJobs.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
    }

    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setTime(scheduleDTO.getTime());
        schedule.setUserName(scheduleDTO.getUserName());
        schedule.setUserId(scheduleDTO.getUserId());
        schedule.setConsultantId(scheduleDTO.getConsultantId());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleByConsultantId(Integer consultantId) {
        return scheduleRepository.findByConsultantId(consultantId);
    }
    @Transactional
    public void deleteSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.deleteByUserIdAndConsultantIdAndDateAndTimeAndUserName(
                scheduleDTO.getUserId(),
                scheduleDTO.getConsultantId(),
                scheduleDTO.getDate(),
                scheduleDTO.getTime(),
                scheduleDTO.getUserName()
        );
    }
}
