package com.store.bookstore.service.impl;

import com.store.bookstore.dto.CustomerDTO;
import com.store.bookstore.entities.Customer;
import com.store.bookstore.repository.CustomerRepository;
import com.store.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.email() == null || customerDTO.email().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (customerRepository.existsByEmail(customerDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        // TODO: validate password size

        Customer customer = new Customer(
                customerDTO.email(),
                customerDTO.firstName(),
                customerDTO.lastName(),
                passwordEncoder.encode(customerDTO.password())
        );

        Customer createdCustomer = customerRepository.save(customer);

        return new CustomerDTO(
                createdCustomer.getId(),
                createdCustomer.getEmail(),
                createdCustomer.getFirstName(),
                createdCustomer.getLastName(),
                null
        );
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                null
        )).toList();
    }
}
