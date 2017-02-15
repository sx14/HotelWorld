package service;


import model.User;
import model.Visa;

public interface UserService {
	public User registerQuickly(User user);
	public User login(User user);
	public boolean saveOrUpdate(User user);
}
