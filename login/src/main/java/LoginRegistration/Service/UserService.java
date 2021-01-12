package LoginRegistration.Service;

import LoginRegistration.Model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
}