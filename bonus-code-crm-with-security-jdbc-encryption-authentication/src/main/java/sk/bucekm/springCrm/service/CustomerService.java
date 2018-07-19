package sk.bucekm.springCrm.service;

import sk.bucekm.springCrm.entity.CrmUser;

import java.util.List;

public interface CustomerService {

	public List<CrmUser> getCrmUsers();

	public CrmUser getCrmUser(String theUsername);

	public void saveCrmUser(CrmUser thecrmUser);

	public void deleteCrmUser(String theUsername);


}
