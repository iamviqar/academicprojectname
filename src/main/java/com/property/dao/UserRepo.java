package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public List<User> findAll();
	public List<User> findByUserRoleId(Long roleId);
	public User findOneById(Long id);
	public User findOneByUserNameAndPassword(String userName,String password);
	
}
