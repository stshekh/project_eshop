package com.gmail.sshekh.controllers.validator;

import com.gmail.sshekh.service.dto.CommentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "content", "comment.content.empty");
    }
}