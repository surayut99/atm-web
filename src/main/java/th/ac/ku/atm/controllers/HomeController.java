package th.ac.ku.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHome(Model model) {
        model.addAttribute("greeting", "Welcome to MY_BANK");
        return "home";
    }
}
