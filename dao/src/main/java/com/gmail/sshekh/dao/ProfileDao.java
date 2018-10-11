package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Profile;

public interface ProfileDao extends GenericDao<Profile> {
    Profile findProfileById(Long id);
}
