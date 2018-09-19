package com.gmail.sshekh.service;

import com.gmail.sshekh.*;
import com.gmail.sshekh.dao.impl.*;
import com.gmail.sshekh.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class UserActionsTests {
    private static final Logger logger = LogManager.getLogger(UserActionsTests.class);
    private UserService userService = new UserServiceImpl();
    private ItemService itemService = new ItemServiceImpl();
    private DiscountService discountService = new DiscountServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private int fromAmount = 100;
    private int toAmount = 500;

    @Test
    public void createTest() {

        ItemDTO[] itemDTO = new ItemDTO[30];
        DiscountDTO discountDTO[] = new DiscountDTO[3];
        Set<ItemDTO> items = new HashSet<>();
        Set<DiscountDTO> discounts = new HashSet<>();


        for (int i = 0; i < 30; i++) {
            itemDTO[i] = new ItemDTO();
            itemDTO[i].setName("Tov" + i);
            itemDTO[i].setUnqueNumber("tov" + i);
            itemDTO[i].setPrice(new BigDecimal(fromAmount + (int) (Math.random() * (toAmount - fromAmount))));
            itemDTO[i].setDescription(i + "sometext" + i);
            itemService.save(itemDTO[i]);
            items.add(itemDTO[i]);
        }

        for (int i = 0; i < 3; i++) {
            discountDTO[i] = new DiscountDTO();
            discountDTO[i].setName("Discount " + (i + 1));
            discountDTO[i].setRate((i + 1) * 10);
            discountDTO[i].setExpTime(LocalDateTime.of(2018, Month.NOVEMBER, 25, 0, 0));
            discountService.save(discountDTO[i]);
            discounts.add(discountDTO[i]);
        }

        itemService.setDiscountsToItems(10, new BigDecimal(200), new BigDecimal(299));

        itemService.setDiscountsToItems(20, new BigDecimal(300), new BigDecimal(399));

        itemService.setDiscountsToItems(30, new BigDecimal(400), new BigDecimal(500));

        Set<ItemDTO> itemDTOS = itemService.showItems(30);
        for (ItemDTO itemDto : itemDTOS) {
            logger.info(itemDto.getName() + itemDto.getDescription());
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName("admin");


        UserDTO userDTO = new UserDTO();
        userDTO.setName("User1");
        userDTO.setSurname("UserSur");
        userDTO.setEmail("user@user");
        userDTO.setPassword("user");
        userDTO.setRole(roleDTO);
        roleDTO = roleService.save(roleDTO);
        userDTO = userService.save(userDTO);
        logger.info(roleDTO.getRoleName());
        userService.setDiscount(userDTO);
        logger.info(userDTO.getDiscountDTO().getName());
        for (int i = 0; i < 4; i++) {
            orderService.addOrderUsingSum(new BigDecimal(250), new BigDecimal(450), userDTO);
        }
        userService.setOrders(userDTO);
        //TODO Create method that adds orders to user
        for (int i = 0; i < userDTO.getOrders().size(); i++) {
            logger.info(userDTO.getName() + " " + userDTO.getOrders().get(i).getItem().getName() + " " + userDTO.getOrders().get(i).getQuantity());
        }
    }
}
