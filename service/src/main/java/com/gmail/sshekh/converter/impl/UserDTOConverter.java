package com.gmail.sshekh.converter.impl;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTOConverter implements DTOConverter<User, UserDTO> {
    @Override
    public UserDTO toDTO(User entity) {
        RoleDTOConverter roleDTOConverter=new RoleDTOConverter();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setName(entity.getFirstName());
        userDTO.setSurname(entity.getLastName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRole(roleDTOConverter.toDTO(entity.getRole()));
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
