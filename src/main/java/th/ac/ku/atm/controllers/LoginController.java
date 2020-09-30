package th.ac.ku.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.ac.ku.atm.models.Customer;
import th.ac.ku.atm.services.BankAccountService;
import th.ac.ku.atm.services.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService;
    private BankAccountService bankAccountService;

    public LoginController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {

        Customer matched = customerService.checkPin(customer);

        if (matched != null) {
            model.addAttribute("customerTitle", matched.getName() + "Bank Accoonts");
            model.addAttribute("bankaccounts", bankAccountService.getCustomerBankAccount(customer.getId()));

            return "customeraccount";
            // model.addAttribute("greeting", "Welcome, " + matched.getName());
        } else {
            model.addAttribute("greeting", "Sorry, this id doesn't exist");
            return "home";
        }
    }
}
