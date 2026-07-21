package com.store.bookstore.dto;

public record AuthResponseDTO(
        String jwt,
        String message,
        CustomerAuthDTO customer,
        Boolean status
) {
}
