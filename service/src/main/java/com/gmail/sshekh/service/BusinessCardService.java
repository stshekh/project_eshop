package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.BusinessCardDTO;

import java.util.List;

public interface BusinessCardService {

    BusinessCardDTO save(BusinessCardDTO businessCard);

    void deleteBusinessCardById(Long id);

    List<BusinessCardDTO> getUsersBusinessCards();

    List<BusinessCardDTO> getUsersBusinessCards(Long id);
}
