package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.PermissionDTO;
import com.gmail.sshekh.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<Role, RoleDTO> {

    @Autowired
    @Qualifier("permissionDTOConverter")
    private DTOConverter<Permission, PermissionDTO> permissionDTOConverter;

    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(entity.getIdRole());
        roleDTO.setRoleName(entity.getRoleName());
        roleDTO.setPermissions(permissionDTOConverter.toDTOSet(entity.getPermissions()));
        return roleDTO;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<RoleDTO> toDTOSet(Set<Role> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
