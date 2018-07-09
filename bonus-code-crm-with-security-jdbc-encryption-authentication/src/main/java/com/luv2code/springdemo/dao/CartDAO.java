package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Cart;

import java.util.List;

public interface CartDAO {

    public List<Cart> getCarts();

    public void saveCart(Cart theCart);

    public Cart getCart(int theId);

}
