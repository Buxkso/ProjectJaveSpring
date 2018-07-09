package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Author;
import java.util.List;

public interface AuthorDAO {

    public List<Author> getAuthors();

    public void saveAuthor(Author theAuthor);

    public Author getAuthor(int theId);

    public void deleteAuthor(int theId);
}
