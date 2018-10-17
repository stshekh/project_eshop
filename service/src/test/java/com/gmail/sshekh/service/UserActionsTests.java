package com.gmail.sshekh.service;

import com.gmail.sshekh.service.impl.*;
import com.gmail.sshekh.service.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;
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
    private Random random = new Random();

    @Test
    public void createTest() {


        //Создаем товары
        for (int i = 1; i <= 30; i++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName("Tov" + i);
            itemDTO.setUniqueNumber("tov" + i);
            itemDTO.setPrice(BigDecimal.valueOf(fromAmount + random.nextInt(toAmount - fromAmount + 1)));
            itemDTO.setDescription(i + "sometext" + i);
            itemService.save(itemDTO);
        }
        //Создаем скидки
        for (int i = 1; i <= 3; i++) {
            DiscountDTO discountDTO = new DiscountDTO();
            discountDTO.setName("Discount " + i);
            discountDTO.setRate(BigDecimal.valueOf(i * 10));
            discountDTO.setExpTime(LocalDateTime.of(2018, Month.NOVEMBER, 25, 0, 0));
            discountService.save(discountDTO);
        }


        //Присваиваем товарам скидку
        itemService.setDiscountsToItems(BigDecimal.valueOf(10), new BigDecimal(200), new BigDecimal(299));

        itemService.setDiscountsToItems(BigDecimal.valueOf(20), new BigDecimal(300), new BigDecimal(399));

        itemService.setDiscountsToItems(BigDecimal.valueOf(30), new BigDecimal(400), new BigDecimal(500));


        //Вывести товары в зависимости от скидки
        Set<ItemDTO> itemDTOS = itemService.showItems(BigDecimal.valueOf(30));
        for (ItemDTO itemDto : itemDTOS) {
            logger.info(itemDto.getName() + itemDto.getDescription());
        }

        //Создаем новые роли и пользователя
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

        //Присвоить произвольную скидку Пользователю
        userService.setDiscount(userDTO);
        logger.info(userDTO.getDiscountDTO().getName());


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreated(LocalDateTime.now());
        orderDTO.setUser(userDTO);
        orderService.save(orderDTO);

        for (int i = 0; i < 4; i++) {
            orderService.addOrdersToItemsAndUsers(new BigDecimal(250), new BigDecimal(450), orderDTO);
        }
        userDTO=userService.findUserByEmail("user@user");
        for (int i = 0; i < userDTO.getOrders().size(); i++) {
            logger.info(userDTO.getName() + " " + userDTO.getOrders().get(i).getItem().getName() + " " + userDTO.getOrders().get(i).getQuantity());
        }
    }

    @Test
    public void userRoleCreateTest() {
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

        logger.debug(userDTO);
        logger.debug(roleDTO);
    }
}
