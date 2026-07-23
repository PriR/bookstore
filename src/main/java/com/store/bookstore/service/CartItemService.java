package com.store.bookstore.service;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;

public interface CartItemService {

    CartItemDTO createOrIncreaseQuantityCartItem(CartItemDTO cartItemDTO);
    Long deleteCartItemById(Long cartItemId);

    Long decreaseQuantityCartItemById(Long cartItemId);

    CartDTO increaseQuantityCartItemById(
            Long customerId,
            CartItemDTO cartItemDTO
    );
}
