package com.store.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerAuthDTO(
        Long id,
        @NotBlank String email,
        @NotBlank String firstName,
        @NotBlank String lastName
        ) {

}
