package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.AuditDao;
import com.gmail.sshekh.dao.model.Audit;

public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl(Class<Audit> clazz) {
        super(clazz);
    }
}
