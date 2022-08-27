package com.booking.service.impl;

import com.booking.exception.ResourceNotFoundException;
import com.booking.model.Users;
import com.booking.repository.UsersRepository;
import com.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * This saves the user in the database;
     *
     * @param user
     * @return
     */
    @Override
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    /**
     * This method updates the user in the database
     *
     * @param updatedUser
     * @return
     */
    @Override
    public Users updateUser(Users updatedUser) {
        Users user = usersRepository.findByName(updatedUser.getName());
        if (user != null) {
            user.setGender(updatedUser.getGender());
            user.setName(updatedUser.getName());
        } else {
            throw new ResourceNotFoundException("User not found");
        }
        return saveUser(user);
    }
}
