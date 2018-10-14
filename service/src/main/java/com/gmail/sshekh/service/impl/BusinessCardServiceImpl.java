package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.BusinessCardDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.BusinessCardService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.BusinessCardDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BusinessCardServiceImpl implements BusinessCardService {
    @Autowired
    private BusinessCardDao businessCardDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("businessCardDTOConverter")
    private DTOConverter<BusinessCard, BusinessCardDTO> businessCardDTOConverter;
    @Autowired
    @Qualifier("businessCardConverter")
    private Converter<BusinessCardDTO, BusinessCard> businessCardConverter;


    @Override
    @Transactional
    public BusinessCardDTO save(BusinessCardDTO businessCardDTO, Long id) {
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        businessCard.setUser(userDao.findUserById(id));
        businessCardDao.create(businessCard);
        return businessCardDTOConverter.toDTO(businessCard);
    }

    @Override
    @Transactional
    public void deleteBusinessCardById(Long id) {
        businessCardDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<BusinessCardDTO> getBusinessCardsByUserId(Long id) {
        List<BusinessCard> businessCards = businessCardDao.getBusinessCardsByUserId(id);
        return businessCardDTOConverter.toDTOList(businessCards);
    }


}
