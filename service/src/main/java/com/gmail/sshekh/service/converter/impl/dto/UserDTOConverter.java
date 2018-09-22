package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDTOConverter implements DTOConverter<User, UserDTO> {
    private OrderDTOConverter orderDTOConverter = new OrderDTOConverter();
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();
    private DiscountDTOConverter discountDTOConverter = new DiscountDTOConverter();

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setName(entity.getFirstName());
        userDTO.setSurname(entity.getLastName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRole(roleDTOConverter.toDTO(entity.getRole()));
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
