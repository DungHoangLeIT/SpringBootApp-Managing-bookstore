package com.example.employee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    @NotNull
    private String username;

    private String email;
    @NotNull
    private String mobile;

//  private String role;

    @NotNull
    private String password;
}
