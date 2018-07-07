package com.luv2code.springdemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="author")
    private Author theAuthor;


    @Temporal(TemporalType.DATE)
    @Column(name="reserved_from")
    private Date reserved_from;

    @Temporal(TemporalType.DATE)
    @Column(name="reserved_to")
    private Date reserved_to;

    @Column(name="reserved")
    private int reserved;


    @ManyToOne
    @JoinColumn(name = "username")
    private CrmUser theUsername;

    public CrmUser getTheUsername() {
        return theUsername;
    }

    public void setTheUsername(CrmUser theUsername) {
        this.theUsername = theUsername;
    }

    @ManyToMany(mappedBy = "bookList")
    private List<Style> styleList = new ArrayList<Style>();

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getTheAuthor() {
        return theAuthor;
    }

    public void setTheAuthor(Author theAuthor) {
        this.theAuthor = theAuthor;
    }

    public Date getReserved_from() {
        return reserved_from;
    }

    public void setReserved_from(Date reserved_from) {
        this.reserved_from = reserved_from;
    }

    public Date getReserved_to() {
        return reserved_to;
    }

    public void setReserved_to(Date reserved_to) {
        this.reserved_to = reserved_to;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }


    public List<Style> getStyleList() {
        return styleList;
    }

    public void setStyleList(List<Style> styleList) {
        this.styleList = styleList;
    }
}
