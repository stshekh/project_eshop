package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.User;

public interface UserDao extends GenericDao<User> {
    User findUserByEmail(String email);
}
