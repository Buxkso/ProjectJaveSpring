package sk.bucekm.springCrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.bucekm.springCrm.dao.BookDAO;
import sk.bucekm.springCrm.entity.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

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
    public List<Book> getBooksByUsr() {
        return bookDAO.getBooksByUsr();
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
