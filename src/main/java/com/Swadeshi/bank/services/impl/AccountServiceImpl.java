package com.Swadeshi.bank.services.impl;

import com.Swadeshi.bank.Repository.AccountRepository;
import com.Swadeshi.bank.enums.AccountStatus;
import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.AccountModel;
import com.Swadeshi.bank.models.BranchModel;
import com.Swadeshi.bank.models.CustomerModel;
import com.Swadeshi.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountModel> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountModel findById(Long id) throws IdNotFoundException {
        Optional<AccountModel> opt = accountRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            throw new IdNotFoundException("Id not matched by any previous account id");
    }

    @Override
    public void save(AccountModel account) throws Exception {
        try{
            CustomerModel customer = account.getCustomer();
            BranchModel branch = account.getBranch();
            if(account.getId() == null){
                account.setStatus(AccountStatus.ACTIVE);
                account.setOpenDate(LocalDate.now());
                account.setBranch(branch);
                account.setCustomer(customer);
                accountRepository.save(account);
            }else{
                AccountModel updateAccount = findById(account.getId());
                updateAccount.setAccountNumber(account.getAccountNumber());
                updateAccount.setAmount(account.getAmount());
                updateAccount.setBranch(branch);
                updateAccount.setCustomer(customer);
                updateAccount.setStatus(account.getStatus());
                updateAccount.setType(account.getType());
                updateAccount.setOpenDate(account.getOpenDate());
                accountRepository.save(updateAccount);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
