package dao;

import model.User;

public interface UserDAO {
	public boolean saveOrUpdate(User user);
	public User get(User user);
}
