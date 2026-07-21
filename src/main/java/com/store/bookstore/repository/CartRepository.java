package com.store.bookstore.repository;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByCustomerId(Long customerId);

    Cart getCartsByCustomerId(Long customerId);

//    CartDTO findCartByCustomerId(Long customerId);
}
