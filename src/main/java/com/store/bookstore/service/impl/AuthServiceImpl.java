package com.store.bookstore.service.impl;

import com.store.bookstore.configuration.JwtProvider;
import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerAuthDTO;
import com.store.bookstore.dto.CustomerDTO;
import com.store.bookstore.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponseDTO registerUser(CustomerDTO customerDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(customerDTO.email(), customerDTO.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomerAuthDTO customerAuthDTO = new CustomerAuthDTO(
                customerDTO.id(),
                customerDTO.email(),
                customerDTO.firstName(),
                customerDTO.lastName()
        );
        String token = JwtProvider.generateToken(authentication, customerAuthDTO);

        return new AuthResponseDTO(
                token,
                "Register Success",
                null,
                true
        );
    }
}
