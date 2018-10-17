package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ItemDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(err, "description", "item.description.empty");
        ValidationUtils.rejectIfEmpty(err, "price", "item.price.empty");
        ItemDTO item = (ItemDTO) obj;
        Pattern pattern = Pattern.compile(
                "^\\d{1,}|\\d{1,}.\\d{1,}",
                Pattern.CASE_INSENSITIVE
        );
        if(!(pattern.matcher(item.getPrice().toString()).matches())){
            err.rejectValue("email","user.email.invalid");
        }
    }
}
