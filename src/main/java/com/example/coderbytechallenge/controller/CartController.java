package com.example.coderbytechallenge.controller;

import com.example.coderbytechallenge.entity.CartItem;
import com.example.coderbytechallenge.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoppingCart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addItemToCart(@RequestBody CartItem item) {
        cartService.addItemToCart(item);
    }

    @RequestMapping(value = "/remove/{itemId}", method = RequestMethod.DELETE)
    public void removeItemFromCart(@PathVariable Long itemId) {
        cartService.removeItemFromCart(itemId);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<CartItem> getAllItemsInACart() {
        return cartService.getAllItemsInCart();
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<CartItem> getAnItemInCart(@PathVariable Long itemId) {
        Optional<CartItem> item = cartService.getItemInCartById(itemId);
        if (item.isPresent()) {
            // If the item is present, return a 200 OK response with the item details.
            return ResponseEntity.ok(item.get());
        } else {
            // If the item is not present, return a 404 Not Found response.
            return ResponseEntity.notFound().build();
        }
    }
}