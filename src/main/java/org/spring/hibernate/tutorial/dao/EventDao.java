package org.spring.hibernate.tutorial.dao;

import java.util.Date;
import java.util.List;

import org.spring.hibernate.tutorial.domain.Event;

public interface EventDao {
	public Long createAndStoreEvent(String title, Date theDate);
	public List<Event> listEvents();
}
