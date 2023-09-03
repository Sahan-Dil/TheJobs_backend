package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.Schedule;
import com.SahanDilshan.TheJobs.models.ScheduleDTO;
import com.SahanDilshan.TheJobs.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleServiceTests {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSchedule() {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate("2023-08-28");
        scheduleDTO.setTime("09:00 AM");
        scheduleDTO.setUserName("TestUser");
        scheduleDTO.setUserId(1);
        scheduleDTO.setConsultantId(2);

        Schedule expectedSchedule = new Schedule();
        expectedSchedule.setDate(scheduleDTO.getDate());
        expectedSchedule.setTime(scheduleDTO.getTime());
        expectedSchedule.setUserName(scheduleDTO.getUserName());
        expectedSchedule.setUserId(scheduleDTO.getUserId());
        expectedSchedule.setConsultantId(scheduleDTO.getConsultantId());

        when(scheduleRepository.save(any(Schedule.class))).thenReturn(expectedSchedule);

        Schedule createdSchedule = scheduleService.createSchedule(scheduleDTO);

        assertEquals(expectedSchedule.getDate(), createdSchedule.getDate());
        assertEquals(expectedSchedule.getTime(), createdSchedule.getTime());
        assertEquals(expectedSchedule.getUserName(), createdSchedule.getUserName());
        assertEquals(expectedSchedule.getUserId(), createdSchedule.getUserId());
        assertEquals(expectedSchedule.getConsultantId(), createdSchedule.getConsultantId());
    }

    @Test
    public void testGetScheduleByConsultantId() {
        Integer consultantId = 1;

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        schedules.add(new Schedule());

        when(scheduleRepository.findByConsultantId(consultantId)).thenReturn(schedules);

        List<Schedule> retrievedSchedules = scheduleService.getScheduleByConsultantId(consultantId);

        assertEquals(schedules.size(), retrievedSchedules.size());
    }

    @Test
    public void testDeleteSchedule() {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate("2023-08-28");
        scheduleDTO.setTime("09:00 AM");
        scheduleDTO.setUserName("TestUser");
        scheduleDTO.setUserId(1);
        scheduleDTO.setConsultantId(2);

        scheduleService.deleteSchedule(scheduleDTO);

        verify(scheduleRepository, times(1)).deleteByUserIdAndConsultantIdAndDateAndTimeAndUserName(
                scheduleDTO.getUserId(),
                scheduleDTO.getConsultantId(),
                scheduleDTO.getDate(),
                scheduleDTO.getTime(),
                scheduleDTO.getUserName()
        );
    }
}
