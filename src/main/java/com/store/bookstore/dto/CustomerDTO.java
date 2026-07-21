package com.store.bookstore.dto;

import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
        Long id,
        @NotNull String email,
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String password
) {
}
