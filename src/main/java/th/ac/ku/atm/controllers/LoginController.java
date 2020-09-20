package th.ac.ku.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.basedClasses.Customer;
import th.ac.ku.atm.services.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {

        Customer matched = customerService.checkPin(customer);

        if (matched != null) {
            model.addAttribute("header", "Welcome, " + matched.getName());
        } else {
            model.addAttribute("header", "Sorry, this id doesn't exist");
        }

        return "home";
    }
}
