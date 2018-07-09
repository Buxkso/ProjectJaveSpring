package com.luv2code.springdemo.service;

import com.luv2code.springdemo.entity.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> getCarts();

    public void saveCart(Cart theCart);

    public Cart getCart(int theId);

}
