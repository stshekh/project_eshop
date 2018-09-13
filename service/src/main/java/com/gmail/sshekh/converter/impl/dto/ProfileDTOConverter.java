package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.dto.ProfileDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileDTOConverter implements DTOConverter<Profile, ProfileDTO> {
    @Override
    public ProfileDTO toDTO(Profile entity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserId(entity.getUserId());
        profileDTO.setAddress(entity.getAddress());
        profileDTO.setTelephone(entity.getTelephone());
        return profileDTO;
    }

    @Override
    public List<ProfileDTO> toDTOList(List<Profile> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
