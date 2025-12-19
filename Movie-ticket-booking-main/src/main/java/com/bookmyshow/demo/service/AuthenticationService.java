package com.bookmyshow.demo.service;

import com.bookmyshow.demo.dtos.LoginRequestDTO;
import com.bookmyshow.demo.dtos.LoginResponseDTO;
import com.bookmyshow.demo.dtos.RegisterRequestDTO;
import com.bookmyshow.demo.entities.User;
import com.bookmyshow.demo.repo.UserRepo;
import com.bookmyshow.demo.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO){
        if(userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already present");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode((registerRequestDTO.getPassword())));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO){
        if(userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already present");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode((registerRequestDTO.getPassword())));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        User user = userRepo.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()->new RuntimeException("user not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        String token = jwtService.generateToken(user);

        return  LoginResponseDTO.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
