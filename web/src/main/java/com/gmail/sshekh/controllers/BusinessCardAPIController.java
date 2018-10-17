package com.gmail.sshekh.controllers;

import com.gmail.sshekh.service.BusinessCardService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.BusinessCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class BusinessCardAPIController {

    private final UserService userService;
    private final Validator businessCardValidator;
    private final BusinessCardService businessCardService;

    @Autowired
    public BusinessCardAPIController(
            UserService userService,
            Validator businessCardValidator,
            BusinessCardService businessCardService
    ) {
        this.userService = userService;
        this.businessCardValidator = businessCardValidator;
        this.businessCardService = businessCardService;
    }


    @GetMapping(value = "/{id}/businessCards")
    public List<BusinessCardDTO> getUsersBusinessCards(@PathVariable(name = "id") Long id) {
        return businessCardService.getUsersBusinessCards(id);
    }

    @DeleteMapping(value = "/{id}/businessCard/{bId}")
    public void deleteBusinessCardDTO(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "bId") Long bId
    ) {
        businessCardService.deleteBusinessCardById(bId);
    }
}
