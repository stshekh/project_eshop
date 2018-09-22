package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

    ProfileDTO save(ProfileDTO profileDTO);

    ProfileDTO update(ProfileDTO profileDTO);

    List<ProfileDTO> findAll();

    void delete(ProfileDTO profileDTO);
}
