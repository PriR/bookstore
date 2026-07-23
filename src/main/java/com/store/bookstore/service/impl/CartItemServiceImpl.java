package com.store.bookstore.service.impl;

import com.store.bookstore.dto.AuthorDTO;
import com.store.bookstore.dto.BookDTO;
import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;
import com.store.bookstore.entities.Author;
import com.store.bookstore.entities.Book;
import com.store.bookstore.entities.Cart;
import com.store.bookstore.entities.CartItem;
import com.store.bookstore.exceptions.BookNotFoundException;
import com.store.bookstore.repository.BookRepository;
import com.store.bookstore.repository.CartItemRepository;
import com.store.bookstore.repository.CartRepository;
import com.store.bookstore.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private BookRepository bookRepository;

    /**
     * Method that creates cart item if it does not exist otherwise increases the quantity for the cart item
     *
     * @param cartItemDTO
     * @return
     */
    @Override
    @Transactional
    public CartItemDTO createOrIncreaseQuantityCartItem(CartItemDTO cartItemDTO) {
        List<CartItem> cartItemList = cartItemRepository.findByCartAndBookIdAndPrice(cartItemDTO.cartId(), cartItemDTO.book().id(), cartItemDTO.price());
        CartItem cartItem;
        if (cartItemList.isEmpty()) {
            cartItem = mapCartDTOToEntity(cartItemDTO);
        } else {
            cartItem = cartItemList.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        return createUpdateCartItem(cartItem);
    }

    private static @NonNull CartItem mapCartDTOToEntity(CartItemDTO cartItemDTO) {
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
        return cartItem;
    }

    @Override
    public Long deleteCartItemById(Long cartItemId) {
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

    /**
     *
     * @param customerId  customer
     * @param cartItemDTO cart item
     *                    Method that handles creation of cart items
     *                    Validate first if book exists:
     *                    If not, exception book not found
     *                    Otherwise:
     *                    If cart exists, add item into cart
     *                    If cart does not exist, create cart and add item into cart
     * @return created cart CartDTO
     */
    @Override
    @Transactional
    public CartDTO increaseQuantityCartItemById(
            Long customerId,
            CartItemDTO cartItemDTO
    ) {
        bookRepository.findById(cartItemDTO.book().id()).orElseThrow(() -> new BookNotFoundException("Book not found"));
        Optional<Cart> cart = cartRepository.findById(cartItemDTO.cartId());

        if (cart.isEmpty()) {
            cartRepository.save(new Cart(customerId));
        }
        return retrieveCartByCustomerId(customerId, cartItemDTO);
    }

    @Transactional
    public CartDTO retrieveCartByCustomerId(Long customerId, CartItemDTO cartItemDTO) {
        createOrIncreaseQuantityCartItem(cartItemDTO);
        Cart cart = cartRepository.getCartByCustomerId(customerId);
        return new CartDTO(
                cart.getId(),
                cart.getCustomerId(),
                cart.getItems()
        );
    }
}
