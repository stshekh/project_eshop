package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    private final UserService userService;
    private final Validator userValidator;

    @Autowired
    public LoginController(
            UserService userService,
            Validator userValidator
    ) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return "users.register";
    }

    @PostMapping("/register")
    public String createUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return "users.register";
        } else {
            userService.save(user);
            return "redirect:/login";
        }
    }

}
