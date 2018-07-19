package sk.bucekm.springCrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.bucekm.springCrm.dao.AuthorDAO;
import sk.bucekm.springCrm.entity.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    // need to inject author dao
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public List<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

    @Override
    @Transactional
    public void saveAuthor(Author theAuthor) {

        authorDAO.saveAuthor(theAuthor);
    }

    @Override
    @Transactional
    public Author getAuthor(int theId) {

        return authorDAO.getAuthor(theId);
    }

    @Override
    @Transactional
    public void deleteAuthor(int theId) {

        authorDAO.deleteAuthor(theId);
    }
}
