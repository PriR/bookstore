package com.store.bookstore.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        @NotNull Long cartId,
        BookDTO book,
        @NotNull Integer quantity,
        @NotNull BigDecimal price
) {
}