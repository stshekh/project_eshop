package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.User;

import java.sql.Connection;

public interface UserDao extends GenericDao<User> {
    User findUserByEmail(String email);
}
