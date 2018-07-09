package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Author;
import com.luv2code.springdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    // need to inject our author service
    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model theModel) {

        // get authors from the service
        List<Author> theAuthors = authorService.getAuthors();

        // add the authors to the model
        theModel.addAttribute("authors", theAuthors);

        return "list-authors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Author theAuthor = new Author();

        theModel.addAttribute("author", theAuthor);

        return "author-form";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("author") Author theAuthor) {

        // save the author using our service
        authorService.saveAuthor(theAuthor);

        return "redirect:/author/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("authorId") int theId,
                                    Model theModel) {

        // get the author from our service
        Author theAuthor = authorService.getAuthor(theId);

        // set author as a model attribute to pre-populate the form
        theModel.addAttribute("author", theAuthor);

        // send over to our form		
        return "author-form";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("authorId") int theId) {

        // delete the author
        authorService.deleteAuthor(theId);

        return "redirect:/author/list";
    }
}
