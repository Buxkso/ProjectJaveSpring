package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Cart> getCarts() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<Cart> theQuery =
                currentSession.createQuery("from Cart order by cart_id",
                        Cart.class);

        // execute query and get result list
        List<Cart> carts = theQuery.getResultList();

        // return the results
        return carts;
    }

    @Override
    public void saveCart(Cart theCart) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(theCart);

    }

    @Override
    public Cart getCart(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Cart theCart = currentSession.get(Cart.class, theId);

        return theCart;
    }

}
