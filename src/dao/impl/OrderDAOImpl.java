package dao.impl;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import dao.OrderDAO;
import model.Order;

public class OrderDAOImpl implements OrderDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<Order> getByUser(int uid) {
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
	@Override
	public List<Order> getByHotel(int hid,Date inDate,Date outDate) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String in = format.format(inDate);
		String out = format.format(outDate);
		String hql = "from Order where hid"+hid+" and not(out_date<="+in+" or in_date>="+out+")";
		Query query = session.createQuery(hql);
		List<Order> orders = query.getResultList();
		transaction.commit();
		session.close();
		return orders;
	}
	@Override
	public boolean update(Order order) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(order);
		transaction.commit();
		session.close();
		return true;
	}
	@Override
	public boolean save(Order order) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(order);
		transaction.commit();
		session.close();
		return true;
	}
	@Override
	public Order getById(int oid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Order where oid="+oid;
		Query query = session.createQuery(hql);
		List<Order> orders = query.getResultList();
		transaction.commit();
		session.close();
		if (orders != null && orders.size() != 0) {			
			return orders.get(0);
		}else{
			return null;
		}
	}
	@Override
	public boolean save(List<Order> orders) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for(Order order : orders){
			session.save(order);
		}
		transaction.commit();
		session.close();
		return true;
	}
}
