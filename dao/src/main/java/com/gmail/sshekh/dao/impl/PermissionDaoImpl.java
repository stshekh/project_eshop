package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.PermissionDao;
import com.gmail.sshekh.dao.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    public PermissionDaoImpl() {
        super(Permission.class);
    }
}
