package com.namsi.Bankapp.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class creditdebitEnquiry {
   private  String accountNumber;
   private  BigDecimal amount  ;  
}
