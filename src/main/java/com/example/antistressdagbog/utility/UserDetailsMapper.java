package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.model.UserCredentials;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toUserDetails(UserCredentials userCredentials) {
        return User.withUsername(userCredentials.getUsername())
                .password(userCredentials.getPassword())
                .roles(userCredentials.getRoles().toArray(String[]::new))
                .build();
    }

}
