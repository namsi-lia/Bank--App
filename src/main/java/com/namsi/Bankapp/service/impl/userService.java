package com.namsi.Bankapp.service.impl;

import com.namsi.Bankapp.dto.Enquiry;
import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.creditdebitEnquiry;
import com.namsi.Bankapp.dto.userRequest;

public interface userService{
    bankResponse createAccount(userRequest userRequest);

    bankResponse balanceEnquiry(Enquiry enquiry);

    String nameEnquiry (Enquiry enquiry);

    bankResponse creditAccount(creditdebitEnquiry enquiry);


}
