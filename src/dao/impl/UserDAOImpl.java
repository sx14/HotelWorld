package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import dao.UserDAO;
import model.User;

public class UserDAOImpl implements UserDAO{
	private SessionFactory sessionFactory;
	@Override
	public boolean saveOrUpdate(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
		session.close();
		return true;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User get(String phone) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from User where phone='"+phone+"'";
		Query query = session.createQuery(hql);
		List users = query.getResultList();
		if (!users.isEmpty()) {
			return (User) users.get(0);
		}
		session.close();
		return null;
	}
	
	
}
