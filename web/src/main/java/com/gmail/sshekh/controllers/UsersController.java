package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.ProfileService;
import com.gmail.sshekh.service.RoleService;
import com.gmail.sshekh.service.UserRoleService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.ProfileDTO;
import com.gmail.sshekh.service.dto.RoleDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.service.dto.UserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final ProfileService profileService;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            Validator userValidator,
            UserRoleService userRoleService,
            RoleService roleService,
            ProfileService profileService
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.profileService = profileService;
    }

    //Shows all the users on page
    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    //Show user with set filter
    @GetMapping("/filter")
    public String getUser(
            @RequestParam(value = "email", defaultValue = "user@user.com") String email,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findUserByEmail(email);
        modelMap.addAttribute("user", user);
        return pageProperties.getUsersPagePath();
    }

    //Shows one user page
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserUpdatePagePath();
    }

    //Updates user
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('VIEW_USERS')")
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


    //Updates user role
    @GetMapping(value = "/roles/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsersRole(@PathVariable("id") Long id, ModelMap modelMap) {
        UserRoleDTO user = userRoleService.getUsersRole(id);
        List<RoleDTO> roles = roleService.findAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserRoleUpdatePage();
    }

    //Updates user role
    @PostMapping(value = "/roles/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String updateUsersRole(
            @PathVariable("id") Long id,
            @ModelAttribute UserRoleDTO user,
            ModelMap modelMap
    ) {
        user.setUserId(id);
        userRoleService.changeRole(user);
        modelMap.addAttribute("user", user);
        return "redirect:/users/roles/{id}";

    }

    //Updates user enable status
    @GetMapping(value = "/enabled/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsersStatus(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserEnableUpdatePage();
    }


    //Updates user enable status
    @PostMapping(value = "/enabled/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String updateUsersStatus(
            @PathVariable("id") Long id,
            @ModelAttribute UserDTO user,
            ModelMap modelMap
    ) {
        user.setId(id);
        userService.setEnabled(user);
        modelMap.addAttribute("user", user);
        return "redirect:/users/enabled/{id}";

    }

    //Moves to the create-page of the user
    @GetMapping(value = "/create")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getUserCreatePagePath();
    }

    //Creating new user
    @PostMapping
    public String createUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserCreatePagePath();
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String deleteUser(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            userService.delete(id);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/profile")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String createProfilePage(@PathVariable("id") Long id, ModelMap modelMap) {
        ProfileDTO profile = new ProfileDTO();
        profile.setUserId(id);
        modelMap.addAttribute("profile", profile);
        return pageProperties.getProfileCreatePagePath();
    }

    //Creates users profile
    @PostMapping(value = "/{id}/profile")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String createProfile(
            @PathVariable("id") Long id,
            @ModelAttribute ProfileDTO profile,
            ModelMap modelMap
    ) {
        profile.setUserId(id);
        profile = profileService.save(profile);
        modelMap.addAttribute("profile", profile);
        return "redirect:/users";

    }

    @GetMapping(value = "/{id}/profile/update")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String updateProfilePage(@PathVariable("id") Long id, ModelMap modelMap) {
        ProfileDTO profile = profileService.findProfileById(id);
        modelMap.addAttribute("profile", profile);
        return pageProperties.getProfilePagePath();
    }

    //Updates users profile
    @PostMapping(value = "/{id}/profile/update")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String updateProfile(
            @PathVariable("id") Long id,
            @ModelAttribute ProfileDTO profile,
            ModelMap modelMap
    ) {
        profile.setUserId(id);
        profile = profileService.update(profile);
        modelMap.addAttribute("profile", profile);
        return "redirect:/users";

    }
}
