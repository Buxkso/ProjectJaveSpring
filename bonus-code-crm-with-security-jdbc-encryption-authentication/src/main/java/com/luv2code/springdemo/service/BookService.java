package com.luv2code.springdemo.service;

import com.luv2code.springdemo.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> getBooks();

    public void saveBook(Book theBook);

    public Book getBook(int theId);

    public void deleteBook(int theId);
}
