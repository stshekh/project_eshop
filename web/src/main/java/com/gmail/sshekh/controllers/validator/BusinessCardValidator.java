package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class BusinessCardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessCardDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "title", "user.business.card.empty");
        BusinessCardDTO businessCard = (BusinessCardDTO) obj;
        Pattern pattern = Pattern.compile(
                "^[0-9]{20}",
                Pattern.CASE_INSENSITIVE
        );
        if (!(pattern.matcher(businessCard.getWorkingTelephone()).matches())) {
            err.rejectValue("workingTelephone", "user.business.card.working.telephone.invalid");
        }
    }
}
