package com.gmail.sshekh.service;

import com.gmail.sshekh.dao.*;
import com.gmail.sshekh.dao.impl.*;
import com.gmail.sshekh.dao.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserActionsTests {
    private static final Logger logger = LogManager.getLogger(UserActionsTests.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private ProfileDao profileDao = new ProfileDaoImpl(Profile.class);
    private AuditDao auditDao = new AuditDaoImpl(Audit.class);
    private NewsDao newsDao = new NewsDaoImpl(News.class);
    private CommentDao commentDao = new CommentDaoImpl(Comment.class);
    private PermissionDao permissionDao = new PermissionDaoImpl(Permission.class);
    private OrderDao orderDao = new OrderDaoImpl(Order.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);

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
        Audit audit1 = new Audit();
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
            List<Audit> audits = auditDao.findAll();
            logger.info(audits.stream().findAny().get().getEventType());
            transaction.commit();
        } catch (Exception e) {
            logger.error(e);
            session.getTransaction().rollback();
        }
    }

    @Test
    public void newsTest() {
        News news = new News();
        Role role = new Role();
        Role role1 = new Role();
        User user = new User();
        User user1 = new User();
        Comment commentUs = new Comment();
        Comment commentUs1 = new Comment();

        role.setRoleName("Admin");
        role1.setRoleName("User");

        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin");
        user.setPassword("admin");

        user1.setFirstName("user");
        user1.setLastName("userov");
        user1.setEmail("user@user");
        user1.setPassword("user");

        news.setTitle("Today news");
        news.setContent("Main todays news that is sdhfkjfhsdfkjsdkbhfnsdkjfjksdfjdvdfdfhgjlkfdglkfdvndlkfngkfdhgdfngjfdngkjdf");
        news.setCreated(LocalDateTime.now());

        commentUs.setContent("admin comments");
        commentUs.setCreated(LocalDateTime.now());

        commentUs1.setContent("user comments");
        commentUs1.setCreated(LocalDateTime.now());

        Set<Comment> commentSet = new HashSet<>();
        commentSet.add(commentUs);
        commentSet.add(commentUs1);

        user.setRole(role);
        user1.setRole(role1);
        news.setUser(user);
        commentUs.setUser(user);
        commentUs1.setUser(user1);
        news.setComments(commentSet);

        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            roleDao.create(role1);
            userDao.create(user);
            userDao.create(user1);
            newsDao.create(news);
            commentDao.create(commentUs);
            commentDao.create(commentUs1);
            List<News> news1 = newsDao.findAll();
            List<User> users = userDao.findAll();
            logger.info(users.stream().findAny().get().getFirstName());
            logger.info(news1.stream().findAny().get().getTitle() + " " + news1.stream().findAny().get().getContent() + " " + news1.stream().findAny().get().getUser().getFirstName());
            Assert.assertEquals(1, news1.size());
            news1.stream().findAny().ifPresent(comment -> {
                Assert.assertEquals(2, comment.getComments().size());
                logger.info(comment.getComments());
            });
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Test
    public void rolePermissionTest() {
        Role role = new Role();
        Role role1 = new Role();
        Permission permission = new Permission();
        Permission permission1 = new Permission();

        role.setRoleName("Admin");
        role1.setRoleName("User");

        permission.setPermissionName("Read");
        permission1.setPermissionName("Create");

        Set<Permission> permissionSet = new HashSet<>();
        Set<Permission> permissionSet1 = new HashSet<>();

        permissionSet.add(permission);
        permissionSet.add(permission1);

        permissionSet1.add(permission);

        Set<Role> roleSet = new HashSet<>();
        Set<Role> roleSet1 = new HashSet<>();

        roleSet.add(role);
        roleSet.add(role1);

        roleSet1.add(role);

        role.setPermissions(permissionSet);
        role1.setPermissions(permissionSet1);
        permission.setRoles(roleSet);
        permission1.setRoles(roleSet1);

        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            roleDao.create(role1);
            permissionDao.create(permission);
            permissionDao.create(permission1);
            List<Role> roles = roleDao.findAll();
            logger.info(roles.stream().findFirst().get().getRoleName());
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

    }

    @Test
    public void userOrderTest() {
        Role role = new Role();
        Role role1 = new Role();
        User user = new User();
        User user1 = new User();


        role.setRoleName("Admin");
        role1.setRoleName("User");

        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin");
        user.setPassword("admin");

        user1.setFirstName("user");
        user1.setLastName("userov");
        user1.setEmail("user@user");
        user1.setPassword("user");

        user.setRole(role);
        user1.setRole(role1);

       Item item=new Item();

        item.setName("Phone");
        item.setDescription("Best phone of 2019");
        item.setUniqueNumber("PH20102");
        item.setPrice(199.12);

        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            roleDao.create(role);
            roleDao.create(role1);
            userDao.create(user);
            userDao.create(user1);
            itemDao.create(item);
            Order order=new Order(user,item);
            order.setQuantity(5);
            order.setCreated(LocalDateTime.now());

            orderDao.create(order);
            List<Order> orders = orderDao.findAll();
            logger.info(orders.stream().findFirst().get().getUser().getFirstName()+" "
                    +orders.stream().findFirst().get().getItem().getName()+" "
                    +orders.stream().findFirst().get().getQuantity());
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
