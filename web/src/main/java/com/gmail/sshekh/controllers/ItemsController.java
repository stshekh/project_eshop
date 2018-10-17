package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.sshekh.service.utils.PaginationUtil.ITEMS_PER_PAGE;
import static com.gmail.sshekh.service.utils.PaginationUtil.getNumberOfPages;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemsController(
            PageProperties pageProperties,
            ItemService itemService,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.userService = userService;
    }

    //ShowAllItems
    @GetMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS','VIEW_PROFILE')")
    public String getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(itemService.countAllItems(), ITEMS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        List<ItemDTO> items = itemService.findAll(page, ITEMS_PER_PAGE);
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    //Removing items
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String deleteItems(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            itemService.remove(id);
        }
        return "redirect:/news";
    }
}
