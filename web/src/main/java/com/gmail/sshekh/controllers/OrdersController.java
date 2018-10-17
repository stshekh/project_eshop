package com.gmail.sshekh.controllers;

import com.gmail.sshekh.service.OrderService;
import com.gmail.sshekh.service.dto.OrderDTO;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.sshekh.service.utils.PaginationUtil.ITEMS_PER_PAGE;
import static com.gmail.sshekh.service.utils.PaginationUtil.getNumberOfPages;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    public OrdersController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getAlOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(orderService.countAllOrders(), ITEMS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        List<OrderDTO> orders = orderService.findAll(page, ITEMS_PER_PAGE);
        modelMap.addAttribute("orders", orders);
        return "orders";
    }

    //Updates user enable status
    @GetMapping(value = "/{idUser}/{idItem}/status")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getOrderStatus(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idItem") Long idItem,
            ModelMap modelMap
    ) {
        OrderDTO order = orderService.getOrderByOrderId(idUser, idItem);
        modelMap.addAttribute("order", order);
        return "orders.status.update";
    }


    //Updates user enable status
    @PostMapping(value = "/{idUser}/{idItem}/status")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String updateOrderStatus(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idItem") Long idItem,
            @ModelAttribute OrderDTO order,
            ModelMap modelMap
    ) {
        orderService.updateStatus(order, idUser, idItem);
        modelMap.addAttribute("order", order);
        return "redirect:/orders";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String getUsersOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,

            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(orderService.countAllOrders(), ITEMS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        List<OrderDTO> orders = orderService.getUsersOrders(page, ITEMS_PER_PAGE);
        modelMap.addAttribute("orders", orders);
        return "orders";
    }
}
