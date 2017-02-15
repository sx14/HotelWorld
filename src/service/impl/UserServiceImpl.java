package service.impl;

import dao.UserDAO;
import dao.VisaDAO;
import model.User;
import model.Visa;
import service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	private VisaDAO visaDAO;
	public VisaDAO getVisaDAO() {
		return visaDAO;
	}
	public void setVisaDAO(VisaDAO visaDAO) {
		this.visaDAO = visaDAO;
	}
	@Override
	public User registerQuickly(User user) {
		boolean result = userDAO.saveOrUpdate(user);
		User u = null;
		if (result == true) {
			u = userDAO.get(user);
		}
		return u;
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
	@Override
	public boolean saveOrUpdate(User user) {
		return userDAO.saveOrUpdate(user);
	}
//	@Override
//	public boolean saveOrUpdate(Visa visa) {
//		return visaDAO.saveOrUpdate(visa);
//	}
	
	

}
