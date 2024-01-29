package com.example.coderbytechallenge.service;

import com.example.coderbytechallenge.dto.CartItemDto;
import com.example.coderbytechallenge.entity.CartItem;
import com.example.coderbytechallenge.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    /**
     *  Adds the item to a cart
     * @param cartItemDto
     */
    public void addItemToCart(CartItemDto cartItemDto) {
        CartItem cartItemToAdd = new CartItem();
        cartItemToAdd.setItemName(cartItemDto.getItemName());
        cartItemToAdd.setPrice(cartItemDto.getPrice());
        cartItemRepository.save(cartItemToAdd);
    }

    /**
     * Removes the item from the cart
     * @param cartItemId
     */
    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    /**
     * Gets all item from the cart
     * @return List<CartItemDto>
     */
    public List<CartItemDto> getAllItemsInCart() {
        List<CartItemDto> allCartItemsDto = new ArrayList<>();
        List<CartItem> allCartItems = cartItemRepository.findAll();

        for (CartItem cartItem : allCartItems) {
            allCartItemsDto.add(getCartItemDto(cartItem));
        }
        return allCartItemsDto;
    }

    /**
     * Conversion of cart item entity to cart item dto
     * @param cartItem
     * @return
     */
    public CartItemDto getCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setItemName(cartItem.getItemName());
        cartItemDto.setPrice(cartItem.getPrice());
        return cartItemDto;
    }

    /**
     * Gets the cart item by id if present or returns null cart item
     * @param itemId
     * @return CartItemDto
     */
    public CartItemDto getItemInCartById(Long itemId) {
        CartItemDto cartItemDto = null;
        Optional<CartItem> cartItem = cartItemRepository.findById(itemId);
        if (cartItem.isPresent()) {
            cartItemDto = getCartItemDto(cartItem.get());
        }

        return cartItemDto;
    }
}
