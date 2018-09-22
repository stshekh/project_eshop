package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.ProfileDao;
import com.gmail.sshekh.dao.model.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    private static Logger logger = LogManager.getLogger(ProfileDaoImpl.class);

    public ProfileDaoImpl() {
        super(Profile.class);
    }
}
