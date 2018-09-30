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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
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
        Profile profile = profileConverter.toEntity(profileDTO);
        profileDao.create(profile);
        return profileDTOConverter.toDTO(profile);
    }

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        Profile profile = profileConverter.toEntity(profileDTO);
        profileDao.update(profile);
        return profileDTOConverter.toDTO(profile);
    }

    @Override
    public List<ProfileDTO> findAll() {
        List<Profile> profiles = profileDao.findAll();
        return profileDTOConverter.toDTOList(profiles);
    }

    @Override
    public void delete(ProfileDTO profileDTO) {
        Profile profile = profileConverter.toEntity(profileDTO);
        profileDao.delete(profile);
    }
}
