package com.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class SignUpRequestDTO {
    private String lastName;
    private String firstName;
    private String emailId;
    private String mobile;
    private String userType;
    private String password;
}