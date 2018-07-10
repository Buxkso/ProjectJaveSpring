package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> getAuthors() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<Author> theQuery =
                currentSession.createQuery("from Author order by surname",
                        Author.class);

        // execute query and get result list
        List<Author> authors = theQuery.getResultList();

        // return the results		
        return authors;
    }


    @Override
    public void saveAuthor(Author theAuthor) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(theAuthor);

    }

    @Override
    public Author getAuthor(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Author theAuthor = currentSession.get(Author.class, theId);

        return theAuthor;
    }

    @Override
    public void deleteAuthor(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("delete from Author where id=:authorId");
        theQuery.setParameter("authorId", theId);

        theQuery.executeUpdate();
    }
}
