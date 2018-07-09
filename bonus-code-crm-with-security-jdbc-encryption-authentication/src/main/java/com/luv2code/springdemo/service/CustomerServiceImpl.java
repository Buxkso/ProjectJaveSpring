package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<CrmUser> getCrmUsers() {
		return customerDAO.getCrmUsers();
	}

	@Override
	@Transactional
	public void saveCrmUser(CrmUser thecrmUser) {

		customerDAO.saveCrmUser(thecrmUser);
	}

	@Override
	@Transactional
	public CrmUser getCrmUser(String theUsername) {
		
		return customerDAO.getCrmUser(theUsername);
	}

	@Override
	@Transactional
	public void deleteCrmUser(String theUsername) {
		
		customerDAO.deleteCrmUser(theUsername);
	}
}





