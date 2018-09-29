package com.gmail.sshekh.controllers.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PageProperties {
    @Autowired
    private Environment environment;

    private String loginPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String usersPagePath;
    private String usersUpdatePagePath;
    private String usersCreatePagePath;
    private String userPagePath;

    @PostConstruct
    public void initialize() {
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.loginPagePath = environment.getProperty("login.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.userPagePath = environment.getProperty("users.update.page.path");
        this.usersUpdatePagePath = environment.getProperty("users.update.page.path");
        this.usersCreatePagePath = environment.getProperty("users.create.page.path");
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getUsersUpdatePagePath() {
        return usersUpdatePagePath;
    }

    public String getUsersCreatePagePath() {
        return usersCreatePagePath;
    }

    public String getUserPagePath() {
        return userPagePath;
    }
}

