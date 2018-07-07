package com.luv2code.springdemo.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="styles")
public class Style {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany
    @JoinTable(name="book_style",joinColumns = {@JoinColumn(name="book_id", referencedColumnName="id")},
    inverseJoinColumns = {@JoinColumn(name="style_id", referencedColumnName="id")}
    )
    private List<Book> bookList = new ArrayList<Book>();

    public Style(){

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
