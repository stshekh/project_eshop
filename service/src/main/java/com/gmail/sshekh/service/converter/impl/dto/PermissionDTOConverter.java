package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("permissionDTOConverter")
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
