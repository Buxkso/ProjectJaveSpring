package sk.bucekm.springCrm.service;

import sk.bucekm.springCrm.entity.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> getAuthors();

    public void saveAuthor(Author theAuthor);

    public Author getAuthor(int theId);

    public void deleteAuthor(int theId);
}
