package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Style;

import java.util.List;

public interface StyleDAO {

    public List<Style> getStyles();

    public void saveStyle(Style theStyle);

    public Style getStyle(int theId);

    public void deleteStyle(int theId);
}
