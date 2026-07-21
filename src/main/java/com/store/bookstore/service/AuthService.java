package com.store.bookstore.service;

import com.store.bookstore.dto.CustomerDTO;

public interface AuthService {

    CustomerDTO registerUser(CustomerDTO customerDTO);
}
