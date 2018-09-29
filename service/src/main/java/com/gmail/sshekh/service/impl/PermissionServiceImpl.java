package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.PermissionDao;
import com.gmail.sshekh.dao.model.Permission;
import com.gmail.sshekh.service.PermissionService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.converter.impl.entity.PermissionConverter;
import com.gmail.sshekh.service.dto.PermissionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LogManager.getLogger(PermissionService.class);
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    @Qualifier("permissionDTOConverter")
    private DTOConverter<Permission, PermissionDTO> permissionDTOConverter;
    @Autowired
    @Qualifier("permissionConverter")
    private Converter<PermissionDTO, Permission> permissionConverter;

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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
        }
    }
}
