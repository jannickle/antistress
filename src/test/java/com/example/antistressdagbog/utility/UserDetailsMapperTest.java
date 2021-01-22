package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.UserCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsMapperTest {

    UserDetailsMapper mapper = new UserDetailsMapper();

    @Test
    public void testUserDetailsMapperNotNull(){
        UserCredentials credentials = new UserCredentials();
        credentials.setAccount(new Account());
        credentials.setEnabled(true);
        credentials.setUsername("testusername");
        credentials.setPassword("testpassword");
        credentials.setRoles(new HashSet<String>() {});
        UserDetails user = mapper.toUserDetails(credentials);
        assertNotNull(user);
    }

    @Test
    public void testUserDetailsMapperUsername(){
        String actual = "testusername";
        UserCredentials credentials = new UserCredentials();
        credentials.setAccount(new Account());
        credentials.setEnabled(true);
        credentials.setUsername(actual);
        credentials.setPassword("testpassword");
        credentials.setRoles(new HashSet<String>() {});
        UserDetails user = mapper.toUserDetails(credentials);
        assertEquals(user.getUsername(), actual);
    }

    @Test
    public void testUserDetailsMapperPassword(){
        String actual = "testpassword";
        UserCredentials credentials = new UserCredentials();
        credentials.setAccount(new Account());
        credentials.setEnabled(true);
        credentials.setUsername("testusername");
        credentials.setPassword(actual);
        credentials.setRoles(new HashSet<String>() {});
        UserDetails user = mapper.toUserDetails(credentials);
        assertEquals(user.getPassword(), actual);
    }

}