package com.gmail.sshekh.dao.impl;


import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }

    @Override
    public Role findRoleById(Long id) {
        String hql = "from Role as R where R.idRole=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Role) query.uniqueResult();
    }

}
