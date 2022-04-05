package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
	public List<UserRole> findAll();
	public UserRole findOneById(Long id);
}
