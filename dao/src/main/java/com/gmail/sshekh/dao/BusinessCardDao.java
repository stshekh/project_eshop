package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.BusinessCard;

import java.util.List;

public interface BusinessCardDao extends GenericDao<BusinessCard> {

    List<BusinessCard> getBusinessCardsByUserId(Long id);
}
