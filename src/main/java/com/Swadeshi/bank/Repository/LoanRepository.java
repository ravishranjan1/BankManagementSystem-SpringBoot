package com.Swadeshi.bank.Repository;

import com.Swadeshi.bank.models.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {
}
