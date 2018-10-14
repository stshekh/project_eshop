package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.BusinessCardDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("businessCardConverter")
public class BusinessCardConverter implements Converter<BusinessCardDTO, BusinessCard> {
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;

    @Override
    public BusinessCard toEntity(BusinessCardDTO dto) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setId(dto.getId());
        businessCard.setFullName(dto.getFullName());
        businessCard.setTitle(dto.getTitle());
        businessCard.setWorkingTelephone(dto.getWorkingTelephone());
        return businessCard;
    }

    @Override
    public List<BusinessCard> toEntityList(List<BusinessCardDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<BusinessCard> toEntitySet(Set<BusinessCardDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
