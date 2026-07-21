package com.store.bookstore.dto;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        Long cartId,
        Long bookId,
        int quantity,
        BigDecimal price
) {
}