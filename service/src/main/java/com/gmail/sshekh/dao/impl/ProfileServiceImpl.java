package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.ProfileService;
import com.gmail.sshekh.converter.impl.dto.ProfileDTOConverter;
import com.gmail.sshekh.converter.impl.entity.ProfileConverter;
import com.gmail.sshekh.dao.ProfileDao;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.dto.ProfileDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    private ProfileDao profileDao = new ProfileDaoImpl(Profile.class);
    private ProfileDTOConverter profileDTOConverter = new ProfileDTOConverter();
    private ProfileConverter profileConverter = new ProfileConverter();

    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Profile profile = profileConverter.toEntity(profileDTO);
            profileDao.create(profile);
            ProfileDTO profileDTONew = profileDTOConverter.toDTO(profile);
            transaction.commit();
            logger.info("Profile was saved");
            return profileDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot save profile", e);
        }
        return profileDTO;
    }

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Profile profile = profileConverter.toEntity(profileDTO);
            profileDao.update(profile);
            ProfileDTO profileDTONew = profileDTOConverter.toDTO(profile);
            transaction.commit();
            logger.info("Profile was updated");
            return profileDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot update profile", e);
        }
        return profileDTO;
    }

    @Override
    public List<ProfileDTO> findAll() {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Profile> profiles = profileDao.findAll();
            List<ProfileDTO> profileDTOS = profileDTOConverter.toDTOList(profiles);
            transaction.commit();
            logger.info("Profile was updated");
            return profileDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot update profile", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(ProfileDTO profileDTO) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Profile profile = profileConverter.toEntity(profileDTO);
            profileDao.delete(profile);
            transaction.commit();
            logger.info("Profile was deleted");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot delete profile", e);
        }
    }
}
