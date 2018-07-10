package com.luv2code.springdemo.controller;

import java.util.*;

import com.luv2code.springdemo.entity.Book;
import com.luv2code.springdemo.entity.CrmUser;
import com.luv2code.springdemo.service.BookService;
import com.luv2code.springdemo.service.CartService;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.luv2code.springdemo.service.CustomerService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CartService cartService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<CrmUser> theCrmUsers = customerService.getCrmUsers();
		// add the customers to the model
		theModel.addAttribute("customers", theCrmUsers);
		return "customer/list-customers";
	}

	@GetMapping("/cart")
	public String listCart(Model theModel) {

		// get customers from the service
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usrnm = authentication.getName();
		CrmUser usr = customerService.getCrmUser(usrnm);
		List<Book> reservedBooks = usr.getUserCart().getBookList();
		List<Book> ownedBooks = usr.getBook();

		theModel.addAttribute("ownedBooks", ownedBooks);
		theModel.addAttribute("reservedBooks", reservedBooks);
		return "customer/list-cart";
	}

	@GetMapping("/showUserCart")
	public String showUserCart(@RequestParam("customerId") String theId,
									Model theModel) {
		CrmUser usr = customerService.getCrmUser(theId);
		List<Book> reservedBooks = usr.getUserCart().getBookList();
		List<Book> ownedBooks = usr.getBook();

		theModel.addAttribute("ownedBooks", ownedBooks);
		theModel.addAttribute("reservedBooks", reservedBooks);
		return "customer/list-cart";

	}

	@GetMapping("/cart-remove")
	public String cartRemove(@RequestParam("bookId") int theId,
									Model theModel) {

		Book theBook = bookService.getBook(theId);
		theBook.setTheCart(null);
		theBook.setReserved(0);
		bookService.saveBook(theBook);

		return "redirect:/customer/cart";
	}

	@GetMapping("/return-book")
	public String returnBook(@RequestParam("bookId") int theId,
									Model theModel) {

		Book theBook = bookService.getBook(theId);
		theBook.setReserved(0);
		theBook.setReserved_to(null);
		theBook.setReserved_from(null);
		theBook.setTheUsername(null);
		bookService.saveBook(theBook);
		return "redirect:/customer/cart";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") String theId,
									Model theModel) {

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 90);
		Date expired = calendar.getTime();
		CrmUser usr = customerService.getCrmUser(theId);

		List<Book> reservedBooks = usr.getUserCart().getBookList();
		for(Book bs :reservedBooks){
			bs.setReserved(3);
			bs.setTheUsername(usr);
			bs.setTheCart(null);
			bs.setReserved_from(today);
			bs.setReserved_to(expired);
			bookService.saveBook(bs);
		}
		return "redirect:/customer/list";
	}

}










