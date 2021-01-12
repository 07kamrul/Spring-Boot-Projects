package JobIT.Service;

import JobIT.Model.User;

public interface UserService {
	public User findByEmail(String email);

	public void saveUser(User user);

	public void saveAdmin(User user);

	public boolean isUserAlreadyPresent(User user);

}
