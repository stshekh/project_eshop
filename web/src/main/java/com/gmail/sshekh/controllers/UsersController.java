package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.RoleDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator userValidator;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            Validator userValidator
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserPagePath();
    }

    @GetMapping("/filter")
    public String getUser(
            @RequestParam(value = "email", defaultValue = "user@user.com") String email,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findUserByEmail(email);
        modelMap.addAttribute("user", user);
        return pageProperties.getUsersPagePath();
    }

    @PostMapping(value = "/{id}")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute UserDTO user,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        user.setId(id);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.update(user);//TODO if-else in converter on RoleDto
            modelMap.addAttribute("user", user);
            return "redirect:/users";
        }

    }
}
