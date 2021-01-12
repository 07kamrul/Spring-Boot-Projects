package com.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.model.User;
import com.restapi.model.UserType;
import com.restapi.repository.UserRepository;
import com.restapi.repository.UserTypeRepository;
import com.restapi.util.MultimediaUtil;
import com.restapi.util.UserValidator;
import com.restapi.views.ResponseObject;
import com.restapi.views.UserViews;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserTypeRepository userTypeRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public UserViews getUserById(int id) {
		User user = userRepository.findUserByUserId(id);
		UserViews userViews = new UserViews(user);
		return userViews;
	}

	public Object login(String email, String password) {
		User user = userRepository.findUserByEmailAndPassword(email, password);

		if (user != null) {
			return new UserViews(user);
		} else {
			return new ResponseObject(0, "Invalid email or password");
		}
	}

	public Object registration(String firstName, String lastName, String email, String password, int userTypeId) {
		/*
		 * if (!UserValidator.validateEmail(email)) { return new ResponseObject(-5,
		 * "This is invaild email!"); }
		 * 
		 * validate the first name
		 * 
		 * if (!UserValidator.validateFirstname(firstName)) { return new
		 * ResponseObject(-6, "First Name Is Not Valid!"); }
		 * 
		 * validate last name
		 * 
		 * if (!UserValidator.validateLastname(lastName)) {
		 * 
		 * return new ResponseObject(-7, "Last Name Is Not Valid!"); }
		 * 
		 * 
		 * validate password
		 * 
		 * 
		 * if (!UserValidator.validatePassword(password)) { return new
		 * ResponseObject(-8, "invallid password!"); }
		 */

		/*
		 * if (profilePic == null) { return new ResponseObject(-4,
		 * "Please add your profile picture!"); }
		 */

		UserType userType = userTypeRepository.findUserTypeById(userTypeId);
		User user = userRepository.findUserByEmail(email);

		/*
		 * if (userType == null) { return new ResponseObject(-3, "Invalid user type!");
		 * } if (user != null) { return new ResponseObject(-2, "This user is exits"); }
		 */
//		String imagePath = MultimediaUtil.uploadImages(profilePic);

		user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
//		user.setProfilePic(imagePath);
		user.setUserType(userType);

		return new UserViews(user);
	}

}
