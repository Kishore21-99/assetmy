package com.sprint1.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.assets.entity.User;
import com.sprint1.assets.exception.UserNotFoundException;
import com.sprint1.assets.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

//Creating User
	@PostMapping(path = "/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u1 = userService.createUser(user);
		if (u1 == null) {
			return new ResponseEntity("Sorry! user not created", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(u1, HttpStatus.CREATED);
	}

//update User role
	@PutMapping(path = "/updateUser/{userId}")
	public String updateUserRole(@PathVariable("userId") int userId, @RequestBody User user)
			throws UserNotFoundException {
		String u1 = userService.updateUser(userId, user);
		if (u1 == null) {
			throw new UserNotFoundException("User not found");
		}
		return "role is updated successfully";

	}

//find user by id
	@GetMapping(path = "/findUser/{userId}")
	public User findUserById(@PathVariable int userId) throws UserNotFoundException {
		User u1 = userService.findUserById(userId);
		if (u1 == null) {
			throw new UserNotFoundException("Enter valid userId");
		}
		return u1;

	}

//delete by id
	@DeleteMapping(path = "/deleteUser/{userId}")
	public String deleteUserById(@PathVariable int userId) throws UserNotFoundException {
		boolean u1 = userService.deleteUserById(userId);
		if (u1 == false) {
			throw new UserNotFoundException("User was not found");
		}
		return "user deleted successfully";

	}

}