package com.security.practisesecurityjwt.controller;

import com.security.practisesecurityjwt.dto.CustomerDto;
import com.security.practisesecurityjwt.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final CustomerService customerService;

    @PostMapping
    public String addCustomer(@RequestBody CustomerDto customerDto){
         customerService.createCustomer(customerDto);
         return "user created";
    }


}
