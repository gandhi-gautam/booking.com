package com.booking.repository;

import com.booking.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersRepositoryTest {

    public static final String NAME = "Gautam";
    public static final String GENDER = "Male";
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void findByName() {

        //Create new User
        Users user = new Users();
        user.setName(NAME);
        user.setGender(GENDER);

        usersRepository.save(user);

        Users actualUser = usersRepository.findByName(NAME);
        assertThat(actualUser.getName()).isEqualTo(NAME);
        assertThat(actualUser.getGender()).isEqualTo(GENDER);
    }
}