package org.project.Project.Controller;

import org.project.Project.models.Account;
import org.project.Project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/info")
    public String info(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "info"; 
    }
    
    @PostMapping("/info")
    public String info_post(@Valid @ModelAttribute Account account, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("errorMessage", "Registration failed. Please check your inputs.");
            return "info.html";
        }
    
        accountService.save(account);
        return "redirect:/"; 
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login"; 
    }
}
