package com.store.bookstore.controller;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;
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
    public ResponseEntity<CartDTO> getCartByCustomer(@PathVariable Long customerId) {
        CartDTO createdCartDTO = cartService.findCartByCustomerId(customerId);
        return ResponseEntity.ok().body(createdCartDTO);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<CartDTO> deleteCartByCustomer(@PathVariable Long customerId) {
        cartService.removeCart(customerId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/customer/{customerId}/add-item") // TODO: recheck REST naming convention - evolve later to also get if substraction or addition
    public ResponseEntity<CartDTO> addItemToCart(
            @PathVariable Long customerId,
            @Valid @RequestBody CartItemDTO cartItemDTO
    ) {
        CartDTO createdCartItemDTO = cartService.addItemToCart(customerId, cartItemDTO);
        return ResponseEntity.ok().body(createdCartItemDTO);
    }

    @PatchMapping("/customer/{customerId}/remove-item") // TODO: recheck REST naming convention - evolve later to also get if substraction or addition
    public ResponseEntity<CartDTO> removeItemFromCart(
            @PathVariable Long customerId,
            @Valid @RequestBody CartItemDTO cartItemDTO
    ) {
        CartDTO createdCartItemDTO = cartService.removeItemFromCart(customerId, cartItemDTO);
        return ResponseEntity.ok().body(createdCartItemDTO);
    }

}