package sk.bucekm.springCrm.dao;

import sk.bucekm.springCrm.entity.Cart;

import java.util.List;

public interface CartDAO {

    public List<Cart> getCarts();

    public void saveCart(Cart theCart);

    public Cart getCart(int theId);

}
