package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getBooks() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<Book> theQuery =
                currentSession.createQuery("from Book order by name",
                        Book.class);

        // execute query and get result list
        List<Book> books = theQuery.getResultList();

        // return the results		
        return books;
    }

    @Override
    public void saveBook(Book theBook) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/upate the book ... finally LOL
        currentSession.saveOrUpdate(theBook);

    }

    @Override
    public Book getBook(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Book theBook = currentSession.get(Book.class, theId);

        return theBook;
    }

    @Override
    public void deleteBook(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);

        theQuery.executeUpdate();
    }
}
