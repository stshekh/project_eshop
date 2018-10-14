package com.gmail.sshekh.service;


import com.gmail.sshekh.service.dto.BusinessCardDTO;
import com.gmail.sshekh.service.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void delete(Long id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAll();

    List<UserDTO> findAll(int startPosition, int maxResult);

    void setDiscount(UserDTO userDTO);

    UserDTO findUserById(Long id);

    void setEnabled(UserDTO userDTO);

    Set<BusinessCardDTO> getBusinessCards(Long id);

    BusinessCardDTO deleteUsersBusinessCard(Long id);

    Integer countUsers();
}
