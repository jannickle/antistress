package com.example.antistressdagbog.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenLoginGetRequest_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void givenPerformLoginRequest_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login").accept(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers.redirectedUrl("/login?error"));
    }

    @Test
    public void givenPerformLogoutRequest_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/perform_logout").accept(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isFound());
    }

}