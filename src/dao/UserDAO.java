package dao;

import model.User;

public interface UserDAO {
	public boolean saveOrUpdate(User user);
	public User get(String phone);
	public boolean remove(User user);
}
