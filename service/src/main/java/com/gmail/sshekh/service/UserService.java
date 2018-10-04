package com.gmail.sshekh.service;


import com.gmail.sshekh.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void delete(Long id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAll();

    List<UserDTO> findAll(int startPosition, int maxResult);

    void setDiscount(UserDTO userDTO);

    UserDTO findById(Long id);
}
