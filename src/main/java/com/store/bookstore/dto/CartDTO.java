package com.store.bookstore.dto;

import com.store.bookstore.entities.CartItem;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartDTO(
        Long id,
        @NotNull Long customerId,
        List<CartItem> items
) {
}
