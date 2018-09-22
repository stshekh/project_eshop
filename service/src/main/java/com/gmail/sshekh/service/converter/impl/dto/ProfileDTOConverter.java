package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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

    @Override
    public Set<ProfileDTO> toDTOSet(Set<Profile> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }

}
