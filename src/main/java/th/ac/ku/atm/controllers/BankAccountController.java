package th.ac.ku.atm.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th.ac.ku.atm.models.BankAccount;
import th.ac.ku.atm.services.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService customerService) {
        this.bankAccountService = customerService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {

        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());

        return "bankaccount"; // html filename
    }

    @PostMapping
    public String createAccount(@ModelAttribute BankAccount account, Model model) {
        bankAccountService.openAccount(account);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());

        return "redirect:bankaccount"; // redirect: used to prevent calling this method again; after ':' <URI>
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {

        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        bankAccountService.deleteBankAccount(account);

        return "redirect:/bankaccount";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id, @RequestParam Map<String, String> map, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        account.setBalance(account.getBalance() + Integer.parseInt(map.get(id + "")));
        bankAccountService.editBankAccount(account);

        return "redirect:/bankaccount";
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id, @RequestParam Map<String, String> map, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        account.setBalance(account.getBalance() - Integer.parseInt(map.get(id + "")));
        bankAccountService.editBankAccount(account);

        return "redirect:/bankaccount";
    }

}
