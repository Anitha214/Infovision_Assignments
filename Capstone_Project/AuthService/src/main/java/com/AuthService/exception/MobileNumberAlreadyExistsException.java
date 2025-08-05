package com.AuthService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class MobileNumberAlreadyExistsException extends Throwable {
    private String message;
}
