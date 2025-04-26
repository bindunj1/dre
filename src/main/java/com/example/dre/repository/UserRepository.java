package com.example.dre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dre.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(String gf);

}
