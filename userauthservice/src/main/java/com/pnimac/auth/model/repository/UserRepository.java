package com.pnimac.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pnimac.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
