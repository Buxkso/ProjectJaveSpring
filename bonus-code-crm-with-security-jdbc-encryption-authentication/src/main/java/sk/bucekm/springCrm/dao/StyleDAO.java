package sk.bucekm.springCrm.dao;

import sk.bucekm.springCrm.entity.Style;

import java.util.List;

public interface StyleDAO {

    public List<Style> getStyles();

    public void saveStyle(Style theStyle);

    public Style getStyle(int theId);

    public void deleteStyle(int theId);
}
