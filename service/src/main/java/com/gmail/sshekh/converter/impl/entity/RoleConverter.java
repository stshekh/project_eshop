package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dto.RoleDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleConverter implements Converter<RoleDTO, Role> {

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setIdRole(dto.getIdRole());
        role.setRoleName(dto.getRoleName());
        role.setPermissions(new PermissionConverter().toEntitySet(dto.getPermissions()));
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Role> toEntitySet(Set<RoleDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
