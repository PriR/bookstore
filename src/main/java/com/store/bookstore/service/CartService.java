package com.store.bookstore.service;

import com.store.bookstore.dto.CartDTO;

public interface CartService {
    CartDTO createCart(CartDTO cartDTO);

    CartDTO findCartByCustomerId(Long customerId);

    void removeCart(Long customerId);

}
