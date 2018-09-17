package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.dto.PermissionDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionConverter implements Converter<PermissionDTO, Permission> {
    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setIdPermission(dto.getId());
        permission.setPermissionName(dto.getName());
        return permission;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Permission> toEntitySet(Set<PermissionDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
