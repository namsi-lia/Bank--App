package com.namsi.Bankapp.controller;

import com.namsi.Bankapp.service.impl.userService;
import org.springframework.web.bind.annotation.RestController;

import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.userRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")



public class userConroller {

    @Autowired
    userService userService;



    @PostMapping
    public bankResponse createAccount(@RequestBody userRequest userRequest) {
		return userService.createAccount(userRequest);
	}

    
    }
    
    

