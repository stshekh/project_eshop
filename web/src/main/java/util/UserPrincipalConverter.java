package util;

import com.gmail.sshekh.dto.UserDTO;
import model.UserPrincipal;

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
