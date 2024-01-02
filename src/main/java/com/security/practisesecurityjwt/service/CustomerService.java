package com.security.practisesecurityjwt.service;

import com.security.practisesecurityjwt.dto.CustomerDto;
import com.security.practisesecurityjwt.entity.Customer;
import com.security.practisesecurityjwt.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;


    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer=new Customer();
       //BeanUtils.copyProperties(customerDto,customer);
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        return customerRepository.save(customer);

    }


}
