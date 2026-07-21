package com.store.bookstore.service;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;

public interface CartService {
    CartDTO createCart(CartDTO cartDTO);

    CartDTO findCartByCustomerId(Long customerId);

    void removeCart(Long customerId);

    CartDTO addItemToCart(
            Long customerId,
            CartItemDTO cartItemDTO
    );

    CartDTO removeItemFromCart(
            Long customerId,
            CartItemDTO cartItemDTO
    );
}
