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
    private String userCreatePagePath;
    private String userUpdatePagePath;
    private String userRoleUpdatePage;
    private String userEnableUpdatePage;

    @PostConstruct
    public void initialize() {
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.loginPagePath = environment.getProperty("login.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.userUpdatePagePath = environment.getProperty("user.update.page.path");
        this.userCreatePagePath = environment.getProperty("user.create.page.path");
        this.userRoleUpdatePage = environment.getProperty("user.role.update.page");
        this.userEnableUpdatePage = environment.getProperty("user.enable.page");
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

    public String getUserCreatePagePath() {
        return userCreatePagePath;
    }

    public String getUserUpdatePagePath() {
        return userUpdatePagePath;
    }

    public String getUserRoleUpdatePage() {
        return userRoleUpdatePage;
    }

    public String getUserEnableUpdatePage() {
        return userEnableUpdatePage;
    }
}

