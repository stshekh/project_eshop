package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.UserRoleDTO;

public interface UserRoleService {
    void changeRole(UserRoleDTO userRole);

    UserRoleDTO getUsersRole(Long id);
}
