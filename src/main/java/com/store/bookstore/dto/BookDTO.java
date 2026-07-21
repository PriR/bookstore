package com.store.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BookDTO(
        @NotBlank Long id,
        @NotBlank String title,
        @NotBlank BigDecimal price,
        @NotBlank AuthorDTO author
) {
}
