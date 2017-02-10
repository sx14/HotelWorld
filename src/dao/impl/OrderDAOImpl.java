package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.OrderDAO;
import model.Order;

public class OrderDAOImpl implements OrderDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<Order> get(int uid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Order where uid ="+uid+" order by ";
		return null;
	}

}
