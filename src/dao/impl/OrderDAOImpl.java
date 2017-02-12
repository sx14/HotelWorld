package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.sun.org.apache.xpath.internal.operations.Or;

import dao.OrderDAO;
import model.Order;

public class OrderDAOImpl implements OrderDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<Order> get(int uid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Order where uid ="+uid+" order by create_date desc";
		Query query = session.createQuery(hql);
		List<Order> orders = query.getResultList();
		transaction.commit();
		session.close();
		return orders;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
