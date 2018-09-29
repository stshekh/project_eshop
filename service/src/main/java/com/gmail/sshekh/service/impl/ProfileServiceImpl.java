package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ProfileDao;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.service.ProfileService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.converter.impl.entity.ProfileConverter;
import com.gmail.sshekh.service.dto.ProfileDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    @Qualifier("profileDTOConverter")
    private DTOConverter<Profile, ProfileDTO> profileDTOConverter;
    @Autowired
    @Qualifier("profileConverter")
    private Converter<ProfileDTO, Profile> profileConverter;

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