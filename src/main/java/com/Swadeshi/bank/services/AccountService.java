package com.Swadeshi.bank.services;

import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.AccountModel;

import java.util.List;

public interface AccountService {
    List<AccountModel> findAllAccounts();
    AccountModel findById(Long id) throws IdNotFoundException;
    void save(AccountModel account) throws Exception;
}
