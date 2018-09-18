package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role findRoleById(Long id);
}
