package com.example.coderbytechallenge.controller;

import com.example.coderbytechallenge.dto.CartItemDto;
import com.example.coderbytechallenge.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * This API provides a functionality to add the items into the Shopping Cart
     * @param cartItemDto
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addItemToCart(@RequestBody CartItemDto cartItemDto) {
        cartService.addItemToCart(cartItemDto);
    }

    /**
     * This API provides a functionality to remove the items from the Shopping Cart
     * @param cartItemId
     */
    @RequestMapping(value = "/remove/{cartItemId}", method = RequestMethod.DELETE)
    public void removeItemFromCart(@PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
    }

    /**
     * This API provides a functionality to get all the items from the Shopping Cart
     * @return List<CartItemDto>
     */
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<CartItemDto> getAllItemsInACart() {
        return cartService.getAllItemsInCart();
    }

    /**
     * This API provides a functionality to get a particular item with item id from the Shopping Cart
     * And if no Item is present then it returns not found
     * @param itemId
     * @return CartItemDto
     */
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<CartItemDto> getAnItemInCart(@PathVariable Long itemId) {
        CartItemDto cartItemDto = cartService.getItemInCartById(itemId);
        if (cartItemDto != null) {
            return ResponseEntity.ok(cartItemDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}