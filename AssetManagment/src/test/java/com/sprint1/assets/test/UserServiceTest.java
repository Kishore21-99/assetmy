package com.sprint1.assets.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sprint1.assets.entity.User;
import com.sprint1.assets.exception.UserNotFoundException;
import com.sprint1.assets.repository.UserRepository;
import com.sprint1.assets.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepo;

	private User u1;

	@BeforeEach
	void setUp() {
		u1 = new User();
		u1.setUserId(1);
		u1.setUserName("Kishore N");
		u1.setRole("Admin");
		u1.setUserPassword("Ki21$8848");

	}

	@Test
	void testCreateUser() {

		Mockito.when(userRepo.save(u1)).thenReturn(u1);
		assertThat(userService.createUser(u1)).isEqualTo(u1);
	}

	@Test
	void testupdateUser() throws Exception {
		Mockito.when(userRepo.existsById(1)).thenReturn(true);
		Mockito.when(userRepo.save(u1)).thenReturn(u1);
		assertThat(userService.updateUser(1, u1)).isEqualTo("Successfully updated");
	}

	@Test
	void testFindUserById() throws UserNotFoundException {
		Mockito.when(userRepo.getById(1)).thenReturn(u1);
		Mockito.when(userRepo.existsById(u1.getUserId())).thenReturn(true);
		assertThat(userService.findUserById(u1.getUserId())).isEqualTo(u1);
	}

	@Test
	void testDeleteUserById() throws UserNotFoundException {

		Mockito.when(userRepo.existsById(2)).thenReturn(true);
		assertThat(userService.deleteUserById(2)).isEqualTo(true);
	}
}
