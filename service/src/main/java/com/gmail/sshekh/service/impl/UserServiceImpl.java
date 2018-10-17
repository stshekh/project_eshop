package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.DiscountService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.BusinessCardDTO;
import com.gmail.sshekh.service.dto.DiscountDTO;
import com.gmail.sshekh.service.dto.OrderDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<User, UserDTO> userDTOConverter;
    @Autowired
    @Qualifier("businessCardDTOConverter")
    private DTOConverter<BusinessCard, BusinessCardDTO> businessCardDTOConverter;
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("orderConverter")
    private Converter<OrderDTO, Order> orderConverter;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDTO save(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        user.setRole(roleDao.findRoleById(2L));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setEnabled(true);
        userDao.create(user);
        return userDTOConverter.toDTO(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDTO update(UserDTO userDTO) {
        User user = userDao.findOne(userDTO.getId());
        List<Order> orders = user.getOrders();
        user = userConverter.toEntity(userDTO);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setRole(roleDao.findRoleById(userDao.getRoleIdByUserId(user.getId())));
        user.setOrders(orders);
        if (userDao.findUserById(userDTO.getId()).getComments() != null) {
            user.setComments(userDao.findUserById(userDTO.getId()).getComments());
        }
        userDao.update(user);
        //TODO order user converter correctly
        return userDTOConverter.toDTO(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(Long id) {
        User user = userDao.findUserById(id);
        user.setRole(null);
        userDao.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDTO findUserByEmail(String email) {
        User savedUser = userDao.findUserByEmail(email);
        return userDTOConverter.toDTO(savedUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<UserDTO> findAll() {
        List<User> users = userDao.findAll();
        return userDTOConverter.toDTOList(users);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<UserDTO> findAll(int startPosition, int maxResult) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxResult;
        else firstPosition = 0;
        List<User> users = userDao.findAll(firstPosition, maxResult);
        return userDTOConverter.toDTOList(users);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void setDiscount(UserDTO userDTO) {
        DiscountDTO discountDTO = discountService.getRandomDiscount();
        userDTO.setDiscountDTO(discountDTO);
        User user = userConverter.toEntity(userDTO);
        userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDTO findUserById(Long id) {
        User user = userDao.findUserById(id);
        return userDTOConverter.toDTO(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void setEnabled(UserDTO userDTO) {
        User user = userDao.findUserById(userDTO.getId());
        user.setEnabled(userDTO.isEnabled());
        userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Set<BusinessCardDTO> getBusinessCards(Long id) {
        Set<BusinessCard> businessCards = userDao.getUsersBusinessCards(id);
        return businessCardDTOConverter.toDTOSet(businessCards);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public BusinessCardDTO deleteUsersBusinessCard(Long id) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Integer countUsers() {
        return userDao.countUsers().intValue();
    }

}
