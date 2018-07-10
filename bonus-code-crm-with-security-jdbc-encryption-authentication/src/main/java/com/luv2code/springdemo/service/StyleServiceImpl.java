package com.luv2code.springdemo.service;

import com.luv2code.springdemo.dao.StyleDAO;
import com.luv2code.springdemo.entity.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    // need to inject style dao
    @Autowired
    private StyleDAO styleDAO;

    @Override
    @Transactional
    public List<Style> getStyles() {
        return styleDAO.getStyles();
    }

    @Override
    @Transactional
    public void saveStyle(Style theStyle) {

        styleDAO.saveStyle(theStyle);
    }

    @Override
    @Transactional
    public Style getStyle(int theId) {

        return styleDAO.getStyle(theId);
    }

    @Override
    @Transactional
    public void deleteStyle(int theId) {

        styleDAO.deleteStyle(theId);
    }
}
