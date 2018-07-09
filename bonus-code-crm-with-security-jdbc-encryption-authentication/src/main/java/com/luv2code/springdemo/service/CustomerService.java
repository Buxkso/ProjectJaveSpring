package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.CrmUser;
import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public List<CrmUser> getCrmUsers();

	public CrmUser getCrmUser(String theUsername);

	public void saveCrmUser(CrmUser thecrmUser);

	public void deleteCrmUser(String theUsername);


}
