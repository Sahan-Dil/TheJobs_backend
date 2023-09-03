package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.Schedule;
import com.SahanDilshan.TheJobs.models.ScheduleDTO;
import com.SahanDilshan.TheJobs.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create")
    public Schedule createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.createSchedule(scheduleDTO);
    }

    @GetMapping("/getByConsultant/{consultantId}")
    public List<Schedule> getScheduleByConsultantId(@PathVariable Integer consultantId) {
        return scheduleService.getScheduleByConsultantId(consultantId);
    }

    @DeleteMapping("/delete")
    public void deleteSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.deleteSchedule(scheduleDTO);
    }
}
