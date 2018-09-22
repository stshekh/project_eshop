package com.gmail.sshekh.controllers.command.impl;

import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.config.ConfigurationManager;
import com.gmail.sshekh.service.impl.UserServiceImpl;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.controllers.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserDTO> users = userService.findAll();
        request.setAttribute("users", users);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USERS_PAGE_PATH);

    }
}
