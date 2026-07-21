package com.store.bookstore.controller;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CustomerDTO;
import com.store.bookstore.service.CartService;
import com.store.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> list = customerService.getAllCustomers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{customerId}/cart")
    public ResponseEntity<CartDTO> fetchCartByCustomer(@PathVariable Long customerId) {
        CartDTO createdCartDTO = cartService.findCartByCustomerId((customerId));
        return ResponseEntity.ok().body(createdCartDTO);
    }
}
