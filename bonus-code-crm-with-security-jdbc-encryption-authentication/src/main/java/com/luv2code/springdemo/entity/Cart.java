package com.luv2code.springdemo.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="carts")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private String cart_id;

    @OneToOne
	@JoinColumn(name = "username")
    private CrmUser cartOfUser;

    @OneToMany(mappedBy = "theCart")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Book> bookList = new ArrayList<>();


    public Cart() {

    }

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}

	public CrmUser getCartOfUser() {
		return cartOfUser;
	}

	public void setCartOfUser(CrmUser cartOfUser) {
		this.cartOfUser = cartOfUser;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}
