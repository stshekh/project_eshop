package com.gmail.sshekh.service;

import com.gmail.sshekh.dao.AuditDao;
import com.gmail.sshekh.dao.ProfileDao;
import com.gmail.sshekh.dao.RoleDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.impl.AuditDaoImpl;
import com.gmail.sshekh.dao.impl.ProfileDaoImpl;
import com.gmail.sshekh.dao.impl.RoleDaoImpl;
import com.gmail.sshekh.dao.impl.UserDaoImpl;
import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserActionsTests {
    private static final Logger logger = LogManager.getLogger(UserActionsTests.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private ProfileDao profileDao = new ProfileDaoImpl(Profile.class);
    private AuditDao auditDao = new AuditDaoImpl(Audit.class);

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

        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            userDao.create(user);
            List<User> users = userDao.findAll();
            logger.info(users.stream().findAny().get().getFirstName());
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Test
    public void userProfileInfo() {
        Role role = new Role();
        User user = new User();
        Profile profile = new Profile();

        role.setRoleName("Admin");

        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin");
        user.setPassword("admin");

        profile.setAddress("Minsk");
        profile.setTelephone("22222222");

        user.setRole(role);
        user.setProfile(profile);
        profile.setUser(user);

        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            userDao.create(user);
            profileDao.create(profile);
            List<User> users = userDao.findAll();
            logger.info(users.stream().findAny().get().getProfile().getAddress());
            logger.info(users.stream().findAny().get().getRole().getRoleName());
            transaction.commit();
        } catch (Exception e) {
            logger.error(e);
            session.getTransaction().rollback();
        }
    }

    @Test
    public void auditUserTest() {
        User user = new User();
        Audit audit = new Audit();
        Audit audit1=new Audit();
        Role role = new Role();

        user.setFirstName("User");
        user.setLastName("User_Last");
        user.setEmail("user@user");
        user.setPassword("user");

        role.setRoleName("user");

        user.setRole(role);

        audit.setEventType("Creating");
        audit.setCreated(LocalDateTime.now());

        audit1.setEventType("Bla bla");
        audit1.setCreated(LocalDateTime.now());

        audit.setUser(user);
        audit1.setUser(user);

        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            userDao.create(user);
            auditDao.create(audit);
            auditDao.create(audit1);
            List<Audit> audits= auditDao.findAll();
            logger.info(audits.stream().findAny().get().getEventType());
            transaction.commit();
        } catch (Exception e) {
            logger.error(e);
            session.getTransaction().rollback();
        }

    }
}
