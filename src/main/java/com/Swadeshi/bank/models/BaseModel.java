package com.Swadeshi.bank.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
}
