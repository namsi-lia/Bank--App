package com.namsi.Bankapp.utils;

import java.time.Year;

public class accountUtils {


    public static final String ACCOUNT_EXIST_CODE="100";

    public static final String ACCOUNT_EXIST_MESSAGE ="Account already exist.";

    public static final String ACCOUNT_CREATED_CODE ="200";

    public static final String ACCOUNT_CREATED_MESSAGE= "Your account was  successfully created.";

    public static final String ACCOUNT_NOT_EXIST_CODE ="300";

    public static final String ACCOUNT_NOT_EXISTS_MESSAGE="The following account does not exists";
    
    public static final String ACCOUNT_FOUND_CODE = "400";
    public static final String ACCOUNT_FOUND_SUCCESS = "User Account Found";



    public static final String ACCOUNT_WAS_CREDITED_SUCCESS ="500";
    public static final String ACCOUNT_WAS_CREDITED_SUCCESS_MESSAGE="Dear customer your account was  credited successfully";

    public static final String INSUFFICIENT_FUNDS__BALANCE_CODE = "600";
    public static final String INSUFFICIENT_FUNDS_BALANCE_MESSAGE = "Insufficient Balance";
 

    public static final String ACCOUNT_WAS_DEBITED_SUCCESS ="700";
    public static final String ACCOUNT_WAS_DEBITED_SUCCESS_MESSAGE ="Dear customer your account was debited succesfully";
    
    
    /**
             * create randomsixdigits
             * 
             */
    public static  String  getAccountNumber(){
        Year currentYear = Year.now();
    
        int min =100000 ;
    
        int max=999999; // 6 digit number range
    
        //generate a randon number  between the min and max values
        int  randNumber = (int) Math.floor(Math.random() * (max - min + 1));
    
        //convert the current and randomNumber to strings  so that we can concatenate them together
        String  year =String.valueOf(currentYear);
        String  randomNumber = String.valueOf(randNumber);
    
        StringBuilder accountNumber  = new StringBuilder();
    
    
        return accountNumber.append(year).append(randomNumber).toString();
}
}