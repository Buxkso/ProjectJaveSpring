package sk.bucekm.springCrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.bucekm.springCrm.entity.*;
import sk.bucekm.springCrm.service.AuthorService;
import sk.bucekm.springCrm.service.BookService;
import sk.bucekm.springCrm.service.CustomerService;
import sk.bucekm.springCrm.service.StyleService;

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

    @PostMapping("/saveBook")
    public String saveBook(HttpServletRequest request) throws ServletException, IOException {

        String[] identifier = request.getParameterValues("book_id");
        Book theBook;
        if (identifier[0].equals("0")) {
            theBook = new Book();
        } else {
            theBook = bookService.getBook(Integer.parseInt(identifier[0]));
        }
        String[] nm = request.getParameterValues("name");
        String[] st = request.getParameterValues("styleList");
        String[] auth = request.getParameterValues("theAuthor");
        theBook.setName(nm[0]);
        theBook.setReserved(0);
        theBook.getStyleList().clear();
        theBook.setTheAuthor(authorService.getAuthor(Integer.parseInt(auth[0])));
        for (int i = 0; i < st.length; i++) {
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
