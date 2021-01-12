package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
	UserType findUserTypeById(int id);

}
