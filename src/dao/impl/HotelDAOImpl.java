package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.ApplyState;
import constant.HotelStar;
import dao.HotelDAO;
import model.Hotel;

public class HotelDAOImpl implements HotelDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<Hotel> get(String city, int level) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Hotel where city='"+city+"' and state="+ApplyState.OPEN.getValue();
		HotelStar[] levels = HotelStar.values();
		for(int i = 0 ; i < levels.length ; i++){
			if (levels[i].getValue() == level && level != HotelStar.N.getValue()) {
				hql = hql + " and star=" + level;
			}
		}
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
