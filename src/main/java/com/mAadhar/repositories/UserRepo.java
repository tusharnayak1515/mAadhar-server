package com.mAadhar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mAadhar.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    public User findByMobile(String mobile);
}
