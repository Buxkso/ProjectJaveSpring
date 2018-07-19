package sk.bucekm.springCrm.service;

import sk.bucekm.springCrm.entity.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> getCarts();

    public void saveCart(Cart theCart);

    public Cart getCart(int theId);

}
