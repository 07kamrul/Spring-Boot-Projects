package com.restapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.model.User;
import com.restapi.model.UserType;
import com.restapi.repository.UserRepository;
import com.restapi.repository.UserTypeRepository;
import com.restapi.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user/login")
	@ResponseBody
	public Object login(@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("-----------------Out------------------");
		return userService.login(email, password);
	}

	// Registration
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user/registration")
	public List<User> getAlluser() {
		return userRepository.findAll();
	}

//	@ResponseBody
//	public Object registration(@RequestParam("email") String email, @RequestParam("password") String password,
//			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
//			@RequestParam("userTypeId") String userTypeId) throws NumberFormatException, IOException {
//		return userService.registration(firstName, lastName, email, password, Integer.valueOf(userTypeId));
//	}
	@PostMapping("/user/registration/add")
	public User registration(@RequestBody User user) {
		return userRepository.save(user);
	}

	@RequestMapping(value = "getHeaders", method = RequestMethod.POST)
	@ResponseBody
	public String getHeaders(@RequestHeader("token") String token) {
		return token;
	}

	@GetMapping("/user/usertype/all")
	public List<UserType> getAllUserType() {
		return userTypeRepository.findAll();
	}

	@GetMapping("/user/usertype/{id}")
	public UserType getUsertypeId(@PathVariable Integer id) {
		Optional<UserType> userType = userTypeRepository.findById(id);
		return userType.get();
	}

	@PostMapping("/user/usertype/add")
	public UserType createUserType(@RequestBody UserType userType) {
		return userTypeRepository.save(userType);
	}

	@DeleteMapping("/user/usertype/{id}")
	public void deleteUserType(@PathVariable("id") Integer id) {
		userTypeRepository.deleteById(id);

	}

}
