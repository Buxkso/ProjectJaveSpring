package com.luv2code.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String home() {

		return "registration/fancy-login";

	}

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "registration/fancy-login";
		
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "system/access-denied";
		
	}
	
}









