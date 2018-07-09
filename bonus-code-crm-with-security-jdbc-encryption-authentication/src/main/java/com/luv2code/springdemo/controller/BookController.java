package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Author;
import com.luv2code.springdemo.entity.Book;
import com.luv2code.springdemo.entity.Style;
import com.luv2code.springdemo.service.AuthorService;
import com.luv2code.springdemo.service.BookService;
import com.luv2code.springdemo.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
    private StyleService styleService;

    @GetMapping("/list")
    public String listBooks(Model theModel) {

        // get books from the service
        List<Book> theBooks = bookService.getBooks();
        // add the books to the model
        theModel.addAttribute("books", theBooks);

        return "list-books";
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

        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(HttpServletRequest request)throws ServletException,IOException {

        // save the book using our service
        String[] identifier = request.getParameterValues("book_id");
        Book theBook;
        if(identifier[0].equals("0")) {
             theBook = new Book();
        }else{
             theBook = bookService.getBook(Integer.parseInt(identifier[0]));
        }
        String[] nm = request.getParameterValues("name");
        String[] st = request.getParameterValues("styleList");
        String[] auth = request.getParameterValues("theAuthor");
        theBook.setName(nm[0]);
        theBook.setReserved(0);
        theBook.getStyleList().clear();
        theBook.setTheAuthor(authorService.getAuthor(Integer.parseInt(auth[0])));
        for(int i=0; i<st.length;i++){
            theBook.addStyle(styleService.getStyle(Integer.parseInt(st[i])));
        }
        bookService.saveBook(theBook);

        return "redirect:/book/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the book from our service
        Book theBook = bookService.getBook(theId);
        List<Style> styles = styleService.getStyles();
        List<Author> theAuthors = authorService.getAuthors();
        // set book as a model attribute to pre-populate the form

        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("styles",styles);
        theModel.addAttribute("book", theBook);

        // send over to our form		
        return "book-form";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theId) {

        // delete the book
        bookService.deleteBook(theId);

        return "redirect:/book/list";
    }
}
