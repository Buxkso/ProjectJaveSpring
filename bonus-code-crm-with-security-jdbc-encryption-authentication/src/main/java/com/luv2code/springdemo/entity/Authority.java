package com.luv2code.springdemo.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table(name="authorities")
public class Authority {

	@Id
	@Column(name="authority")
	private String role;

	@ManyToOne
	@JoinColumn(name = "username")
	@LazyCollection(LazyCollectionOption.TRUE)
	private CrmUser theUsernameAuth;

	public Authority(){

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}





