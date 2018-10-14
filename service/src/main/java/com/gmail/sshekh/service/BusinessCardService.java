package com.gmail.sshekh.service;

import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.service.dto.BusinessCardDTO;

import java.util.List;

public interface BusinessCardService {

    BusinessCardDTO save(BusinessCardDTO businessCard, Long id);

    void deleteBusinessCardById(Long id);

    List<BusinessCardDTO> getBusinessCardsByUserId(Long id);
}
