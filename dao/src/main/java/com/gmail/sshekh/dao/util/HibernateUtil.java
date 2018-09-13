package com.gmail.sshekh.dao.util;


import com.gmail.sshekh.config.ConfigurationManager;
import com.gmail.sshekh.dao.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;


import java.util.HashMap;
import java.util.Map;

import static com.gmail.sshekh.config.ConfigurationManager.*;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, ConfigurationManager.getInstance().getProperty(DATABASE_DRIVER_NAME));
                settings.put(Environment.URL, ConfigurationManager.getInstance().getProperty(DATABASE_URL));
                settings.put(Environment.USER, ConfigurationManager.getInstance().getProperty(DATABASE_USERNAME));
                settings.put(Environment.PASS, ConfigurationManager.getInstance().getProperty(DATABASE_PWD));
                settings.put(Environment.HBM2DDL_AUTO, ConfigurationManager.getInstance().getProperty(HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, ConfigurationManager.getInstance().getProperty(CURRENT_SESSION_CONTEXT_CLASS));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry builder created succesfully!");

                MetadataSources sources = new MetadataSources(registry).
                        addAnnotatedClass(User.class).
                        addAnnotatedClass(Role.class).
                        addAnnotatedClass(Audit.class).
                        addAnnotatedClass(Comment.class).
                        addAnnotatedClass(Item.class).
                        addAnnotatedClass(News.class).
                        addAnnotatedClass(Order.class).
                        addAnnotatedClass(Permission.class).
                        addAnnotatedClass(Profile.class).
                        addAnnotatedClass(RolePermission.class).
                        addAnnotatedClass(OrderId.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("Session Factory created.");
            } catch (Exception e) {
                logger.error("Session Factory failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
