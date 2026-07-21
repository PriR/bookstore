package com.store.bookstore.service.impl;

import com.store.bookstore.dto.*;
import com.store.bookstore.entities.CartItem;
import com.store.bookstore.repository.CartItemRepository;
import com.store.bookstore.service.CartItemService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;

//    Boolean cartItemExist(Long cartItemId) {
//        return cartItemRepository.existsById(cartItemId);
//    }

    /**
     *
     * @param cartItemDTO
     * @return
     * TODO: create cart item for cart and customer only if it doesn't exist. If it exists, increment quantity
     *
     */
    @Override
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cartItemDTO.cartId());
        cartItem.setBook(cartItemDTO.bookId());
        cartItem.setQuantity(cartItemDTO.quantity());
        cartItem.setPrice(cartItemDTO.price());
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return new CartItemDTO(
                createdCartItem.getId(),
                createdCartItem.getCart(),
                createdCartItem.getBook(),
                createdCartItem.getQuantity(),
                createdCartItem.getPrice()
        );

//        return new CartItemDTO(
//                createdCartItem.getId(),
//                new CartDTO(
//                        createdCartItem.getId(),
//                        createdCartItem.getCart()
//                        createdCartItem.getCart().getId(),
//                        createdCartItem.getCart(),
//                        createdCartItem.getCart().getCustomer(),
//                        createdCartItem.getCart().getCustomer().getId(),
//                        new CustomerDTO(
//                                createdCartItem.getCart().getCustomer().getId(),
//                                createdCartItem.getCart().getCustomer().getEmail(),
//                                createdCartItem.getCart().getCustomer().getFirstName(),
//                                createdCartItem.getCart().getCustomer().getLastName(),
//                                null
//                        ),
//                        createdCartItem.get

//                ),
//                new BookDTO(
//                        createdCartItem.getBook().getId(),
//                        createdCartItem.getBook().getTitle(),
//                        createdCartItem.getBook().getPrice().toString(),
//                        createdCartItem.getBook().getQuantityInStock(),
//                        new AuthorDTO(
//                                createdCartItem.getBook().getAuthor().getId(),
//                                createdCartItem.getBook().getAuthor().getFirstName(),
//                                createdCartItem.getBook().getAuthor().getLastName()
//                        )
//
//                ),
//                createdCartItem.getQuantity(),
//                createdCartItem.getPrice()
//        );
    }
}
