package com.Swadeshi.bank.services.impl;

import com.Swadeshi.bank.Repository.CustomerRepository;
import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.CustomerModel;
import com.Swadeshi.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(CustomerModel customer) throws Exception {
        try{
            if(customer.getId()==null){
                customerRepository.save(customer);
            }else{
                    CustomerModel updateCustomer = findById(customer.getId());
                    updateCustomer.setName(customer.getName());
                    updateCustomer.setPhone(customer.getPhone());
                    updateCustomer.setEmail(customer.getEmail());
                    updateCustomer.setAddress(customer.getAddress());
                    updateCustomer.setDob(customer.getDob());
                    customerRepository.save(updateCustomer);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CustomerModel findById(Long id) throws IdNotFoundException {
        Optional<CustomerModel> opt = customerRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new IdNotFoundException("Customer not found with id : "+id);
        }
    }

    @Override
    public void removeCustomer(Long id) throws IdNotFoundException {
        CustomerModel customer = findById(id);
        customerRepository.delete(customer);
    }


}
