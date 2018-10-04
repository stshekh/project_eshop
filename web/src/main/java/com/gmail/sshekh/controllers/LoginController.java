package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator userValidator;

    @Autowired
    public LoginController(
            PageProperties pageProperties,
            UserService userService,
            Validator userValidator
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getLoginPage() {
        return pageProperties.getLoginPagePath();
    }

}
