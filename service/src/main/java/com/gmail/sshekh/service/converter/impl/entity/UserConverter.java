package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.service.RoleService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.impl.RoleServiceImpl;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.dao.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserDTO, User> {
    private RoleConverter roleConverter = new RoleConverter();

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getName());
        user.setLastName(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setRole(roleConverter.toEntity(dto.getRole()));
        if (dto.getDiscountDTO() != null) {
            user.setDiscount(new DiscountConverter().toEntity(dto.getDiscountDTO()));
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
