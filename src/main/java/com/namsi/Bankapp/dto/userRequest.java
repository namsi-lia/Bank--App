package com.namsi.Bankapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userRequest {
    private Long Id;
    private String firstName;
    private String middleName;
    private String surName;
    private String gender;
    private String stateofOrigin;
    private String email;
    private String accountNumber;
    private String phoneNumber;
    private String altphoneNumber;
    private String address;
}
