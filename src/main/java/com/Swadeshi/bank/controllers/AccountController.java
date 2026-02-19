package com.Swadeshi.bank.controllers;

import com.Swadeshi.bank.models.AccountModel;
import com.Swadeshi.bank.services.AccountService;
import com.Swadeshi.bank.services.BranchService;
import com.Swadeshi.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/swadeshi/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BranchService branchService;

    @GetMapping("/")
    public String getAccounts(Model model){
        List<AccountModel> accounts = accountService.findAllAccounts();
        if(accounts.isEmpty()){
            model.addAttribute("error", "No Accounts found");
        }else{
            model.addAttribute("success", accounts.size()+" Accounts found");
        }
        model.addAttribute("accounts", accounts);
        return "account";
    }

    @GetMapping("/new")
    public String newAccount(Model model){
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("branches", branchService.findAllBranches());
        model.addAttribute("account", new AccountModel());
        return "account-form";
    }

    @PostMapping("/save")
    public String saveAccount(@ModelAttribute AccountModel account, Model model){
        try {
            accountService.save(account);
            if(account.getId()==null){
                model.addAttribute("success","Account Created successfully");
                model.addAttribute("accounts",List.of());
            }else{
                model.addAttribute("success", "Account Details updated successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "account";
    }
}
