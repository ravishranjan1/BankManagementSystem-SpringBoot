package com.Swadeshi.bank.validator;

import com.Swadeshi.bank.models.CustomerModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerValidator implements DataValidator{

    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<String>();
        CustomerModel customer = (CustomerModel) data;

        if(customer == null){
            errors.add("Customer cannot be null");
            return errors;
        }

        if(customer.getName() == null || customer.getName().trim().isEmpty()){
            errors.add("Name must be required");
        }else if(customer.getName().length()<2 || customer.getName().length()>50){
            errors.add("Name must be between 2 and 50 characters");
        }

        if(customer.getEmail() == null || customer.getEmail().trim().isEmpty()){
            errors.add("Email must be required");
        }else if(!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            errors.add("Invalid email format");
        }

        if(customer.getPhone() == null){
            errors.add("Phone must be required");
        }
        else if(String.valueOf(customer.getPhone()).length() != 10){
            errors.add("Phone number must be 10 digits");
        }

        if(customer.getAddress() == null || customer.getAddress().trim().isEmpty()){
            errors.add("Address must be required");
        }

        if(customer.getDob() == null){
            errors.add("Date of Birth must be required");
        }
        else {
            LocalDate today = LocalDate.now();
            LocalDate minDate = today.minusYears(18);
            if (customer.getDob().isAfter(minDate)){
                errors.add("Age must be at least 18 years");
            }
        }
        return errors;
    }
}
