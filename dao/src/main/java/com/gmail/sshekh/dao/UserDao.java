package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao extends GenericDao<User> {

    User findUserByEmail(String email);

    User findUserById(Long id);

    List<User> findAll(int startPosition, int maxResult);

    Long getRoleIdByUserId(Long id);

    Set<BusinessCard> getUsersBusinessCards(Long id);

    Long countUsers();
}
