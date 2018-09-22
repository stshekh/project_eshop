package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.service.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleConverter implements Converter<RoleDTO, Role> {

    private PermissionConverter permissionConverter = new PermissionConverter();

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setIdRole(dto.getIdRole());
        role.setRoleName(dto.getRoleName());
        role.setPermissions(permissionConverter.toEntitySet(dto.getPermissions()));
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
