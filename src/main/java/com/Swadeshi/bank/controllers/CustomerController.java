package com.Swadeshi.bank.controllers;

import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.CustomerModel;
import com.Swadeshi.bank.services.CustomerService;
import com.Swadeshi.bank.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/swadeshi/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @GetMapping("/")
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
        List<String> errors = customerValidator.validate(customer);
        if(!errors.isEmpty()){
            model.addAttribute("error", errors);
        }else{
            try{
                boolean isNew = (customer.getId() == null);
                customerService.saveCustomer(customer);
                if(isNew){
                    model.addAttribute("success","Customer saved successfully");
                }else{
                    model.addAttribute("success","Customer updated successfully");
                }
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        model.addAttribute("customers", customerService.findAll());
        return "customer";
    }

    @GetMapping("/edit/{id}")
    public String updateCustomer(@PathVariable Long id, Model model){
        try {
            CustomerModel customer = customerService.findById(id);
            model.addAttribute("customer", customer);
            return "customer-form";
        } catch (IdNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("customers", customerService.findAll());
            return "customer";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String removeCustomer(@PathVariable Long id, Model model){
        try {
            customerService.removeCustomer(id);
            model.addAttribute("success", "Customer removed successfully");
        } catch (IdNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("customers", customerService.findAll());
        return "customer";
    }

    @GetMapping("/find/{id}")
    public String findCustomerById(@PathVariable Long id, Model model){
        try {
            CustomerModel customer = customerService.findById(id);
            model.addAttribute("success", "Customer found with id : "+id);
            model.addAttribute("customers",List.of(customer));
        } catch (IdNotFoundException e) {
            model.addAttribute("error", "No Customer found with id : "+id);
            model.addAttribute("customers", null);
        }
        return "customer";
    }


}
