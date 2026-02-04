package com.Swadeshi.bank.Repository;

import com.Swadeshi.bank.models.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchModel, Long> {
}
