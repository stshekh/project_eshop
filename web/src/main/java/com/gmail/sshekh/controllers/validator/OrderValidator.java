package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.OrderDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "quantity", "order.quantity.empty");
        OrderDTO order = (OrderDTO) obj;
        Pattern pattern = Pattern.compile(
                "^[0-9]{1,}",
                Pattern.CASE_INSENSITIVE
        );
        if (!(pattern.matcher(order.getQuantity().toString()).matches())) {
            err.rejectValue("quantity", "order.quantity.invalid");
        }
    }
}
