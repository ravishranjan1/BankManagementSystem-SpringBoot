package com.Swadeshi.bank.controllers;

import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.BranchModel;
import com.Swadeshi.bank.services.BranchService;
import com.Swadeshi.bank.validator.BranchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/swadeshi/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchValidator branchValidator;

    @GetMapping("/")
    public String getBranches(Model model){
        List<BranchModel> branches = branchService.findAllBranches();
        if(branches.isEmpty()){
            model.addAttribute("error", "No Branch found");
        }else{
            model.addAttribute("success", branches.size()+" branches found");
        }
        model.addAttribute("branches", branches);
        return "branch";
    }

    @GetMapping("/new")
    public String getBranchForm(Model model){
        model.addAttribute("branch", new BranchModel());
        return "branch-form";
    }

    @PostMapping("/save")
    public String saveBranch(@ModelAttribute BranchModel branch, Model model){
        List<String> errors = branchValidator.validate(branch);
        if(!errors.isEmpty()){
            model.addAttribute("error", errors);
        }else{
            try {
                boolean isNew = (branch.getId() == null);
                branchService.saveBranch(branch);
                if(isNew){
                    model.addAttribute("success", "Branch saved successfully");
                }else{
                    model.addAttribute("success", "Branch updated successfully");
                }
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        model.addAttribute("branches", branchService.findAllBranches());
        return "branch";
    }

    @GetMapping("/edit/{id}")
    public String updateBranch(@PathVariable Long id, Model model){
        try {
            BranchModel branch = branchService.findById(id);
            model.addAttribute("branch", branch);
            return "branch-form";
        } catch (IdNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("branches", branchService.findAllBranches());
            return "branch";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String removeBranch(@PathVariable Long id, Model model){
        try {
            branchService.removeBranchById(id);
            model.addAttribute("success", "Branch removed successfully");
        } catch (IdNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("branches", branchService.findAllBranches());
        return "branch";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable Long id, Model model){
        try {
            BranchModel branch = branchService.findById(id);
            model.addAttribute("success", "Branch found with id : "+id);
            model.addAttribute("branches", List.of(branch));
        } catch (IdNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("branches", null);
        }
        return "branch";
    }

}
