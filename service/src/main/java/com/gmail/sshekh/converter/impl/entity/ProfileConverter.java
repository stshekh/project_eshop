package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Profile;
import com.gmail.sshekh.dto.ProfileDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileConverter implements Converter<ProfileDTO, Profile> {
    @Override
    public Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setUserId(dto.getUserId());
        profile.setAddress(dto.getAddress());
        profile.setTelephone(dto.getTelephone());
        return profile;
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Profile> toEntitySet(Set<ProfileDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}

