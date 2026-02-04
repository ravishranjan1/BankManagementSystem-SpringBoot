package com.Swadeshi.bank.services.impl;

import com.Swadeshi.bank.Repository.CustomerRepository;
import com.Swadeshi.bank.models.CustomerModel;
import com.Swadeshi.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }
}
