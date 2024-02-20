package com.namsi.Bankapp.service.impl;

import com.namsi.Bankapp.dto.EmailData;

public interface EmailService {

    void sendmailAlert(EmailData emailData);
    
}
