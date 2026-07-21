package com.store.bookstore.controller;

import com.store.bookstore.configuration.JwtProvider;
import com.store.bookstore.dto.AuthResponseDTO;
import com.store.bookstore.dto.CustomerAuthDTO;
import com.store.bookstore.dto.CustomerDTO;
import com.store.bookstore.exceptions.CustomerNotFoundException;
import com.store.bookstore.exceptions.PasswordUnmatchException;
import com.store.bookstore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        AuthResponseDTO customerCredentials = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(customerCredentials);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginCustomer(@RequestBody CustomerDTO customerDTO) {
        String username = customerDTO.email();
        String password = customerDTO.password();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // after authorization, get customer by email
        CustomerAuthDTO custumerAuthDTO = customerService.findUserByEmail(customerDTO.email());

        String token = JwtProvider.generateToken(authentication, custumerAuthDTO);
        CustomerAuthDTO customer = customerService.findUserByEmail(customerDTO.email());
        AuthResponseDTO authResponse = new AuthResponseDTO(
                token,
                "Login success",
                customer,
                true
        );
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerService.loadUserByEmail(email);
        if (userDetails == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new PasswordUnmatchException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
