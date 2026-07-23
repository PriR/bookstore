package com.store.bookstore.service;

import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerAuthDTO;
import com.store.bookstore.dto.CustomerDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerService {

    AuthResponseDTO createCustomer(CustomerDTO customerDTO);

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    CustomerAuthDTO findUserByEmail(String email);
}
