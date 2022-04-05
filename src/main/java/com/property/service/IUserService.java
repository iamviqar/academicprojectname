package com.property.service;

import java.util.List;

import com.property.entity.User;

public interface IUserService {

	public List<User> getAll() throws Exception;
	public User getById(Long id) throws Exception;
	public User save(User user) throws Exception;
	public User signUp(User user) throws Exception;
	public User login(User user) throws Exception;
	
	
}
