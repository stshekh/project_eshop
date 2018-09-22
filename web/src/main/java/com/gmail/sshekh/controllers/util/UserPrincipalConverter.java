package com.gmail.sshekh.controllers.util;

import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.controllers.model.UserPrincipal;

public class UserPrincipalConverter {

    public static UserPrincipal toUserPrincipal(UserDTO user) {
        return UserPrincipal.newBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName() + " " + user.getSurname())
                .role(user.getRole().getIdRole())
                .build();
    }
}
