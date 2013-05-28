package org.spring.hibernate.tutorial.dao;


public interface PersonDao {
	public Long createAndStorePerson(String fname, String lname);
	public void addPersonToEvent(Long personId, Long eventId);
	public void addEmailToPerson(Long personId, String emailAddress);
}
