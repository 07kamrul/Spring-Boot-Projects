package com.loginregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loginregistration.model.User;
import com.loginregistration.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/register/all")
	public List<User> getAlluser() {
		return userRepository.findAll();
	}

	@PostMapping("/register/add")
	public User register(@RequestBody User user) {
		return userRepository.save(user);
	}

    @PostMapping("/login")
    public User Login(@RequestBody User user) {
        User oldUSer = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return oldUSer;
    }
//	@PostMapping("/login")
//	@ResponseBody
//	public Object login(@RequestParam("email") String email, @RequestParam("password") String password) {
//		User user = userRepository.findByEmailAndPassword(email, password);
//		return user;
//
//	}

}
