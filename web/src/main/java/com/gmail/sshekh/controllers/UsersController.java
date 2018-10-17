package com.gmail.sshekh.controllers;

import com.gmail.sshekh.service.*;
import com.gmail.sshekh.service.dto.*;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.sshekh.service.utils.PaginationUtil.USERS_PER_PAGE;
import static com.gmail.sshekh.service.utils.PaginationUtil.getNumberOfPages;
import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;


@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final Validator userValidator;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final Validator businessCardValidator;
    private final BusinessCardService businessCardService;

    @Autowired
    public UsersController(
            UserService userService,
            Validator userValidator,
            UserRoleService userRoleService,
            RoleService roleService,
            ProfileService profileService,
            Validator businessCardValidator,
            BusinessCardService businessCardService
    ) {
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
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(userService.countUsers(), USERS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        List<UserDTO> users = userService.findAll(page, USERS_PER_PAGE);
        modelMap.addAttribute("users", users);
        return "users";
    }

    //Shows one user page
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('VIEW_USERS','VIEW_PROFILE')")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        return "users.update";
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
            return "users";
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
        modelMap.addAttribute("user", user);
        List<RoleDTO> roles = roleService.findAll();
        modelMap.addAttribute("roles", roles);
        return "users.role.update";
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
        return "users.enable.update";
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
        return "users.create";
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
            return "users.create";
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
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String createProfilePage(ModelMap modelMap) {
        UserPrincipal userPrincipal = getLoggedInUser();
        Long id = userPrincipal.getId();
        if (profileService.findProfileById(id) != null) {
            return "redirect:/users/profile/update";
        }
        ProfileDTO profile = new ProfileDTO();
        profile.setUserId(userPrincipal.getId());
        modelMap.addAttribute("profile", profile);
        return "users.profile.create";
    }

    //Creates users profile
    @PostMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String createProfile(
            @ModelAttribute ProfileDTO profile,
            ModelMap modelMap
    ) {

        profile = profileService.save(profile);
        modelMap.addAttribute("profile", profile);
        return "redirect:/items";

    }

    @GetMapping(value = "/profile/update")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String updateProfilePage(ModelMap modelMap) {
        UserPrincipal userPrincipal = getLoggedInUser();
        Long id = userPrincipal.getId();
        UserDTO user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        ProfileDTO profile = profileService.findProfileById(id);
        modelMap.addAttribute("profile", profile);
        return "users.profile.update";
    }

    //Updates users profile
    @PostMapping(value = "/profile/update")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String updateProfile(
            @ModelAttribute ProfileDTO profile,
            @ModelAttribute UserDTO user,
            ModelMap modelMap
    ) {
        //TODO create validators
        profile = profileService.update(profile);
        modelMap.addAttribute("profile", profile);
        UserPrincipal userPrincipal = getLoggedInUser();
        user.setId(userPrincipal.getId());
        user = userService.update(user);
        modelMap.addAttribute("user", user);
        return "redirect:/items";
    }

    @GetMapping(value = "/businessCard")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String getBusinessCards(ModelMap modelMap) {
        List<BusinessCardDTO> businessCards = businessCardService.getUsersBusinessCards();
        modelMap.addAttribute("businessCards", businessCards);
        return "users.business.cards";
    }

    @GetMapping(value = "/businessCard/create")
    @PreAuthorize("hasAnyAuthority('VIEW_PROFILE')")
    public String getBusinessCardPage(ModelMap modelMap) {
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return "users.business.cards.create";
    }

    //Creating new user
    @PostMapping(value = "/businessCard/create")
    public String createUsersBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            BindingResult result,
            ModelMap modelMap
    ) {
        businessCardValidator.validate(businessCard, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return "users.business.cards.create";
        } else {
            businessCardService.save(businessCard);
            return "redirect:/users/businessCard";
        }
    }
}
