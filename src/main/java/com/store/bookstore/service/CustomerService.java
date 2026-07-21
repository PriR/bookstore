package com.store.bookstore.service;

import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerAuthDTO;
import com.store.bookstore.dto.CustomerDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CustomerService {

    AuthResponseDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;


//    public List<Customer> getAllCustomer()  ;
//
//    public Customer findUserProfileByJwt(String jwt);
//
    CustomerAuthDTO findUserByEmail(String email) ;
//
//    public Customer findUserById(String userId) ;
//
//    public List<Customer> findAllUsers();
}
