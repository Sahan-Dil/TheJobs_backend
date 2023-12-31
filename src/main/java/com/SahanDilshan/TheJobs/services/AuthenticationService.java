package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.LoginResponseDTO;
import com.SahanDilshan.TheJobs.models.Role;
import com.SahanDilshan.TheJobs.repository.RoleRepository;
import com.SahanDilshan.TheJobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser registerUser(String username,String password,String personName, String phone, String roleName){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole;
        if ("CONSULTANT".equals(roleName)) {
            userRole = roleRepository.findByAuthority("CONSULTANT")
                    .orElseThrow(() -> new RuntimeException("Consultant role not found"));
        } else {
            userRole = roleRepository.findByAuthority("USER")
                    .orElseThrow(() -> new RuntimeException("User role not found"));
        }

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new ApplicationUser(0,username,encodedPassword,personName,phone,authorities));
    }

//    public ApplicationUser registerConsultantUser(String username,String password,String personName, String phone){
//        String encodedPassword = passwordEncoder.encode(password);
//        Role userRole = roleRepository.findByAuthority("CONSULTANT").get();
//
//        Set<Role> authorities = new HashSet<>();
//        authorities.add(userRole);
//        return userRepository.save(new ApplicationUser(0,username,encodedPassword,personName,phone,authorities));
//    }

   public LoginResponseDTO loginUser(String username, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        }catch (AuthenticationException e){
            return new LoginResponseDTO(null,"");
        }
   }

}
