package sk.bucekm.springCrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.bucekm.springCrm.dao.CartDAO;
import sk.bucekm.springCrm.entity.Cart;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    // need to inject cart dao
    @Autowired
    private CartDAO cartDAO;

    @Override
    @Transactional
    public List<Cart> getCarts() {
        return cartDAO.getCarts();
    }

    @Override
    @Transactional
    public void saveCart(Cart theCart) {

        cartDAO.saveCart(theCart);
    }

    @Override
    @Transactional
    public Cart getCart(int theId) {

        return cartDAO.getCart(theId);
    }

}
