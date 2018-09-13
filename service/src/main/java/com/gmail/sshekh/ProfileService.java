package com.gmail.sshekh;

import com.gmail.sshekh.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

    ProfileDTO save(ProfileDTO profileDTO);

    ProfileDTO update(ProfileDTO profileDTO);

    List<ProfileDTO> findAll();

    void delete(ProfileDTO profileDTO);
}
