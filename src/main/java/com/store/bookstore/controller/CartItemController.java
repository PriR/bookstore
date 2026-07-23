package com.store.bookstore.controller;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;
import com.store.bookstore.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PatchMapping("/{cartItemId}/decrease-quantity")
    public ResponseEntity<Long> decreaseQuantityCartItemById(@PathVariable Long cartItemId) {
        cartItemService.decreaseQuantityCartItemById(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

    @PatchMapping("/customer/{customerId}/increase-quantity")
    public ResponseEntity<CartDTO> increaseQuantityCartItemById(@PathVariable Long customerId, @Valid @RequestBody CartItemDTO cartItemDTO) {
        CartDTO createdCartItemDTO = cartItemService.increaseQuantityCartItemById(customerId, cartItemDTO);
        return ResponseEntity.ok().body(createdCartItemDTO);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Long> deleteCartItemById(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }
}