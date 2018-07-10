package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StyleDAOImpl implements StyleDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Style> getStyles() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<Style> theQuery =
                currentSession.createQuery("from Style order by name",
                        Style.class);

        // execute query and get result list
        List<Style> styles = theQuery.getResultList();

        // return the results
        return styles;
    }

    @Override
    public void saveStyle(Style theStyle) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(theStyle);

    }

    @Override
    public Style getStyle(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Style theStyle = currentSession.get(Style.class, theId);

        return theStyle;
    }

    @Override
    public void deleteStyle(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("delete from Style where id=:styleId");
        theQuery.setParameter("styleId", theId);

        theQuery.executeUpdate();
    }
}
