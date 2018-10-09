package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findUserByEmail(String email);

    User findUserById(Long id);

    List<User> findAll(int startPosition, int maxResult);

    Long getRoleIdByUserId(Long id);
}
