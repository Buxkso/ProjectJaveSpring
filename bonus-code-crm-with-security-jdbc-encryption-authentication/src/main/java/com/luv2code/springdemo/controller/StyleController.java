package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Style;
import com.luv2code.springdemo.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/style")
public class StyleController {
    // need to inject our style service
    @Autowired
    private StyleService styleService;

    @GetMapping("/list")
    public String listStyles(Model theModel) {

        // get styles from the service
        List<Style> theStyles = styleService.getStyles();

        // add the styles to the model
        theModel.addAttribute("styles", theStyles);

        return "list-styles";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Style theStyle = new Style();

        theModel.addAttribute("style", theStyle);

        return "style-form";
    }

    @PostMapping("/saveStyle")
    public String saveStyle(@ModelAttribute("style") Style theStyle) {

        // save the style using our service
        styleService.saveStyle(theStyle);

        return "redirect:/style/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("styleId") int theId,
                                    Model theModel) {

        // get the style from our service
        Style theStyle = styleService.getStyle(theId);

        // set style as a model attribute to pre-populate the form
        theModel.addAttribute("style", theStyle);

        // send over to our form		
        return "style-form";
    }

    @GetMapping("/delete")
    public String deleteStyle(@RequestParam("styleId") int theId) {

        // delete the style
        styleService.deleteStyle(theId);

        return "redirect:/style/list";
    }
}
