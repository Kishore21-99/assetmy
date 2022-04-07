package com.sprint1.assets.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.assets.entity.User;
import com.sprint1.assets.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

// create/add new user into database
	@Transactional
	@Override
	public User createUser(User user) {
		return userRepository.save(user);

	}

// view all the users in database
	@Override
	public List<User> viewUsers() {
		return userRepository.findAll();
	}

// used to update the user
	@Transactional
	@Override
	public String updateUser(int userId,User user) {
		if (userRepository.existsById(userId)) {

			userRepository.save(user);
			return "Successfully updated";
		}
		return null;
	}

// finds the user based on userId
	@Override
	public User findUserById(int userId) {
		if (userRepository.existsById(userId)) {
			return userRepository.getById(userId);
		}
		return null;

	}

//delete the user
	@Transactional
	@Override
	public boolean deleteUserById(int userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

//for logging in into the application
	@Override
	public String loginService(int userId, String userPassword) {

//used custom query to get password
		if (userRepository.existsById(userId)) {
			String password = userRepository.getPassword(userId);
			if (password.equals(userPassword)) {
				User user = userRepository.getById(userId);
				if (user.getRole().equalsIgnoreCase("user")) {
					logger.info("Logged in as User");
					return user.getRole();
				} else if (user.getRole().equalsIgnoreCase("Admin")) {
					logger.info("Logged in as Administrator");
					return user.getRole();
				} else if (user.getRole().equalsIgnoreCase("Manager")) {
					logger.info("Logged in as Warehouse Manager");
					return user.getRole();
				}
			}
		}

		return null;
	}
}