package com.SahanDilshan.TheJobs.controllers;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.LoginResponseDTO;
import com.SahanDilshan.TheJobs.models.RegistrationDTO;
import com.SahanDilshan.TheJobs.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }


    @Test
    void testRegisterUser() throws Exception {
        RegistrationDTO registrationDTO = new RegistrationDTO("testUser", "testPassword", "John Doe", "123456789");

        when(authenticationService.registerUser(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getPersonName(),
                registrationDTO.getPhone(),
                "USER"))
                .thenReturn(new ApplicationUser());

        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\",\"personName\":\"John Doe\",\"phone\":\"123456789\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testLoginUser() throws Exception {
        RegistrationDTO registrationDTO = new RegistrationDTO("testUser", "testPassword", "John Doe", "123456789");
        LoginResponseDTO responseDTO = new LoginResponseDTO(new ApplicationUser(), "jwtToken");

        when(authenticationService.loginUser(
                registrationDTO.getUsername(),
                registrationDTO.getPassword()))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("jwtToken"));
    }


}