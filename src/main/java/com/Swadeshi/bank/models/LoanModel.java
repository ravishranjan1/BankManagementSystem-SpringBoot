package com.Swadeshi.bank.models;

import com.Swadeshi.bank.enums.LoanStatus;
import com.Swadeshi.bank.enums.LoanType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class LoanModel extends BaseModel{

    private LoanType type;

    private BigDecimal amount;
    private BigDecimal interest;
    private Integer tenureMonth;
    private LoanStatus status;

    @ManyToOne
    private CustomerModel customer;

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getTenureMonth() {
        return tenureMonth;
    }

    public void setTenureMonth(Integer tenureMonth) {
        this.tenureMonth = tenureMonth;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
