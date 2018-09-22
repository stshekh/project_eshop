package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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
