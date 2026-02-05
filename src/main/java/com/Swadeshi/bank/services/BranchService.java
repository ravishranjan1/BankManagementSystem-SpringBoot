package com.Swadeshi.bank.services;

import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.BranchModel;

import java.util.List;

public interface BranchService {
    List<BranchModel> findAllBranches();
    void saveBranch(BranchModel branch) throws Exception;
    BranchModel findById(Long id) throws IdNotFoundException;
    void removeBranchById(Long id) throws IdNotFoundException;
}
