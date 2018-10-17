package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.*;
import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.OrderService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.ItemDTO;
import com.gmail.sshekh.service.dto.OrderDTO;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    @Qualifier("orderIdDTOConverter")
    private DTOConverter<OrderId, OrderIdDTO> orderIdDTOConverter;
    @Autowired
    @Qualifier("orderIdConverter")
    private Converter<OrderIdDTO, OrderId> orderIdConverter;
    @Autowired
    private ItemService itemService;

    @Override
    public void save(OrderDTO orderDTO) {
        //TODO test either it works or not
        Item item = itemDao.findOne(orderDTO.getItem().getId());
        User user = userDao.findOne(orderDTO.getUser().getId());
        Order order = new Order(user, item);
        order.setQuantity(orderDTO.getQuantity());
        order.setCreated(orderDTO.getCreated());
        user.getOrders().add(order);
        item.getOrders().add(order);
        userDao.update(user);
        itemDao.update(item);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderDao.findAll();
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public void addOrdersToItemsAndUsers(BigDecimal fromAmount, BigDecimal toAmount, OrderDTO orderDTO) {
        ItemDTO item = itemService.getItemOfPrice(fromAmount, toAmount);
        Long amount = itemDao.countItemsInRange(fromAmount, toAmount);
        orderDTO.setQuantity(amount);
        orderDTO.setItem(item);
        update(orderDTO);
    }

    @Override
    public void update(OrderDTO orderDTO) {
        Item item = itemDao.findOne(orderDTO.getItem().getId());
        User user = userDao.findOne(orderDTO.getUser().getId());
        Order order = new Order(user, item);
        order.setQuantity(orderDTO.getQuantity());
        order.setCreated(orderDTO.getCreated());
        user.getOrders().add(order);
        order.getUser().getOrders().add(order);
        userDao.update(user);
    }

    @Override
    public Integer countAllOrders() {
        return orderDao.countAllOrders().intValue();
    }

    @Override
    public List<OrderDTO> findAll(int startPosition, int maxOnPage) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxOnPage;
        else firstPosition = 0;
        List<Order> orders = orderDao.findAll(firstPosition, maxOnPage);
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public OrderDTO getOrderByOrderId(Long idUser, Long idItem) {
        OrderIdDTO id = new OrderIdDTO();
        id.setUserId(idUser);
        id.setItemId(idItem);
        return orderDTOConverter.toDTO(orderDao.getOrderByOrderId(orderIdConverter.toEntity(id)));
    }

    @Override
    public void updateStatus(OrderDTO orderDTO, Long idUser, Long idItem) {
        OrderIdDTO id = new OrderIdDTO();
        id.setUserId(idUser);
        id.setItemId(idItem);
        OrderId orderId = orderIdConverter.toEntity(id);
        Order order = orderDao.getOrderByOrderId(orderId);
        order.setStatus(StatusEnum.valueOf(orderDTO.getStatus()));
        orderDao.update(order);
    }

    @Override
    public List<OrderDTO> getUsersOrders(int startPosition, int maxOnPage) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxOnPage;
        else firstPosition = 0;
        UserPrincipal userPrincipal = getLoggedInUser();
        Long userId = userPrincipal.getId();
        return orderDTOConverter.toDTOList(orderDao.findOrdersByUserId(userId, firstPosition, maxOnPage));
    }
}
