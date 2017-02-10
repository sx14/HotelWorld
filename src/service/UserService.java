package service;

import model.User;

public interface UserService {
	public boolean registerQuickly(User user);
	public User login(User user);
}
