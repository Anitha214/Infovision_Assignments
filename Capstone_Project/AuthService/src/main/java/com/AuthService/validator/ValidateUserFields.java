package com.AuthService.validator;


import com.AuthService.dto.SignUpRequestDTO;
import com.AuthService.exception.MissingFieldException;
import com.AuthService.utility.Constants;
import org.springframework.util.StringUtils;

public class ValidateUserFields {

    public void validateSignUpFields(SignUpRequestDTO signUpRequestDTO) throws MissingFieldException {

        if (!StringUtils.hasText(signUpRequestDTO.getFirstName()))
            throw new MissingFieldException(Constants.FIRSTNAME_MANDATORY);

        if (!StringUtils.hasText(signUpRequestDTO.getLastName()))
            throw new MissingFieldException(Constants.LASTNAME_MANDATORY);

        if (!StringUtils.hasText(signUpRequestDTO.getMobile()))
            throw new MissingFieldException(Constants.PHONE_NUMBER_MANDATORY);

        if (!StringUtils.hasText(signUpRequestDTO.getEmailId()))
            throw new MissingFieldException(Constants.EMAIL_ID_MANDATORY);

        if (!StringUtils.hasText(signUpRequestDTO.getPassword()))
            throw new MissingFieldException(Constants.PASSWORD_MANDATORY);

        if (!StringUtils.hasText(signUpRequestDTO.getUserType()))
            throw new MissingFieldException(Constants.USER_TYPE_MANDATORY);

    }
}