package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO roleDTO);

    RoleDTO update(RoleDTO roleDTO);

    void delete(RoleDTO roleDTO);

    RoleDTO findRoleById(Long id);

    List<RoleDTO> findAll();

    RoleDTO getRoleByName(String name);
}
