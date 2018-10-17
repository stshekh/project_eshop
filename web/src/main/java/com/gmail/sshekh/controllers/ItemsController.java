package com.gmail.sshekh.controllers;

import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.OrderService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.ItemDTO;
import com.gmail.sshekh.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.sshekh.service.utils.PaginationUtil.ITEMS_PER_PAGE;
import static com.gmail.sshekh.service.utils.PaginationUtil.getNumberOfPages;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final Validator itemValidator;
    private final ItemService itemService;
    private final OrderService orderService;
    private final Validator orderValidator;

    @Autowired
    public ItemsController(
            Validator itemValidator,
            ItemService itemService,
            OrderService orderService,
            Validator orderValidator
    ) {
        this.itemValidator = itemValidator;
        this.itemService = itemService;
        this.orderService = orderService;
        this.orderValidator = orderValidator;
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
        return "items";
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
        return "redirect:/items";
    }

    //Go to item create page
    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getCreatePage(ModelMap modelMap) {
        modelMap.addAttribute("item", new ItemDTO());
        return "items.create";
    }

    //Creating items
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String createItem(
            @ModelAttribute("item") ItemDTO item,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        itemValidator.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            return "items.create";
        } else {
            item = itemService.save(item);
            modelMap.addAttribute("item", item);
            return "redirect:/items";
        }
    }

    //Shows one item page
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getItem(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        ItemDTO item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        return "items.update";
    }

    //Updates item
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String updateItem(
            @PathVariable("id") Long id,
            @ModelAttribute ItemDTO item,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        item.setId(id);
        itemValidator.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            return "items.update";
        } else {
            item = itemService.update(item);
            modelMap.addAttribute("item", item);
            return "redirect:/users";
        }
    }


    @GetMapping(value = "/{id}/orders/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String getOrderCreatePage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        modelMap.addAttribute("order", new OrderDTO());
        modelMap.addAttribute("item", itemService.findOne(id));
        return "orders.create";
    }

    @PostMapping(value = "/{idItem}/orders/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String addItemsToOrder(
            @ModelAttribute("order") OrderDTO order,
            @PathVariable("idItem") Long idItem,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        orderValidator.validate(order, bindingResult);
        if (bindingResult.hasErrors()) {
            return "orders.create";
        } else {
            orderService.save(order, idItem);
            modelMap.addAttribute("order", order);
            return "redirect:/orders/users";
        }
    }
}
