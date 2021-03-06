package sk.bucekm.springCrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sk.bucekm.springCrm.entity.Cart;
import sk.bucekm.springCrm.entity.CrmUser;
import sk.bucekm.springCrm.service.CartService;
import sk.bucekm.springCrm.service.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("crmUser", new CrmUser());

        return "registration/registration-form";

    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theCrmUser.getUserName();

        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()) {

            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name/password can not be empty.");

            logger.warning("User name/password can not be empty.");

            return "registration/registration-form";
        }


        boolean userExists = doesUserExist(userName);

        if (userExists) {
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");

            return "registration/registration-form";
        }

        //
        //we passed all of the validation checks!

        // encrypt the password
        String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());

        // prepend the encoding algorithm id
        encodedPassword = "{bcrypt}" + encodedPassword;

        // give user default role of "employee"
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");

        // create user object (from Spring Security framework)
        User tempUser = new User(userName, encodedPassword, authorities);

        Cart theCart = new Cart();

        userDetailsManager.createUser(tempUser);
        CrmUser createdUser = customerService.getCrmUser(userName);
        theCart.setCartOfUser(createdUser);
        cartService.saveCart(theCart);

        logger.info("Successfully created user: " + userName);

        return "registration/registration-confirmation";
    }

    private boolean doesUserExist(String userName) {

        logger.info("Checking if user exists: " + userName);

        // check the database if the user already exists
        boolean exists = userDetailsManager.userExists(userName);

        logger.info("User: " + userName + ", exists: " + exists);

        return exists;
    }

}
