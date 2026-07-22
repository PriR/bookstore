package com.store.bookstore.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        @NotNull Long cartId,
        @NotNull Long bookId,
        @NotNull int quantity,
        @NotNull BigDecimal price
) {
}