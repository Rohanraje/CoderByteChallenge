package com.example.coderbytechallenge.service;

import com.example.coderbytechallenge.entity.CartItem;
import com.example.coderbytechallenge.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addItemToCart(CartItem item) {
        cartItemRepository.save(item);
    }

    public void removeItemFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public List<CartItem> getAllItemsInCart() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getItemInCartById(Long itemId) {
        return cartItemRepository.findById(itemId);
    }
}
