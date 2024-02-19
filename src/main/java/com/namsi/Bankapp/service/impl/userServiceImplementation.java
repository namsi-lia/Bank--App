package com.namsi.Bankapp.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.namsi.Bankapp.dto.AccountInfo;
import com.namsi.Bankapp.dto.EmailData;
import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.userRequest;
import com.namsi.Bankapp.entity.User;
import com.namsi.Bankapp.repository.UserRepository;
import com.namsi.Bankapp.utils.accountUtils;


@Service
public class userServiceImplementation implements userService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public bankResponse createAccount(userRequest userRequest) {
        /**
         * create  an account - saving the new user into the db
         * check if user has an existing account
         */

        if  (userRepository.existsByEmail(userRequest.getEmail())){
            return bankResponse.builder()
            .responseCode(accountUtils.ACCOUNT_EXIST_CODE)
            .responseMessage(accountUtils.ACCOUNT_EXIST_MESSAGE)
            .accountInfo(null)

            .build();
        }

        
        User newuser  = User.builder()
                .firstName(userRequest.getFirstName())
                .middleName(userRequest.getMiddleName())
                .surName(userRequest.getSurName())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .stateofOrigin(userRequest.getStateofOrigin())
                .accountNumber(accountUtils.getAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .altphoneNumber(userRequest.getAltphoneNumber())
                .status("ACTIVE")
                .build();

                 User savedUser =userRepository.save(newuser);

                 EmailData emailData = EmailData.builder()
                 .recipient(savedUser.getEmail())
                 .subject("Account Creation")
                 .messageBody("Well Done !! Your account has been successfully created. \n Account details: " +
                 "Account Name: " + savedUser.getFirstName() + " " + savedUser.getMiddleName() + " " + savedUser.getSurName() + "\nAccount Number: " + savedUser.getAccountNumber())

                 .build();

                 emailService.sendmailAlert(emailData);

                 return bankResponse.builder()
				.responseCode(accountUtils.ACCOUNT_CREATED_CODE)
				.responseMessage(accountUtils.ACCOUNT_CREATED_MESSAGE)
				.accountInfo(AccountInfo.builder()
						.accountBalance(savedUser.getAccountBalance())
						.accountNumber(savedUser.getAccountNumber())
						.accountName(savedUser.getFirstName() + " " + savedUser.getMiddleName() + " " + savedUser.getSurName())
						.build())
				.build();
		
    }
}
