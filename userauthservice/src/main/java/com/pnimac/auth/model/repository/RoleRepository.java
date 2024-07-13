package com.pnimac.auth.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pnimac.auth.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	public Optional<Role> findByRolename(String rolename);
}
