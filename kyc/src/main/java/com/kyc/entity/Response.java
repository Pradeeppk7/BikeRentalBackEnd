package com.kyc.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

//import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    
    private Kyc kyc;
    private List<Kyc> list;
}

