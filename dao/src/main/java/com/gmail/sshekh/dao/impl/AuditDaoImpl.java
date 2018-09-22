package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.AuditDao;
import com.gmail.sshekh.dao.model.Audit;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl() {
        super(Audit.class);
    }

}
