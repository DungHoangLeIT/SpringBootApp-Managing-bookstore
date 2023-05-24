package com.example.employee.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String mobile;
    private List<Long> roleIds;
    private String verificationCode;
    private boolean enabled;


}
