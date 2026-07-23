package com.store.bookstore.repository;

import com.store.bookstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartAndBookIdAndPrice(
            Long cartId,
            Long bookId,
            BigDecimal price
    );
}
