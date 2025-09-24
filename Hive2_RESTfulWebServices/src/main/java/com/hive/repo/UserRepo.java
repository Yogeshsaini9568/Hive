package com.hive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hive.models.User;

public interface UserRepo extends JpaRepository<User, String> {


}
