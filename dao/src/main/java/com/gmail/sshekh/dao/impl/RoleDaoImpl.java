package com.gmail.sshekh.dao.impl;


import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findRoleById(Long id) {
        String hql = "FROM Role AS R WHERE R.idRole=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Role) query.uniqueResult();
    }

}
