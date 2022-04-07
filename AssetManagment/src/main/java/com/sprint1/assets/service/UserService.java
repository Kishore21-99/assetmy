package com.sprint1.assets.service;

import java.util.List;

import com.sprint1.assets.entity.User;
import com.sprint1.assets.exception.UserNotFoundException;

public interface UserService {
	public User createUser(User user);

	public String updateUser(int userId, User user) throws UserNotFoundException;

	public User findUserById(int userId) throws UserNotFoundException;

	public boolean deleteUserById(int userId) throws UserNotFoundException;

	public List<User> viewUsers() throws UserNotFoundException;

	public String loginService(int userId, String userpassword) throws UserNotFoundException;
}
