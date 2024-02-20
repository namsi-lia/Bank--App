package com.namsi.Bankapp.controller;

import com.namsi.Bankapp.service.impl.userService;
import org.springframework.web.bind.annotation.RestController;

import com.namsi.Bankapp.dto.Enquiry;
import com.namsi.Bankapp.dto.bankResponse;
import com.namsi.Bankapp.dto.creditdebitEnquiry;
import com.namsi.Bankapp.dto.userRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/users")



public class userConroller {

    @Autowired
    userService userService;



    @PostMapping
    public bankResponse createAccount(@RequestBody userRequest userRequest) {
		return userService.createAccount(userRequest);
	}

  @GetMapping("/balanceEnquiry")
  public bankResponse balanceEnquiry(@RequestBody Enquiry enquiry) {
      return userService.balanceEnquiry(enquiry);
  }

@GetMapping("/nameEnquiry")
public String nameEnquiry(@RequestBody Enquiry enquiry) {

    return userService.nameEnquiry(enquiry);
}


@PostMapping("/credit")
public bankResponse creditAccount(@RequestBody creditdebitEnquiry enquiry) {
  return userService.creditAccount(enquiry);
}



  } 
    

