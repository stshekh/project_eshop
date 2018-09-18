package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dto.UserDTO;
import com.gmail.sshekh.dao.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter implements Converter<UserDTO, User> {
    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getName());
        user.setLastName(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setRole(new RoleConverter().toEntity(dto.getRole()));
        user.setProfile(new ProfileConverter().toEntity(dto.getProfile()));
        user.setOrders(new OrderConverter().toEntityList(dto.getOrders()));
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
