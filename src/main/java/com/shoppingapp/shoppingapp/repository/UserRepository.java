package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.User;
import com.shoppingapp.shoppingapp.model.UserOriginalSanityCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {}
