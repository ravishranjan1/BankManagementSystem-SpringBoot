package com.Swadeshi.bank.validator;

import com.Swadeshi.bank.models.BranchModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        BranchModel branch = (BranchModel) data;
        if(branch == null){
            errors.add("Branch cannot be null");
            return errors;
        }

        if(branch.getBranchName() == null || branch.getBranchName().trim().isEmpty()){
            errors.add("Branch Name is required");
        }else if(branch.getBranchName().length()<3){
            errors.add("Branch name must be at least 3 characters long");
        }

        if (branch.getIfscCode() == null || branch.getIfscCode().trim().isEmpty()) {
            errors.add("IFSC code is required");
        } else if (!branch.getIfscCode().matches("^[A-Z]{4}0[A-Z0-9]{6}$")) {
            errors.add("Invalid IFSC code format");
        }

        if (branch.getCity() == null || branch.getCity().trim().isEmpty()) {
            errors.add("City is required");
        } else if (branch.getCity().length() < 2) {
            errors.add("City name must be at least 2 characters long");
        }

        return errors;
    }
}
