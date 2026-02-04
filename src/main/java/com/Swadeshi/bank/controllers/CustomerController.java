package com.Swadeshi.bank.controllers;

import com.Swadeshi.bank.models.CustomerModel;
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
@RequestMapping("/bank/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getCustomers(Model model){
        List<CustomerModel> customers = customerService.findAll();
        if(customers.isEmpty()){
            model.addAttribute("error", "No Customer found");
        }else{
            model.addAttribute("success", customers.size()+" customer found");
        }
        model.addAttribute("customers", customers);
        return "customer";
    }

    @GetMapping("/new")
    public String getCustomerForm(Model model){
        model.addAttribute("customer", new CustomerModel());
        return "customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute CustomerModel customer, Model model){

        model.addAttribute("customers", customerService.findAll());
        return "customer";
    }

}
