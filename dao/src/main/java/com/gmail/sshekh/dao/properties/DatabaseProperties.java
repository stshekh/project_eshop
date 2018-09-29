package com.gmail.sshekh.dao.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseProperties {

    @Autowired
    private Environment environment;

    private Integer maxPoolSize;
    private String dataSourceClass;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String cachePreparedStatements;
    private Integer cachePreparedStatementsSize;
    private Integer cachePreparedStatementsSQLLimit;
    private String useServerPreparedStatements;
    private String hibernateCurrentSessionContextClass;
    private String hibernateHbm2dllAuto;
    private String hibernateShowSQL;
    private String hibernateDialect;
    private String hibernateCacheUseSecondLevelCache;
    private String hibernateCacheRegionFactoryClass;

    @PostConstruct
    public void initialize() {
        this.maxPoolSize = Integer.valueOf(environment.getProperty("pool.max.size"));
        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.databaseUrl = environment.getProperty("database.url");
        this.databaseUsername = environment.getProperty("database.username");
        this.databasePassword = environment.getProperty("database.password");
        this.cachePreparedStatements = environment.getProperty("pool.cache.prepared.statements");
        this.cachePreparedStatementsSize = Integer.valueOf(environment.getProperty("pool.cache.prepared.statements.size"));
        this.cachePreparedStatementsSQLLimit = Integer.valueOf(environment.getProperty("pool.cache.prepared.statements.sql.limit"));
        this.useServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");
        this.hibernateCurrentSessionContextClass = environment
                .getProperty("hibernate.current_session_context_class");
        this.hibernateHbm2dllAuto = environment.getProperty("hibernate.hbm2ddl.auto");
        this.hibernateShowSQL = environment.getProperty("hibernate.show_sql");
        this.hibernateDialect = environment.getProperty("hibernate.dialect");
        this.hibernateCacheUseSecondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.hibernateCacheRegionFactoryClass = environment.getProperty("hibernate.cache.region.factory_class");


    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getHibernateCurrentSessionContextClass() {
        return hibernateCurrentSessionContextClass;
    }

    public String getHibernateHbm2dllAuto() {
        return hibernateHbm2dllAuto;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public Integer getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public Integer getCachePreparedStatementsSQLLimit() {
        return cachePreparedStatementsSQLLimit;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }

    public String getHibernateShowSQL() {
        return hibernateShowSQL;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateCacheUseSecondLevelCache() {
        return hibernateCacheUseSecondLevelCache;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }
}
