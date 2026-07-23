package com.store.bookstore.controller;

import com.store.bookstore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<Long> decreaseQuantityCartItemById(@PathVariable Long cartItemId) {
        cartItemService.decreaseQuantityCartItemById(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Long> deleteCartItemById(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }
}