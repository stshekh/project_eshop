package com.gmail.sshekh.service.converter.impl.dto;


import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.BusinessCardDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("businessCardDTOConverter")
public class BusinessCardDTOConverter implements DTOConverter<BusinessCard, BusinessCardDTO> {

    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public BusinessCardDTO toDTO(BusinessCard entity) {
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setId(entity.getId());
        businessCardDTO.setFullName(entity.getFullName());
        businessCardDTO.setTitle(entity.getTitle());
        businessCardDTO.setWorkingTelephone(entity.getWorkingTelephone());
        return businessCardDTO;
    }

    @Override
    public List<BusinessCardDTO> toDTOList(List<BusinessCard> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<BusinessCardDTO> toDTOSet(Set<BusinessCard> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
