package com.example.coderbytechallenge.repository;

import com.example.coderbytechallenge.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
