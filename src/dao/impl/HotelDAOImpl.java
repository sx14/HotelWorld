package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.ApplyState;
import dao.HotelDAO;
import model.Hotel;

public class HotelDAOImpl implements HotelDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<Hotel> get(String city) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Hotel where city='"+city+"' and state="+ApplyState.PASS.getValue();
		Query query = session.createQuery(hql);
		List hotels = query.getResultList();
		session.close();
		return hotels;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean saveOrUpdate(Hotel hotel) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(session.merge(hotel));
		transaction.commit();
		session.close();
		return true;
	}
	@Override
	public List<Hotel> get(ApplyState state) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Hotel where state='"+state.getValue()+"'";
		Query query = session.createQuery(hql);
		List<Hotel> hotels = query.getResultList();
		transaction.commit();
		session.close();
		return hotels;
	}
	@Override
	public List<Hotel> getByUid(int uid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Hotel where uid="+uid;
		Query query = session.createQuery(hql);
		List<Hotel> hotels = query.getResultList();
		transaction.commit();
		session.close();
		return hotels;
	}
	@Override
	public List<Hotel> getByHid(int hid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Hotel where hid="+hid;
		Query query = session.createQuery(hql);
		List<Hotel> hotels = query.getResultList();
		transaction.commit();
		session.close();
		return hotels;
	}

}
