package dao.impl;

import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
	@Override
	public boolean saveOrUpdate(Visa visa) {
		Session session = SessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(visa);
		transaction.commit();
		session.close();
		return true;
	}
	
}	
