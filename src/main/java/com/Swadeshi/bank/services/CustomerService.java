package com.Swadeshi.bank.services;

import com.Swadeshi.bank.models.CustomerModel;

import java.util.List;

public interface CustomerService {

    List<CustomerModel> findAll();
}
