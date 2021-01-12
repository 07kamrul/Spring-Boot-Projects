package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUserId(int userId);

	User findUserByEmailAndPassword(String email, String password);

	User findUserByEmail(String email);

}
