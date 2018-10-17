package com.gmail.sshekh.controllers.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private String userRegisterPagePath;
    private String userUpdatePagePath;
    private String userRoleUpdatePage;
    private String userEnableUpdatePage;
    private String newsPagePath;
    private String newsCreatePage;
    private String profilePagePath;
    private String profileCreatePagePath;
    private String oneNewsPage;
    private String newsUpdatePage;
    private String commentCreatePage;
    private String usersBusinessCards;
    private String usersCreateBusinessCard;
    private String fileUploadPagePath;

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
        this.newsPagePath = environment.getProperty("news.page.path");
        this.profilePagePath = environment.getProperty("profile.page.path");
        this.profileCreatePagePath = environment.getProperty("profile.create.path");
        this.newsCreatePage = environment.getProperty("news.create.page");
        this.oneNewsPage = environment.getProperty("one.news.page");
        this.newsUpdatePage = environment.getProperty("news.update.page");
        this.commentCreatePage = environment.getProperty("comment.create.page");
        this.usersBusinessCards = environment.getProperty("business.cards.page");
        this.usersCreateBusinessCard = environment.getProperty("business.card.create.page");
        this.userRegisterPagePath = environment.getProperty("user.register.page.path");
        this.fileUploadPagePath = environment.getProperty("items.upload.page");//TODO CHANGE PATH
    }

    public String getFileUploadPagePath() {
        return fileUploadPagePath;
    }

    public String getUserRegisterPagePath() {
        return userRegisterPagePath;
    }

    public String getUsersCreateBusinessCard() {
        return usersCreateBusinessCard;
    }

    public String getUsersBusinessCards() {
        return usersBusinessCards;
    }

    public String getCommentCreatePage() {
        return commentCreatePage;
    }

    public String getNewsUpdatePage() {
        return newsUpdatePage;
    }

    public String getOneNewsPage() {
        return oneNewsPage;
    }

    public String getNewsCreatePage() {
        return newsCreatePage;
    }

    public String getProfileCreatePagePath() {
        return profileCreatePagePath;
    }

    public String getProfilePagePath() {
        return profilePagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
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

