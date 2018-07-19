package sk.bucekm.springCrm.dao;

import sk.bucekm.springCrm.entity.Author;

import java.util.List;

public interface AuthorDAO {

    public List<Author> getAuthors();

    public void saveAuthor(Author theAuthor);

    public Author getAuthor(int theId);

    public void deleteAuthor(int theId);
}
