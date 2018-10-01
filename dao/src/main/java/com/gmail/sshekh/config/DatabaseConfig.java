package com.gmail.sshekh.config;

import com.gmail.sshekh.dao.model.*;
import com.gmail.sshekh.dao.properties.DatabaseProperties;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setPoolName("App connection pool");
        ds.setMaximumPoolSize(databaseProperties.getMaxPoolSize());
        ds.setDataSourceClassName(databaseProperties.getDataSourceClass());
        ds.addDataSourceProperty("url", databaseProperties.getDatabaseUrl());
        ds.addDataSourceProperty("user", databaseProperties.getDatabaseUsername());
        ds.addDataSourceProperty("password", databaseProperties.getDatabasePassword());
        ds.addDataSourceProperty("cachePrepStmts", databaseProperties.getCachePreparedStatements());
        ds.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getCachePreparedStatementsSize());
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getCachePreparedStatementsSQLLimit());
        ds.addDataSourceProperty("useServerPrepStmts", databaseProperties.getUseServerPreparedStatements());
        return ds;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(Boolean.TRUE);
        liquibase.setChangeLog("classpath:migration/db-changelog.xml");
        return liquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put(DIALECT, databaseProperties.getHibernateDialect());
        properties.put(SHOW_SQL, databaseProperties.getHibernateShowSQL());
        properties.put(HBM2DDL_AUTO, databaseProperties.getHibernateHbm2dllAuto());
        properties.put(USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateCacheUseSecondLevelCache());
        properties.put(CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                Audit.class,
                Comment.class,
                Discount.class,
                Item.class,
                News.class,
                Order.class,
                OrderId.class,
                Permission.class,
                Profile.class,
                Role.class,
                User.class
        );
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
