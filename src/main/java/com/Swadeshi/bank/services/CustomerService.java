package com.Swadeshi.bank.services;

import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.CustomerModel;

import java.util.List;

public interface CustomerService {

    List<CustomerModel> findAll();
    void saveCustomer(CustomerModel customer) throws Exception;
    CustomerModel findById(Long id) throws IdNotFoundException;
    void removeCustomer(Long id) throws IdNotFoundException;
}
