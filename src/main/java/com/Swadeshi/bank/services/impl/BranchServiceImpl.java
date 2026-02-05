package com.Swadeshi.bank.services.impl;

import com.Swadeshi.bank.Repository.BranchRepository;
import com.Swadeshi.bank.exception.IdNotFoundException;
import com.Swadeshi.bank.models.BranchModel;
import com.Swadeshi.bank.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchModel> findAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public BranchModel findById(Long id) throws IdNotFoundException {
        Optional<BranchModel> opt = branchRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new IdNotFoundException("No Branch found with Id : "+id);
        }
    }

    @Override
    public void saveBranch(BranchModel branch) throws Exception {
        try{
            if(branch.getId() == null){
                branchRepository.save(branch);
            }else{
                BranchModel updateBranch = findById(branch.getId());
                updateBranch.setBranchName(branch.getBranchName());
                updateBranch.setIfscCode(branch.getIfscCode());
                updateBranch.setCity(branch.getCity());
                branchRepository.save(updateBranch);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeBranchById(Long id) throws IdNotFoundException {
        BranchModel branch = findById(id);
        branchRepository.delete(branch);
    }

}
