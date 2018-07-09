package com.luv2code.springdemo.controller;

import java.util.List;

import com.luv2code.springdemo.entity.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<CrmUser> theCrmUsers = customerService.getCrmUsers();
		// add the customers to the model
		theModel.addAttribute("customers", theCrmUsers);
		return "list-customers";
	}



}










