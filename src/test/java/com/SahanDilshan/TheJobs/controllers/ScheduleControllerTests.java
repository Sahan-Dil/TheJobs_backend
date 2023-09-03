package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.Schedule;
import com.SahanDilshan.TheJobs.models.ScheduleDTO;
import com.SahanDilshan.TheJobs.services.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private ScheduleController scheduleController;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    public void testCreateSchedule() throws Exception {
        // Create a ScheduleDTO object with test data
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate("2023-08-28");
        scheduleDTO.setTime("09:00 AM");
        scheduleDTO.setUserName("TestUser");
        scheduleDTO.setUserId(1);
        scheduleDTO.setConsultantId(2);

        // Mock the behavior of the service method
        Schedule createdSchedule = new Schedule();
        createdSchedule.setDate("2023-08-28");
        createdSchedule.setTime("09:00 AM");
        createdSchedule.setUserName("TestUser");
        createdSchedule.setUserId(1);
        createdSchedule.setConsultantId(2);
        when(scheduleService.createSchedule(any(ScheduleDTO.class))).thenReturn(createdSchedule);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/schedule/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-08-28\",\"time\":\"09:00 AM\",\"userName\":\"TestUser\",\"userId\":1,\"consultantId\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2023-08-28"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.time").value("09:00 AM"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("TestUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consultantId").value(2));
    }

    @Test
    public void testGetScheduleByConsultantId() throws Exception {
        // Create a list of Schedule objects as the expected result
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        schedules.add(new Schedule());

        // Mock the behavior of the service method
        when(scheduleService.getScheduleByConsultantId(1)).thenReturn(schedules);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/getByConsultant/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeleteSchedule() throws Exception {
        // Create a ScheduleDTO object with test data
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate("2023-08-28");
        scheduleDTO.setTime("09:00 AM");
        scheduleDTO.setUserName("TestUser");
        scheduleDTO.setUserId(1);
        scheduleDTO.setConsultantId(2);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-08-28\",\"time\":\"09:00 AM\",\"userName\":\"TestUser\",\"userId\":1,\"consultantId\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the service method was called
        verify(scheduleService, times(1)).deleteSchedule(ArgumentMatchers.any(ScheduleDTO.class));    }
}
