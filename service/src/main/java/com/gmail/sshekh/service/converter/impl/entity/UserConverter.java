package com.gmail.sshekh.service.converter.impl.entity;

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

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getName());
        user.setLastName(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.isEnabled());
        if (!dto.getComments().isEmpty()) {
            user.setComments(commentConverter.toEntitySet(dto.getComments()));
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
