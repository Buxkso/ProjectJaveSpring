package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.CrmUser;

public interface CustomerDAO {

	public List<CrmUser> getCrmUsers();

	public CrmUser getCrmUser(String theUsername);

	public void saveCrmUser(CrmUser thecrmUser);

	public void deleteCrmUser(String theUsername);


	
}
