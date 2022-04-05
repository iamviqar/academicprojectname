package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.UserRepo;
import com.property.entity.User;
import com.property.entity.UserRole;
import com.property.service.IUserService;


@Service
public class UserService implements IUserService {

	@Autowired
	UserRepo userRepo;
		
	@Override
	public User getById(Long id) throws Exception {
		User agreement = null;
		try {
			agreement = userRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	public User save(User agreement) throws Exception {
		try {
			userRepo.save(agreement);
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	public User signUp(User user) throws Exception {
		try {
			UserRole userRole = new UserRole();
			userRole.setId(1L);
			user.setUserRole(userRole);
			userRepo.save(user);
		}catch(Exception e) {
			throw e;
		}
		return user;
	}
	
	@Override
	public User login(User user) throws Exception {
		User authUser = null;
		try {
			authUser = userRepo.findOneByUserNameAndPassword(user.getUserName(), user.getPassword());
			
		if(authUser==null ) {
			throw new Exception("Invalid Credentials");
		}
	
		}catch(Exception e) {
			throw e;
		}
		return authUser;
	}


	@Override
	public List<User> getAll() throws Exception {
		List<User> users = null;
		try {
			
			List<User> usersFromDao = userRepo.findAll();
			if(usersFromDao!=null && !usersFromDao.isEmpty()) {
				//users = userAssembler.removePersistantBags(usersFromDao);
			}
			
		}catch(Exception e) {
			throw e;
		}
		return users;
	}

	
}
