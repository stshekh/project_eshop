package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.dto.PermissionDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionDTOConverter implements DTOConverter<Permission, PermissionDTO> {
    @Override
    public PermissionDTO toDTO(Permission entity) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(entity.getIdPermission());
        permissionDTO.setName(entity.getPermissionName());
        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> toDTOList(List<Permission> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<PermissionDTO> toDTOSet(Set<Permission> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
