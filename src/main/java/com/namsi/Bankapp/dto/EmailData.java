package com.namsi.Bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmailData {


    private String recipient;
    private String subject;
    private String messageBody;
    private String attachment;

    
}
