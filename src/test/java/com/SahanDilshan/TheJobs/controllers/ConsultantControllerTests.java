package com.SahanDilshan.TheJobs.controllers;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.SahanDilshan.TheJobs.controllers.ConsultantController;
import com.SahanDilshan.TheJobs.models.Consultancy;
import com.SahanDilshan.TheJobs.models.ConsultancyDTO;
import com.SahanDilshan.TheJobs.services.ConsultancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsultantControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private ConsultantController consultantController;

    @Mock
    private ConsultancyService consultancyService;

    @Autowired
    private ObjectMapper objectMapper; // Used for JSON conversion


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(consultantController).build();
    }

    @Test
    public void testCreateConsultancy() throws Exception {
        // Create a ConsultancyDTO object with test data
        ConsultancyDTO consultancyDTO = new ConsultancyDTO();
        consultancyDTO.setName("Test Consultancy");
        consultancyDTO.setCountry("USA");
        // Add other properties as needed

        // Mock the behavior of the service method
        Consultancy createdConsultancy = new Consultancy();
        createdConsultancy.setName("Test Consultancy");
        createdConsultancy.setCountry("USA");
        Mockito.when(consultancyService.createConsultancy(Mockito.any(ConsultancyDTO.class))).thenReturn(createdConsultancy);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/consultant/createConsultancy").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(consultancyDTO))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Consultancy")).andExpect(MockMvcResultMatchers.jsonPath("$.country").value("USA"));

    }

    @Test
    public void testUpdateConsultancy() throws Exception {
        // Create a ConsultancyDTO object with test data for updating
        ConsultancyDTO updateDTO = new ConsultancyDTO();
        updateDTO.setName("Updated Consultancy");
        updateDTO.setCountry("Updated Country");
        // Add other properties as needed

        // Mock the behavior of the service method for updating
        Consultancy existingConsultancy = new Consultancy();
        existingConsultancy.setConsultancyId(1); // Set the ID of the existing Consultancy
        existingConsultancy.setName("Updated Consultancy");
        existingConsultancy.setCountry("Updated Country");

        // Set other properties as needed for the existing Consultancy
        Mockito.when(consultancyService.updateConsultancy(Mockito.eq(1), Mockito.any(ConsultancyDTO.class))).thenReturn(existingConsultancy);

        // Perform the PUT request to update the Consultancy
        mockMvc.perform(MockMvcRequestBuilders.put("/consultant/updateConsultancy/1") // Use the desired ID
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updateDTO))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Consultancy")).andExpect(MockMvcResultMatchers.jsonPath("$.country").value("Updated Country")); // Check updated fields
    }

    @Test
    public void testDeleteConsultancy() throws Exception {
        // Mock the behavior of the service method for deleting
        Mockito.when(consultancyService.deleteConsultancyByUserId(Mockito.eq(1))).thenReturn("Deleted");

        // Perform the DELETE request to delete the Consultancy
        mockMvc.perform(MockMvcRequestBuilders.delete("/consultant/deleteConsultancy/1")) // Use the desired userId
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted")); // Check the response content
    }

    @Test
    public void testGetConsultancy() throws Exception {
        // Create a mock Consultancy object to return as a response
        Consultancy mockConsultancy = new Consultancy();
        mockConsultancy.setConsultancyId(1);
        mockConsultancy.setName("Mocked Consultancy");
        // Set other properties as needed

        // Mock the behavior of the service method for getting Consultancy by userId
        Mockito.when(consultancyService.getConsultancyByUserId(Mockito.eq(1))).thenReturn(mockConsultancy);

        // Perform the GET request to retrieve the Consultancy
        mockMvc.perform(MockMvcRequestBuilders.get("/consultant/getConsultancy/1")) // Use the desired userId
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.consultancyId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mocked Consultancy"));
        // Check other expected properties using jsonPath

    }

}
