package com.store.bookstore.service;

import com.store.bookstore.dto.CartItemDTO;

public interface CartItemService {

    CartItemDTO createCartItem(CartItemDTO cartItemDTO);
    Long deleteCartItemById(Long cartItemId);

    Long decreaseQuantityCartItemById(Long cartItemId);

//    Long increaseQuantityCartItemById(Long cartItemId);
}
