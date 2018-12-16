package com.rs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.model.User;
import com.rs.app.service.UserService;

@RestController
@RequestMapping(value = UserController.USER_CONTROLLER_BASE_URI)
public class UserController {
	protected static final String USER_CONTROLLER_BASE_URI = "/user";

	private @Autowired UserService userService;

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = this.userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User savedUser = this.userService.getAvailableUser(id);
		return ResponseEntity.ok(savedUser);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> availableUsers = this.userService.getAvailableUsers();
		return ResponseEntity.ok(availableUsers);
	}

	@DeleteMapping
	public ResponseEntity<User> removeUser(@RequestParam("id") long id) {
		User savedUser = this.userService.removeExistedUser(id);
		return ResponseEntity.ok(savedUser);
	}
}
