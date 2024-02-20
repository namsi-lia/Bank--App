package com.namsi.Bankapp.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.namsi.Bankapp.dto.AccountInfo;
import com.namsi.Bankapp.dto.EmailData;
import com.namsi.Bankapp.dto.Enquiry;
import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.creditdebitEnquiry;
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

    @Override
    public bankResponse balanceEnquiry(Enquiry enquiry) {

        //verify if the account  number is valid in the database

        boolean isAccountExists = userRepository.existsByAccountNumber(enquiry.getAccountNumber());

        if (!isAccountExists) {
            return bankResponse.builder()
            .responseCode(accountUtils.ACCOUNT_NOT_EXIST_CODE)
            .responseMessage(accountUtils.ACCOUNT_NOT_EXISTS_MESSAGE)
            .accountInfo(null)
            .build() ;
            
        }
        User foundUser =userRepository.findByAccountNumber(enquiry.getAccountNumber());

        return bankResponse.builder()
        .responseCode(accountUtils.ACCOUNT_FOUND_CODE)
        .responseMessage(accountUtils.ACCOUNT_FOUND_SUCCESS)
        .accountInfo(AccountInfo.builder()
            .accountBalance(foundUser.getAccountBalance())
            .accountNumber(enquiry.getAccountNumber())
            .accountName(foundUser.getFirstName() + " " + foundUser.getMiddleName() + " " + foundUser.getSurName())
            .build()
         )
        .build();
    }

    @Override
    public String nameEnquiry(Enquiry enquiry) {
      boolean isAcconutExist =userRepository.existsByAccountNumber(enquiry.getAccountNumber());
      if(!isAcconutExist){
        return accountUtils.ACCOUNT_NOT_EXISTS_MESSAGE;
    }
        User foundUser= userRepository.findByAccountNumber(enquiry.getAccountNumber());
        return foundUser.getFirstName()+ " "+foundUser.getMiddleName()+ ""+foundUser.getSurName();

    

}

    @Override
    public bankResponse creditAccount(creditdebitEnquiry enquiry) {

                /// verify if the account exists]
        boolean isAcccountExists=userRepository.existsByAccountNumber(enquiry.getAccountNumber());
        if(!isAcccountExists){
            return  bankResponse.builder()
            .responseCode(accountUtils.ACCOUNT_NOT_EXIST_CODE)
            .responseMessage(accountUtils.ACCOUNT_NOT_EXISTS_MESSAGE)
            .accountInfo(null)
            .build();          
    }

    User userCredit =userRepository.findByAccountNumber(enquiry.getAccountNumber());
    userCredit.setAccountBalance(userCredit.getAccountBalance().add(enquiry.getAmount()));
    userRepository.save(userCredit);

    return bankResponse.builder()
        .responseCode (accountUtils.ACCOUNT_WAS_CREDITED_SUCCESS)
        .responseMessage(accountUtils.ACCOUNT_WAS_CREDITED_SUCCESS_MESSAGE)
        .accountInfo(AccountInfo.builder()
            .accountName(userCredit.getFirstName()+ ""+ userCredit.getMiddleName()+""+userCredit.getSurName())
            .accountBalance(userCredit.getAccountBalance())
            .accountNumber(enquiry.getAccountNumber())
            .build())
            
            .build();

}
}