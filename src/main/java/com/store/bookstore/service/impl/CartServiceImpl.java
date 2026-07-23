package com.store.bookstore.service.impl;

import com.store.bookstore.dto.CartDTO;
import com.store.bookstore.entities.Cart;
import com.store.bookstore.exceptions.CartNotFoundException;
import com.store.bookstore.repository.CartRepository;
import com.store.bookstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

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

}
