package com.luv2code.springdemo.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class CrmUser {

    @Id
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String userName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @OneToMany(mappedBy = "theUsernameAuth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Authority> auths = new ArrayList<>();

    @OneToMany(mappedBy = "theUsername")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> book = new ArrayList<>();

    @OneToOne(mappedBy = "cartOfUser")
    private Cart userCart;

    public CrmUser() {

    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public List<Authority> getAuths() {
        return auths;
    }

    public void setAuths(List<Authority> auths) {
        this.auths = auths;
    }


    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
