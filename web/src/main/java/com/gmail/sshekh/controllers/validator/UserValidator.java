package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(err, "email", "user.email.empty");
        UserDTO user = (UserDTO) obj;
        Pattern pattern = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}",
                Pattern.CASE_INSENSITIVE
        );
        if(!(pattern.matcher(user.getEmail()).matches())){
            err.rejectValue("email","user.email.invalid");
        }
    }
}
