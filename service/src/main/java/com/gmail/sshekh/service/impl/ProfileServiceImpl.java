package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ProfileDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.service.ProfileService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.ProfileDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("profileDTOConverter")
    private DTOConverter<Profile, ProfileDTO> profileDTOConverter;
    @Autowired
    @Qualifier("profileConverter")
    private Converter<ProfileDTO, Profile> profileConverter;

    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        Profile profile = profileConverter.toEntity(profileDTO);
        UserPrincipal userPrincipal = getLoggedInUser();
        profile.setUserId(userPrincipal.getId());
        profile.setUser(userDao.findUserById(profile.getUserId()));
        profileDao.create(profile);
        return profileDTOConverter.toDTO(profile);
    }

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        UserPrincipal userPrincipal = getLoggedInUser();
        Profile profile = profileConverter.toEntity(profileDTO);
        profile.setUserId(userPrincipal.getId());
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

    @Override
    public ProfileDTO findProfileById(Long id) {
        Profile profile = profileDao.findProfileById(id);
        return profileDTOConverter.toDTO(profile);
    }
}
