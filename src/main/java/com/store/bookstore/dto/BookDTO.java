package com.store.bookstore.dto;

import java.math.BigDecimal;

public record BookDTO(
        Long id,
        String title,
        BigDecimal price,
        AuthorDTO author
) {
}
