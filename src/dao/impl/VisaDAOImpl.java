package dao.impl;

import org.hibernate.SessionFactory;

import dao.VisaDAO;
import model.Visa;

public class VisaDAOImpl implements VisaDAO{
	private SessionFactory SessionFactory;
	@Override
	public Visa get(String vid) {
		return new Visa("孙旭",100.0);
	}
	public SessionFactory getSessionFactory() {
		return SessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		SessionFactory = sessionFactory;
	}
	
}	
