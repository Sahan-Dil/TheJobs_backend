package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.LoginResponseDTO;
import com.SahanDilshan.TheJobs.models.RegistrationDTO;
import com.SahanDilshan.TheJobs.models.Role;
import com.SahanDilshan.TheJobs.repository.RoleRepository;
import com.SahanDilshan.TheJobs.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // Create a mock RegistrationDTO
        RegistrationDTO registrationDTO = new RegistrationDTO("testUser", "testPassword", "John Doe", "123456789");

        // Mock the role repository
        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(new Role("USER")));

        // Mock password encoding
        when(passwordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");

        // Mock the user repository
        when(userRepository.save(any(ApplicationUser.class)))
                .thenReturn(new ApplicationUser(1, "testUser", "encodedPassword", "John Doe", "123456789", Collections.emptySet()));

        ApplicationUser result = authenticationService.registerUser(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getPersonName(),
                registrationDTO.getPhone(),
                "USER"
        );

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());

    }
    @Test
    public void testLoginUser() {
        // Mocking authentication
        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        // Mocking token service
        when(tokenService.generateJwt(any())).thenReturn("testJwt");

        // Mocking repository
        ApplicationUser user = new ApplicationUser();
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        LoginResponseDTO response = authenticationService.loginUser("testUser", "password");

        assertEquals(user, response.getUser());
        assertEquals("testJwt", response.getJwt());
    }

    @Test
    public void testRegisterConsultant() {
        // Create a mock RegistrationDTO
        RegistrationDTO registrationDTO = new RegistrationDTO("testCONSULTANT", "testPassword", "John Doe", "123456789");

        // Mock the role repository
        when(roleRepository.findByAuthority("CONSULTANT")).thenReturn(Optional.of(new Role("CONSULTANT")));

        // Mock password encoding
        when(passwordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");

        // Mock the user repository
        when(userRepository.save(any(ApplicationUser.class)))
                .thenReturn(new ApplicationUser(1, "testCONSULTANT", "encodedPassword", "John Doe", "123456789", Collections.emptySet()));

        ApplicationUser result = authenticationService.registerUser(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getPersonName(),
                registrationDTO.getPhone(),
                "CONSULTANT"
        );

        assertNotNull(result);
        assertEquals("testCONSULTANT", result.getUsername());

    }
}