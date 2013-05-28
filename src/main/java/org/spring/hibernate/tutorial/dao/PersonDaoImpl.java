package org.spring.hibernate.tutorial.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.hibernate.tutorial.domain.Event;
import org.spring.hibernate.tutorial.domain.Person;

public class PersonDaoImpl implements PersonDao {

	// private HibernateTransactionManager txManager;
	private SessionFactory sessionFactory;

	/*
	 * public void setTxManagerFactory(HibernateTransactionManager txManager) {
	 * this.txManager = txManager; this.session =
	 * this.txManager.getSessionFactory().getCurrentSession(); }
	 */

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long createAndStorePerson(String fname, String lname) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = new Person();
		aPerson.setFirstname(fname);
		aPerson.setLastname(lname);
		Long personId = (Long) session.save(aPerson);

		session.getTransaction().commit();

		return personId;
	}

	public void addPersonToEvent(Long personId, Long eventId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(anEvent);

		session.getTransaction().commit();
	}

	public void addEmailToPerson(Long personId, String emailAddress) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person) session.load(Person.class, personId);
		aPerson.getEmailAddresses().add(emailAddress);

		session.getTransaction().commit();
	}
}
