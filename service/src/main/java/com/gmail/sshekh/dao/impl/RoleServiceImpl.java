package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.RoleService;
import com.gmail.sshekh.converter.impl.entity.RoleConverter;
import com.gmail.sshekh.converter.impl.dto.RoleDTOConverter;
import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();
    private RoleConverter roleConverter = new RoleConverter();

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Role role = roleConverter.toEntity(roleDTO);
            roleDao.create(role);
            RoleDTO roleDTONew = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error(e.getMessage(), e);
        }
        return roleDTO;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Role role = roleConverter.toEntity(roleDTO);
            roleDao.update(role);
            RoleDTO roleDTONew = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return roleDTO;
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Role role = roleConverter.toEntity(roleDTO);
            roleDao.delete(role);
            transaction.commit();
            logger.info("Role " + role.getRoleName() + " was deleted");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete role", e);
        }
    }

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

    @Override
    public List<RoleDTO> findAll() {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Role> roles = roleDao.findAll();
            List<RoleDTO> roleDTOS = roleDTOConverter.toDTOList(roles);
            transaction.commit();
            return roleDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return Collections.emptyList();
    }
}
