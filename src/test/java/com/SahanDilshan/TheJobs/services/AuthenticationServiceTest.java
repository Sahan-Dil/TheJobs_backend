package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.LoginResponseDTO;
import com.SahanDilshan.TheJobs.models.Role;
import com.SahanDilshan.TheJobs.repository.RoleRepository;
import com.SahanDilshan.TheJobs.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

//    @Test
//    public void testRegisterUser() {
//        // Mocking role
//        Role userRole = new Role();
//        userRole.setAuthority("USER");
//        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(userRole));
//
//        // Mocking repository
//        ApplicationUser user = new ApplicationUser();
//        user.setUsername("testUser");
//        user.setPassword("encodedPassword");
//        when(userRepository.save(any())).thenReturn(user);
//
//        ApplicationUser result = authenticationService.registerUser("testUser", "password", "John", "1234567890", "USER");
//
//        // Print out actual and expected results
//        System.out.println("Actual result: " + result);
//        System.out.println("Expected username: testUser");
//
//        assertEquals("testUser", result.getUsername());
//    }

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

//    @Test
//    public void testRegisterConsultant() {
//        // Mocking role
//        Role consultantRole = new Role();
//        consultantRole.setAuthority("CONSULTANT");
//        when(roleRepository.findByAuthority("CONSULTANT")).thenReturn(Optional.of(consultantRole));
//
//        // Mocking repository
//        ApplicationUser user = new ApplicationUser();
//        user.setUsername("testUser");
//        user.setPassword("encodedPassword");
//        when(userRepository.save(any())).thenReturn(user);
//
//        ApplicationUser result = authenticationService.registerUser("testUser", "password", "John", "1234567890", "CONSULTANT");
//
//        assertEquals("testUser", result.getUsername());
//    }
}
