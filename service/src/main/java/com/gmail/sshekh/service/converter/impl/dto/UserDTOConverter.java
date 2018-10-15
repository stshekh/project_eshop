package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.*;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<User, UserDTO> {
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    @Qualifier("roleDTOConverter")
    private DTOConverter<Role, RoleDTO> roleDTOConverter;
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<Discount, DiscountDTO> discountDTOConverter;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<Comment, CommentDTO> commentDTOConverter;

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setName(entity.getFirstName());
        userDTO.setSurname(entity.getLastName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setEnabled(entity.isEnabled());

        if (!entity.getComments().isEmpty()) {
            userDTO.setComments(commentDTOConverter.toDTOList(entity.getComments()));
        }

        if (entity.getRole() != null) {
            userDTO.setRole(roleDTOConverter.toDTO(entity.getRole()));
        }

        if (entity.getDiscount() != null) {
            userDTO.setDiscountDTO(discountDTOConverter.toDTO(entity.getDiscount()));
        }

        if (!entity.getOrders().isEmpty()) {
            userDTO.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<UserDTO> toDTOSet(Set<User> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
