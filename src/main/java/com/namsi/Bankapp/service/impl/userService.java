package com.namsi.Bankapp.service.impl;

import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.userRequest;

public interface userService{
    bankResponse createAccount(userRequest userRequest);


}
