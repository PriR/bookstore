package com.store.bookstore.service;

import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerDTO;

public interface AuthService {

    AuthResponseDTO registerUser(CustomerDTO customerDTO);
}
