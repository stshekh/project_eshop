package com.gmail.sshekh;

import com.gmail.sshekh.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {
    PermissionDTO save(PermissionDTO permissionDTO);

    PermissionDTO update(PermissionDTO permissionDTO);

    List<PermissionDTO> findAll();

    void delete(PermissionDTO permissionDTO);
}
