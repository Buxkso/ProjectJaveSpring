package com.luv2code.springdemo.service;

import com.luv2code.springdemo.dao.BookDAO;
import com.luv2code.springdemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    // need to inject book dao
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book theBook) {

        bookDAO.saveBook(theBook);
    }

    @Override
    @Transactional
    public Book getBook(int theId) {

        return bookDAO.getBook(theId);
    }

    @Override
    @Transactional
    public void deleteBook(int theId) {

        bookDAO.deleteBook(theId);
    }
}
