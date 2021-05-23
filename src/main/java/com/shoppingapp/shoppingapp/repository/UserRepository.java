package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT U FROM User U")
    List<User> findAllUsers();

    @Query(value = "SELECT U FROM User U WHERE U.user_id = ?1")
    User findUserById(String user_id);
}