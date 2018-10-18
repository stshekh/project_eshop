package com.gmail.sshekh.controllers;


import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/items")
public class UsersAPIController {

    private final UserService userService;
    private final Validator itemValidator;
    private final ItemService itemService;

    @Autowired
    public UsersAPIController(
            UserService userService,
            Validator itemValidator,
            ItemService itemService
    ) {
        this.userService = userService;
        this.itemValidator = itemValidator;
        this.itemService = itemService;
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('API_USER')")
    public ItemDTO createItem(
            @RequestBody ItemDTO item
    ) {
        return itemService.save(item);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('API_USER')")
    public ItemDTO updateItem(
            @RequestBody ItemDTO item,
            @PathVariable(name = "id") Long id
    ) {
        return itemService.update(item);
    }

    @DeleteMapping(value = "/remove/{id}")
    @PreAuthorize("hasAuthority('API_USER')")
    public void removeItem(
            @PathVariable(name = "id") Long id
    ) {
        itemService.remove(id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('API_USER')")
    public void deleteItem(
            @PathVariable(name = "id") Long id
    ) {
        itemService.delete(id);
    }

}
