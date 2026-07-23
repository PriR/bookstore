package com.store.bookstore.service.impl;

import com.store.bookstore.dto.AuthorDTO;
import com.store.bookstore.dto.BookDTO;
import com.store.bookstore.dto.CartItemDTO;
import com.store.bookstore.entities.Author;
import com.store.bookstore.entities.Book;
import com.store.bookstore.entities.CartItem;
import com.store.bookstore.repository.CartItemRepository;
import com.store.bookstore.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;
    /**
     *
     * @param cartItemDTO
     * @return TODO: create cart item for cart and customer only if it doesn't exist. If it exists, increment quantity
     *
     */
    @Override
    @Transactional
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        List<CartItem> cartItemList = cartItemRepository.findByCartAndBookIdAndPrice(cartItemDTO.cartId(), cartItemDTO.book().id(), cartItemDTO.price());
        if (cartItemList.isEmpty()) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cartItemDTO.cartId());
            cartItem.setBook(new Book(
                    cartItemDTO.book().id(),
                    cartItemDTO.book().title(),
                    cartItemDTO.book().price(),
                    new Author(
                            cartItemDTO.book().author().id(),
                            cartItemDTO.book().author().firstName(),
                            cartItemDTO.book().author().lastName()
                    )
            ));
            cartItem.setQuantity(cartItemDTO.quantity());
            cartItem.setPrice(cartItemDTO.price());
            return createUpdateCartItem(cartItem);

        } else {
            CartItem cartItem = cartItemList.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return createUpdateCartItem(cartItem);
        }
    }

    @Override
    public Long deleteCartItemById(Long cartItemId) {
        // TODO: check if cart item exists, only then delete cart item
        cartItemRepository.deleteById(cartItemId);
        return cartItemId;
    }

    @Transactional
    public CartItemDTO createUpdateCartItem(CartItem cartItem) {
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return new CartItemDTO(
                createdCartItem.getId(),
                createdCartItem.getCart(),
                new BookDTO(
                        createdCartItem.getBook().getId(),
                        createdCartItem.getBook().getTitle(),
                        createdCartItem.getBook().getPrice(),
                        new AuthorDTO(
                                createdCartItem.getBook().getAuthor().getId(),
                                createdCartItem.getBook().getAuthor().getFirstName(),
                                createdCartItem.getBook().getAuthor().getLastName()
                        )
                ),
                createdCartItem.getQuantity(),
                createdCartItem.getPrice()
        );
    }

    @Override
    public Long decreaseQuantityCartItemById(Long cartItemId) {
        cartItemRepository.findById(cartItemId).ifPresent(cartItem -> {
            if (cartItem.getQuantity() == 1) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            }
        });
        return cartItemId;
    }


}
