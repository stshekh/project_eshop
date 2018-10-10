package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.UserRoleService;
import com.gmail.sshekh.service.dto.UserRoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LogManager.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public void changeRole(UserRoleDTO userRole) {
        User user = userDao.findUserById(userRole.getUserId());
        Role role = roleDao.findRoleById(userRole.getRoleId());
        user.setRole(role);
        userDao.update(user);
    }

    @Override
    public UserRoleDTO getUsersRole(Long id) {
        Long roleId = userDao.getRoleIdByUserId(id);
        UserRoleDTO userRole = new UserRoleDTO();
        userRole.setUserId(id);
        userRole.setRoleId(roleId);
        userRole.setUserName(userDao.findUserById(id).getFirstName());
        return userRole;
    }
}
