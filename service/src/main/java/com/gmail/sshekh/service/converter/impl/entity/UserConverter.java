package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.*;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("userConverter")
public class UserConverter implements Converter<UserDTO, User> {
    @Autowired
    @Qualifier("roleConverter")
    private Converter<RoleDTO, Role> roleConverter;
    @Autowired
    @Qualifier("discountConverter")
    private Converter<DiscountDTO, Discount> discountConverter;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;
    @Autowired
    @Qualifier("businessCardConverter")
    private Converter<BusinessCardDTO, BusinessCard> businessCardConverter;
    @Autowired
    private UserDao userDao;

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        if (dto.getId() != null) {
            user = userDao.findOne(dto.getId());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getName() != null) {
            user.setFirstName(dto.getName());
        }
        if (dto.getSurname() != null) {
            user.setLastName(dto.getSurname());
        }
        user.setPassword(dto.getPassword());
        user.setEnabled(true);
        if (!dto.getComments().isEmpty()) {
            user.setComments(commentConverter.toEntityList(dto.getComments()));
        }
        if (dto.getRole() != null) {
            user.setRole(roleConverter.toEntity(dto.getRole()));
        }
        if (dto.getDiscountDTO() != null) {
            user.setDiscount(discountConverter.toEntity(dto.getDiscountDTO()));
        }
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());

    }

    @Override
    public Set<User> toEntitySet(Set<UserDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
