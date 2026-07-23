package com.store.bookstore.service.impl;

import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerAuthDTO;
import com.store.bookstore.dto.CustomerDTO;
import com.store.bookstore.entities.Customer;
import com.store.bookstore.exceptions.CustomerAlreadyExistsException;
import com.store.bookstore.exceptions.CustomerNotFoundException;
import com.store.bookstore.repository.CustomerRepository;
import com.store.bookstore.service.AuthService;
import com.store.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Override
    public AuthResponseDTO createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.email() == null || customerDTO.email().isBlank()) {
            throw new CustomerAlreadyExistsException("Email is required");
        }

        if (customerRepository.existsByEmail(customerDTO.email())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }
        // TODO: validate password size
        Customer customer = new Customer(
                customerDTO.email(),
                customerDTO.firstName(),
                customerDTO.lastName(),
                passwordEncoder.encode(customerDTO.password())
        );

        customerRepository.save(customer);
        return authService.registerUser(customerDTO);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws CustomerNotFoundException {
        Customer user = customerRepository.findByEmail(email);
        if (user == null) {
            throw new CustomerNotFoundException("Customer not found with this email: " + email);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public CustomerAuthDTO findUserByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with this email: " + email);
        }
        return new CustomerAuthDTO(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }
}
