package com.store.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        @NotBlank Long cartId,
        @NotBlank Long bookId,
        @NotBlank int quantity,
        @NotBlank BigDecimal price
) {
}