package com.Swadeshi.bank.validator;

import java.util.List;

public interface DataValidator <T>{
    List<String> validate(T data);
}
