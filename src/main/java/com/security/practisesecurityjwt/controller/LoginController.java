package com.security.practisesecurityjwt.controller;

import com.security.practisesecurityjwt.config.CustomerDetailService;
import com.security.practisesecurityjwt.dto.LoginDto;
import com.security.practisesecurityjwt.dto.LoginResponse;
import com.security.practisesecurityjwt.repository.CustomerRepository;
import com.security.practisesecurityjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;


    private final CustomerDetailService customerDetailsService;

    private final JwtUtil jwtUtil;

    private final CustomerRepository repository;


@PostMapping
    public ResponseEntity<LoginResponse> checkLogin(@RequestBody LoginDto loginDto) {

    try{
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));}
    catch (Exception e){
    log.info("authmanager"+authenticationManager);
    return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    //var user=repository.findByEmail(loginDto.getEmail()).get();


    UserDetails details;
    try {

        details = customerDetailsService.loadUserByUsername(loginDto.getEmail());

        log.info("details"+details);
    }catch (UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
     String token= jwtUtil.generateToken(details);
    log.info("token"+token);
        return  ResponseEntity.ok(new LoginResponse(token));

    }
}
