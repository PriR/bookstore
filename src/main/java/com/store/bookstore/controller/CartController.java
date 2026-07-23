package com.store.bookstore.controller;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> createCartByCustomer(@Valid @RequestBody CartDTO cartDTO) {
        CartDTO createdCartDTO = cartService.createCart(cartDTO);
        return ResponseEntity.ok().body(createdCartDTO);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CartDTO> getCartByCustomerId(@PathVariable Long customerId) {
        CartDTO createdCartDTO = cartService.findCartByCustomerId(customerId);
        return ResponseEntity.ok().body(createdCartDTO);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<CartDTO> deleteCartByCustomerId(@PathVariable Long customerId) {
        cartService.removeCart(customerId);
        return ResponseEntity.noContent().build();
    }

}