package com.SahanDilshan.TheJobs.services;
import com.SahanDilshan.TheJobs.models.Consultancy;
import com.SahanDilshan.TheJobs.models.ConsultancyDTO;
import com.SahanDilshan.TheJobs.repository.ConsultancyRepository;
import com.SahanDilshan.TheJobs.services.ConsultancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConsultancyServiceTest {
    @InjectMocks
    private ConsultancyService consultancyService;

    @Mock
    private ConsultancyRepository consultancyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateConsultancy() {
        ConsultancyDTO consultancyDTO = new ConsultancyDTO();
        consultancyDTO.setName("Consultancy Name");
        // Set other properties as needed

        // Mock behavior for the repository
        when(consultancyRepository.existsById(consultancyDTO.getUserId())).thenReturn(false);

        // Mock the saved entity
        Consultancy savedConsultancy = new Consultancy();
        savedConsultancy.setName(consultancyDTO.getName());
        // Set other properties as needed
        when(consultancyRepository.save(any(Consultancy.class))).thenReturn(savedConsultancy);

        Consultancy createdConsultancy = consultancyService.createConsultancy(consultancyDTO);

        assertEquals("Consultancy Name", createdConsultancy.getName());
        // Add more assertions for other properties if needed
    }

    @Test
    public void testUpdateConsultancy() {
        // Create a ConsultancyDTO with updated information
        ConsultancyDTO updatedConsultancyDTO = new ConsultancyDTO();
        updatedConsultancyDTO.setName("Updated Consultancy Name");
        // Set other properties as needed

        // Mock behavior for the repository to find an existing consultancy
        Consultancy existingConsultancy = new Consultancy();
        existingConsultancy.setConsultancyId(1); // Set the ID of the existing Consultancy
        // Set other properties of the existing Consultancy as needed
        when(consultancyRepository.findById(existingConsultancy.getConsultancyId())).thenReturn(Optional.of(existingConsultancy));

        // Mock behavior for the repository to save the updated Consultancy
        when(consultancyRepository.save(any(Consultancy.class))).thenAnswer(invocation -> {
            Consultancy savedConsultancy = invocation.getArgument(0);
            // Simulate saving the updated Consultancy and return it
            return savedConsultancy;
        });

        Consultancy updatedConsultancy = consultancyService.updateConsultancy(existingConsultancy.getConsultancyId(), updatedConsultancyDTO);

        // Verify that the Consultancy was updated with the new information
        assertEquals("Updated Consultancy Name", updatedConsultancy.getName());
        // Add more assertions for other properties if needed
    }

    @Test
    public void testGetConsultancyByUserId() {
        Integer userId = 1; // Replace with the desired user ID for testing

        // Create a mock Consultancy entity
        Consultancy mockConsultancy = new Consultancy();
        mockConsultancy.setUserId(userId);
        mockConsultancy.setName("Consultancy Name");
        // Set other properties as needed

        // Mock the behavior of the repository
        when(consultancyRepository.findByUserId(userId)).thenReturn(mockConsultancy);

        // Call the service method
        Consultancy retrievedConsultancy = consultancyService.getConsultancyByUserId(userId);

        // Verify that the service returned the expected Consultancy
        assertNotNull(retrievedConsultancy);
        assertEquals(userId, retrievedConsultancy.getUserId());
        assertEquals("Consultancy Name", retrievedConsultancy.getName());
        // Add more assertions for other properties if needed
    }

    @Test
    public void testDeleteConsultancyByUserId_NotFound() {
        // Define a user ID for a non-existing consultancy
        Integer userId = 1;

        // Mock behavior for the repository to not find the consultancy (returns null)
        when(consultancyRepository.findByUserId(userId)).thenReturn(null);

        // Perform the delete operation
        String result = consultancyService.deleteConsultancyByUserId(userId);

        // Verify that the delete operation does not delete anything
        verify(consultancyRepository, never()).delete(any(Consultancy.class));
        assertEquals("Not found", result);
    }
}
