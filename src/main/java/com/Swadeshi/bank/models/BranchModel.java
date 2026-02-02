package com.Swadeshi.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class BranchModel extends BaseModel{

    private String branchName;

    @Column(unique = true)
    private String ifscCode;
    private String city;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
