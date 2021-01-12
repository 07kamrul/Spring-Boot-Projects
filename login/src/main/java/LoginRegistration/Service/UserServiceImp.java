package LoginRegistration.Service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import LoginRegistration.Model.Role;
import LoginRegistration.Model.User;
import LoginRegistration.Repository.RoleRepository;
import LoginRegistration.Repository.UserRepository;

@Service("userService")
public class UserServiceImp implements UserService {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 @Override
	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  user.setActive(1);
	  Role userRole = roleRespository.findByRole("ADMIN");
	  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);
	 }
}
