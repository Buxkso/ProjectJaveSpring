package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.*;
import com.luv2code.springdemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // need to inject our book service
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StyleService styleService;


    @GetMapping("/list")
    public String listBooks(Model theModel) {

        // get books from the service
        List<Book> theBooks = bookService.getBooksByUsr();
        // add the books to the model
        theModel.addAttribute("books", theBooks);

        return "book/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Book theBook = new Book();
        List<Style> theStyles = styleService.getStyles();
        List<Author> theAuthors = authorService.getAuthors();
        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("styles", theStyles);
        theModel.addAttribute("book", theBook);

        return "book/book-form";
    }

    @GetMapping("/bookToCart")
    public String bookToCart(@RequestParam("bookId") int theId,
                             Model theModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CrmUser usr = customerService.getCrmUser(authentication.getName());
        Cart crt = usr.getUserCart();
        Book bk = bookService.getBook(theId);
        bk.setReserved(1);
        bk.setTheCart(crt);
        bookService.saveBook(bk);
        return "redirect:/book/list";
    }


@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book") Book book, BindingResult result, HttpServletRequest request, Model model) {

        bookService.saveBook(book);

        return "redirect:/book/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the book from our service
        Book theBook = bookService.getBook(theId);
        List<Style> styles = styleService.getStyles();
        List<Author> theAuthors = authorService.getAuthors();

        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("styles", styles);
        theModel.addAttribute("book", theBook);

        return "book/book-form";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theId) {

        // delete the book
        bookService.deleteBook(theId);

        return "redirect:/book/list";
    }
}
