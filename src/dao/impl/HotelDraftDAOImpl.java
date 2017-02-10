package dao.impl;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.ApplyState;
import dao.HotelDAO;
import dao.HotelDraftDAO;
import model.HotelDraft;

public class HotelDraftDAOImpl implements HotelDraftDAO{
	private SessionFactory sessionFactory;
	@Override
	public List<HotelDraft> get(ApplyState state) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from HotelDraft where state="+state;
		Query query = session.createQuery(hql);
		List<HotelDraft> hotelDrafts = query.getResultList();
		transaction.commit();
		session.close();
		return hotelDrafts;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
