package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.RoleService;
import com.gmail.sshekh.converter.impl.RoleConverter;
import com.gmail.sshekh.converter.impl.RoleDTOConverter;
import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();
    private RoleConverter roleConverter = new RoleConverter();

    @Override
    public RoleDTO findRoleById(Long id) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Role role = roleDao.findRoleById(id);
            transaction.commit();
            return roleDTOConverter.toDTO(role);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to fetch users", e);
        }
        return null;
    }
}
