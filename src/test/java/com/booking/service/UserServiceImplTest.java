package com.booking.service;

import com.booking.model.Users;
import com.booking.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    private Users user = null;

    @BeforeEach
    void setup() {
        user = new Users("Gautam", "Male");
        user.setId(1);
    }

    @Test
    void saveUser() {
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
    }

    @Test
    void updateUser() {
        when(usersRepository.findByName("Gautam")).thenReturn(user);
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
    }
}