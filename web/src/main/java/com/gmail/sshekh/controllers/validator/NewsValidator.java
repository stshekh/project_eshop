package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.NewsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "title", "item.title.empty");
        ValidationUtils.rejectIfEmpty(err, "content", "news.content.empty");
    }
}
