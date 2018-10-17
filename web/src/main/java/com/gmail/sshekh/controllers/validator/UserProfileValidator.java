package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "address", "user.address.empty");
        ValidationUtils.rejectIfEmpty(err, "telephone", "user.telephone.empty");
        ProfileDTO profile = (ProfileDTO) obj;
        Pattern pattern = Pattern.compile(
                "^((8|\\+375)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
                Pattern.CASE_INSENSITIVE
        );
        if (!(pattern.matcher(profile.getTelephone()).matches())) {
            err.rejectValue("telephone", "users.telephone.invalid");
        }
    }
}
