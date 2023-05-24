package com.example.employee.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class JwtResponse {

    private String accessToken;

    private String httpStatusCode;

    private String message;

    private String username;

    private String email;

    List<String> roles;
    private boolean success;

//    public JwtResponse(String format, String valueOf, List<String> b) {
//
//        this.accessToken= format;
//        this.httpStatusCode=valueOf;
//        this.success= b;
//    }



    public JwtResponse(String jwt,  String username, List<String> roles) {
        this.accessToken = jwt;
        this.username = username;
        this.roles = roles;
    }


}
