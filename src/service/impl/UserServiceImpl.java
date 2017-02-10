package service.impl;

import dao.UserDAO;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	@Override
	public boolean registerQuickly(User user) {
		return userDAO.add(user);
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public User login(User user) {
		return userDAO.get(user);
	}
	
	

}
