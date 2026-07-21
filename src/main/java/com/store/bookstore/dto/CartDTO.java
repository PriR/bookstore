package com.store.bookstore.dto;

import com.store.bookstore.entities.CartItem;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CartDTO(
        Long id,
        @NotBlank Long customerId,
        List<CartItem> items
) {
}
