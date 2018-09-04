package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao extends GenericDao<Role> {
    Role findRoleById(Long id);

    List<Role> findAll(Connection connection);
}
