package com.store.bookstore.service.impl;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.dto.CartItemDTO;
import com.store.bookstore.entities.Cart;
import com.store.bookstore.exceptions.BookNotFoundException;
import com.store.bookstore.exceptions.CartNotFoundException;
import com.store.bookstore.repository.BookRepository;
import com.store.bookstore.repository.CartRepository;
import com.store.bookstore.service.CartItemService;
import com.store.bookstore.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartItemService cartItemService;

    /**
     *
     * @param cartDTO cart
     *                Method check if cart for consumer already exists
     *                If yes:
     *                return existing cart
     *                else:
     *                create cart
     *                return created cart
     * @return created CartDTO
     */
    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        Optional<Cart> cartDB = cartRepository.findCartByCustomerId(cartDTO.customerId());

        if (cartDB.isPresent()) {
            Cart cart = cartDB.get();
            return new CartDTO(cart.getId(),
                    cart.getCustomerId(),
                    cart.getItems());
        } else {
            Cart cart = new Cart();
            cart.setCustomerId(cartDTO.customerId());
            Cart createdCart = cartRepository.save(cart);
            return new CartDTO(createdCart.getId(),
                    createdCart.getCustomerId(),
                    createdCart.getItems());
        }

    }

    @Override
    public CartDTO findCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
        return new CartDTO(cart.getId(), cart.getCustomerId(), cart.getItems());
    }

    @Override
    public void removeCart(Long customerId) {
        cartRepository.deleteById(customerId);
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
    public CartDTO addItemToCart(Long customerId, CartItemDTO cartItemDTO) {
        bookRepository.findById(cartItemDTO.book().id()).orElseThrow(() -> new BookNotFoundException("Book not found"));
        Optional<Cart> cart = cartRepository.findById(cartItemDTO.cartId());

        if (cart.isEmpty()) {
            cartRepository.save(new Cart(customerId));
        }
        return retrieveCartByCustomerId(customerId, cartItemDTO);
    }

    CartDTO retrieveCartByCustomerId(Long customerId, CartItemDTO cartItemDTO) {
        cartItemService.createCartItem(cartItemDTO);
        Cart cart = cartRepository.getCartByCustomerId(customerId);
        return new CartDTO(
                cart.getId(),
                cart.getCustomerId(),
                cart.getItems()
        );
    }


    @Override
    @Transactional
    public CartDTO removeItemFromCart(Long customerId, CartItemDTO cartItemDTO) {
        return null;
    }
}
