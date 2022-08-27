package com.booking.service;

import com.booking.model.Users;

public interface UserService {
    public Users saveUser(Users user);

    public Users updateUser(Users user);
}
