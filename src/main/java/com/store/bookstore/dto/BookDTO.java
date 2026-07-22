package com.store.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BookDTO(
        @NotNull Long id,
        @NotBlank String title,
        @NotNull BigDecimal price,
        @NotNull AuthorDTO author
) {
}
