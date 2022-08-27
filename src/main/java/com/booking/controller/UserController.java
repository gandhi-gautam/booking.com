package com.booking.controller;

import com.booking.model.Users;
import com.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This method saves new user in the database
     *
     * @param user
     * @return
     */
    @PostMapping()
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return new ResponseEntity<Users>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * This method updates the user in the database
     *
     * @param user
     * @return
     */
    @PutMapping()
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }
}
