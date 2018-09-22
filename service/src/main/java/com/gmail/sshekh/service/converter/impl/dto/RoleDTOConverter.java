package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.service.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleDTOConverter implements DTOConverter<Role, RoleDTO> {
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();

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
