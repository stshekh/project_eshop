package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.PermissionService;
import com.gmail.sshekh.converter.impl.dto.PermissionDTOConverter;
import com.gmail.sshekh.converter.impl.entity.PermissionConverter;
import com.gmail.sshekh.dao.PermissionDao;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.dto.PermissionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LogManager.getLogger(PermissionService.class);
    private PermissionDao permissionDao = new PermissionDaoImpl(Permission.class);
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();
    private PermissionConverter permissionConverter = new PermissionConverter();

    @Override
    public PermissionDTO save(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permissionDao.create(permission);
            PermissionDTO permissionDTONew = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            logger.info("Permission save was success");
            return permissionDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Permission save failed", e);
        }
        return permissionDTO;
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permissionDao.update(permission);
            PermissionDTO permissionDTONew = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            logger.info("Permission update was success");
            return permissionDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Permission update failed", e);
        }
        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> findAll() {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Permission> permissions = permissionDao.findAll();
            List<PermissionDTO> permissionDTOS = permissionDTOConverter.toDTOList(permissions);
            transaction.commit();
            logger.info("Permissions were found");
            return permissionDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find all the permissions");
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permissionDao.delete(permission);
            transaction.commit();
            logger.info("Permission was deleted");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Permission wasn't deleted", e);
        }
    }
}
