package dao;

import model.User;

public interface UserDAO {
	public boolean add(User user);
	public User get(User user);
}
