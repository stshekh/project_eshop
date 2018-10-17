package com.gmail.sshekh.controllers.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {


    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${errors.page.path}")
    private String errorsPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${user.create.page.path}")
    private String userCreatePagePath;
    @Value("${user.register.page.path}")
    private String userRegisterPagePath;
    @Value("${user.update.page.path}")
    private String userUpdatePagePath;
    @Value("${user.role.update.page}")
    private String userRoleUpdatePage;
    @Value("${user.enable.page}")
    private String userEnableUpdatePage;
    @Value("${news.page.path}")
    private String newsPagePath;
    @Value("${news.create.page}")
    private String newsCreatePage;
    @Value("${profile.page.path}")
    private String profilePagePath;
    @Value("${profile.create.path}")
    private String profileCreatePagePath;
    @Value("${one.news.page}")
    private String oneNewsPage;
    @Value("${news.update.page}")
    private String newsUpdatePage;
    @Value("${comment.create.page}")
    private String commentCreatePage;
    @Value("${business.cards.page}")
    private String usersBusinessCards;
    @Value("${business.card.create.page}")
    private String usersCreateBusinessCard;
    @Value("${items.upload.page}")
    private String fileUploadPagePath;
    @Value("${items.create.page}")
    private String itemsCreatePage;
    @Value("${items.update.page}")
    private String itemsUpdatePage;
    @Value("${order.status.update}")
    private String orderStatusUpdate;
    @Value("${orders.create}")
    private String orderCreate;
}

