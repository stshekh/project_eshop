package com.gmail.sshekh.service;

import com.gmail.sshekh.ItemService;
import com.gmail.sshekh.dao.impl.*;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dto.*;
import com.sun.corba.se.impl.ior.IORTemplateImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class UserActionsTests {
    private static final Logger logger = LogManager.getLogger(UserActionsTests.class);
    ItemService itemService = new ItemServiceImpl();


    @Test
    public void createTest() {
        int fromAmount = 100;
        int toAmount = 500;
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
            new DiscountServiceImpl().save(discountDTO[i]);
            discounts.add(discountDTO[i]);
        }

        ItemService itemService = new ItemServiceImpl();
        itemService.setDiscountsToItems(10, new BigDecimal(200), new BigDecimal(299));

        itemService.setDiscountsToItems(20, new BigDecimal(300), new BigDecimal(399));

        itemService.setDiscountsToItems(30, new BigDecimal(400), new BigDecimal(500));

        Set<ItemDTO> itemDTOS = new ItemServiceImpl().showItems(30);
        for (ItemDTO itemDto : itemDTOS) {
            logger.info(itemDto.getName() + itemDto.getDescription());
        }


        ProfileDTO profileDTO = new ProfileDTO();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName("admin");


        UserDTO userDTO = new UserDTO();
        userDTO.setName("User1");
        userDTO.setSurname("UserSur");
        userDTO.setEmail("user@user");
        userDTO.setPassword("user");
        userDTO.setDiscountDTO(discountDTO[0 + (int) (Math.random() * 2)]);
        userDTO.setRole(roleDTO);
        profileDTO.setAddress("aaa");
        profileDTO.setTelephone("9900099");
        profileDTO.setUserId(userDTO.getId());
        profileDTO.setUser(userDTO);
        userDTO.setProfile(profileDTO);
        new RoleServiceImpl().save(roleDTO);
        new ProfileServiceImpl().save(profileDTO);
        new UserServiceImpl().save(userDTO);
    }

    @Test
    public void createDiscountTest() {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setName("Primary");
        discountDTO.setRate(10);
        discountDTO.setExpTime(LocalDateTime.now());
        new DiscountServiceImpl().save(discountDTO);

    }
}
