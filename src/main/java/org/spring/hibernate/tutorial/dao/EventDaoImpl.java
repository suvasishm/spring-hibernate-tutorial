package org.spring.hibernate.tutorial.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.hibernate.tutorial.domain.Event;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

public class EventDaoImpl implements EventDao {

	private HibernateTransactionManager txManager;
	private SessionFactory sessionFactory;

	public void setTxManagerFactory(HibernateTransactionManager txManager) {
		this.txManager = txManager;
		this.sessionFactory = this.txManager.getSessionFactory();
	}

	/*
	 * public void setSessionFactory(SessionFactory sessionFactory) {
	 * this.sessionFactory = sessionFactory; }
	 */

	public Long createAndStoreEvent(String title, Date theDate) {
		Session session = this.sessionFactory.getCurrentSession();
		
		session.beginTransaction();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		Long eventId = (Long) session.save(theEvent);

		session.getTransaction().commit();
		
		return eventId;
	}
	
	public List<Event> listEvents() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}

}
