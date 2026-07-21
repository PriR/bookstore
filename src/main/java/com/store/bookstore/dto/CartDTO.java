package com.store.bookstore.dto;

import com.store.bookstore.entities.CartItem;

import java.util.List;

public record CartDTO(
        Long id,
        Long customerId,
        List<CartItem> items
) {
}
