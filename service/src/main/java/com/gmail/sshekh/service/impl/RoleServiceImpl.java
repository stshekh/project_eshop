package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.service.RoleService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleDao roleDao;
    @Autowired
    @Qualifier("roleDTOConverter")
    private DTOConverter<Role, RoleDTO> roleDTOConverter;
    @Autowired
    @Qualifier("roleConverter")
    private Converter<RoleDTO, Role> roleConverter;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleConverter.toEntity(roleDTO);
        roleDao.create(role);
        return roleDTOConverter.toDTO(role);
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
        Role role = roleDao.findRoleById(id);
        return roleDTOConverter.toDTO(role);
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleDao.findAll();
        return roleDTOConverter.toDTOList(roles);
    }
}
