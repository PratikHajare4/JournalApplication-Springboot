package com.pratik.JournalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    @Disabled
    public void testSaveNewUser(){
       Assertions.assertNotNull(userRepository.getUSerForSA());
    }
}
