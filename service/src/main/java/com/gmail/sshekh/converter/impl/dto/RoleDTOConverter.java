package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dto.RoleDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTOConverter implements DTOConverter<Role, RoleDTO> {
    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(entity.getIdRole());
        roleDTO.setRoleName(entity.getRoleName());
        roleDTO.setPermissions(new PermissionDTOConverter().toDTOSet(entity.getPermissions()));
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
