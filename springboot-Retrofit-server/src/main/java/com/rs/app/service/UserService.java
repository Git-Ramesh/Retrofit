package com.rs.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.rs.app.exception.UserAlreadyAvailableException;
import com.rs.app.exception.UserNotAvailableException;
import com.rs.app.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private static final List<User> USERS = new ArrayList<>();

	static {
		log.info("Default users added..");
		USERS.add(new User(1, "EMP100", "Ramesh", "ramesh@rsageventures.com", "Hyderabad"));
		USERS.add(new User(2, "EMP101", "Jagadeesh", "jagadeesh@test.com", "Nemali"));
	}

	public User saveUser(User user) {
		if (checkUserAvailability(user)) {
			throw new UserAlreadyAvailableException("User with " + user.getEmpno() + " already available");
		}
		USERS.add(user);
		try {
			return getUser(user.getId(), user.getEmpno());
		} catch (NoSuchElementException nseex) {
			throw new UserNotAvailableException("Unable to save user");
		}
	}

	public User getAvailableUser(long id) {
		sleep(5000);
		try {
			return getUser(id);
		} catch (NoSuchElementException nseex) {
			throw new UserNotAvailableException("User not found!");
		}
	}
	
	public List<User> getAvailableUsers() {
		sleep(5000);
		return USERS;
	}

	public User removeExistedUser(long id) {
		User user = getAvailableUser(id);
		USERS.remove(user);
		return user;
	}

	private boolean checkUserAvailability(User user) {
		return USERS.stream().anyMatch(existedUser -> existedUser.getEmpno().equals(user.getEmpno()));
	}

	private User getUser(long id, String empno) {
		return USERS.stream().filter(Objects::nonNull)
				.filter(existedUser -> (existedUser.getEmpno().equals(empno) && existedUser.getId() == id)).findFirst()
				.orElseThrow();
	}

	private User getUser(long id) {
		return USERS.stream().filter(Objects::nonNull).filter(existedUser -> existedUser.getId() == id).findFirst()
				.orElseThrow();
	}
	
	private static final void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
