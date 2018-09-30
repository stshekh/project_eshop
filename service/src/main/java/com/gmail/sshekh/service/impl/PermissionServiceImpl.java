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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
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
        Permission permission = permissionConverter.toEntity(permissionDTO);
        permissionDao.create(permission);
        return permissionDTOConverter.toDTO(permission);
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        Permission permission = permissionConverter.toEntity(permissionDTO);
        permissionDao.update(permission);
        return permissionDTOConverter.toDTO(permission);

    }

    @Override
    public List<PermissionDTO> findAll() {
        List<Permission> permissions = permissionDao.findAll();
        return permissionDTOConverter.toDTOList(permissions);
    }

    @Override
    public void delete(PermissionDTO permissionDTO) {
        Permission permission = permissionConverter.toEntity(permissionDTO);
        permissionDao.delete(permission);

    }
}
