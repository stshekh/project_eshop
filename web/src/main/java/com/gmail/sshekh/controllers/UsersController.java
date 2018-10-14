package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.service.*;
import com.gmail.sshekh.service.dto.*;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator userValidator;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final Validator businessCardValidator;
    private final BusinessCardService businessCardService;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            Validator userValidator,
            UserRoleService userRoleService,
            RoleService roleService,
            ProfileService profileService,
            Validator businessCardValidator,
            BusinessCardService businessCardService
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.profileService = profileService;
        this.businessCardValidator = businessCardValidator;
        this.businessCardService = businessCardService;
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
    @PreAuthorize("hasAnyAuthority('VIEW_USERS','VIEW_PROFILE')")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserUpdatePagePath();
    }

    //Updates user
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('VIEW_USERS','VIEW_PROFILE')")
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
            user = userService.update(user);
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
    @PostMapping(value = "/create")
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

    @GetMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String createProfilePage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long id = userPrincipal.getId();
        if (profileService.findProfileById(id) != null) {
            return "redirect:/users/profile/update";
        }
        ProfileDTO profile = new ProfileDTO();
        profile.setUserId(userPrincipal.getId());
        modelMap.addAttribute("profile", profile);
        return pageProperties.getProfileCreatePagePath();
    }

    //Creates users profile
    @PostMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String createProfile(
            @ModelAttribute ProfileDTO profile,
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        profile.setUserId(userPrincipal.getId());
        profile = profileService.save(profile);
        modelMap.addAttribute("profile", profile);
        return "redirect:/users";

    }

    @GetMapping(value = "/profile/update")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String updateProfilePage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long id = userPrincipal.getId();
        UserDTO user = userService.findUserById(id);
        ProfileDTO profile = profileService.findProfileById(id);
        modelMap.addAttribute("profile", profile);
        modelMap.addAttribute("user", user);
        return pageProperties.getProfilePagePath();
    }

    //Updates users profile
    @PostMapping(value = "/profile/update")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String updateProfile(
            @ModelAttribute ProfileDTO profile,
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO user = userService.findUserById(userPrincipal.getId());
        profile.setUserId(userPrincipal.getId());
        profile = profileService.update(profile);
        user = userService.update(user);
        modelMap.addAttribute("profile", profile);
        modelMap.addAttribute("user", user);
        return "redirect:/users";
    }

    @GetMapping(value = "/businessCard")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String getBusinessCards(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long id = userPrincipal.getId();
        List<BusinessCardDTO> businessCards = businessCardService.getBusinessCardsByUserId(id);
        modelMap.addAttribute("businessCards", businessCards);
        return pageProperties.getUsersBusinessCards();
    }

    @GetMapping(value = "/businessCard/create")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE', 'VIEW_USERS')")
    public String getBusinessCardPage(ModelMap modelMap) {
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return pageProperties.getUsersCreateBusinessCard();
    }

    //Creating new user
    @PostMapping(value = "/businessCard/create")
    public String createUsersBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            BindingResult result,
            ModelMap modelMap
    ) {
        businessCardValidator.validate(businessCard, result);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getUsersCreateBusinessCard();
        } else {
            businessCardService.save(businessCard, userPrincipal.getId());
            return "redirect:/users/businessCard";
        }
    }
}
