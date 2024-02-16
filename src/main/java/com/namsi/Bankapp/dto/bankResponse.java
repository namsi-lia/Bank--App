package com.namsi.Bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class bankResponse {

    private String responseCode;
    private String responseMessage;

    private AccountInfo accountInfo;
}
