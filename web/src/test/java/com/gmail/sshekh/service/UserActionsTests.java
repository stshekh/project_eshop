package com.gmail.sshekh.service;

import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.impl.RoleDaoImpl;
import com.gmail.sshekh.dao.impl.UserDaoImpl;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class UserActionsTests {
    private static final Logger logger = LogManager.getLogger(UserActionsTests.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);

    @Test
    public void userSaveInfo() {
        Role role = new Role();
        User user = new User();

        role.setRoleName("Admin");

        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin");
        user.setPassword("admin");

        user.setRole(role);
        role.setUser(user);

        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            user.setRoleId(role.getIdRole());
            userDao.create(user);
            List<User> users = userDao.findAll();
            logger.info(users.stream().findAny().get().getFirstName());
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
