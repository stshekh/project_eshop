package com.gmail.sshekh;


import com.gmail.sshekh.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void delete(UserDTO userDTO);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAll();
}
