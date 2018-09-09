package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.PermissionDao;
import com.gmail.sshekh.dao.model.Permission;

public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    public PermissionDaoImpl(Class<Permission> clazz) {
        super(clazz);
    }
}
