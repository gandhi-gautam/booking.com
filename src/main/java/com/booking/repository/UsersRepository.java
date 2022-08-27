package com.booking.repository;

import com.booking.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * This method find user by name
     *
     * @param name
     * @return
     */
    @Query("from Users where name = :name")
    Users findByName(String name);
}
