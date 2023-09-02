package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.Role;
import com.SahanDilshan.TheJobs.models.UserDetailsDTO;
import com.SahanDilshan.TheJobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in the User Service");

        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Not Found"));

    }

    public List<UserDetailsDTO> getUsersByRole(String roleName) {
        // Fetch users by their role using the custom query method in UserRepository
        List<ApplicationUser> users = userRepository.findByAuthoritiesAuthority(roleName);

        // Map the ApplicationUser objects to UserDetailsDTO objects
        List<UserDetailsDTO> userDetailsList = users.stream()
                .map(user -> {
                    UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
                    userDetailsDTO.setUserId(user.getUserId());
                    userDetailsDTO.setUsername(user.getUsername());
                    userDetailsDTO.setPersonName(user.getPersonName());
                    userDetailsDTO.setPhone(user.getPhone());
                    userDetailsDTO.setAuthorities(user.getAuthorities());
                    return userDetailsDTO;
                })
                .collect(Collectors.toList());

        return userDetailsList;
    }
}
