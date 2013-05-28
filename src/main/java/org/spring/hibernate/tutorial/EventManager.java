package org.spring.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.hibernate.tutorial.dao.EventDao;
import org.spring.hibernate.tutorial.dao.PersonDao;
import org.spring.hibernate.tutorial.domain.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventManager {
	final static Logger logger = LoggerFactory.getLogger(EventManager.class);
	private static ApplicationContext applicationContext;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Initializing Spring context.");

		applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

		logger.info("Spring context initialized.");

		EventDao eventDao = applicationContext.getBean("myEventDao", EventDao.class);
		PersonDao personDao = applicationContext.getBean("myPersonDao", PersonDao.class);

		if (args[0].equals("store")) {
			Long eventId = eventDao.createAndStoreEvent("Spring-Hibernate", new Date());
			System.out.println("Created New Event : " + eventId);
		} else if (args[0].equals("list")) {
			List<Event> events = eventDao.listEvents();
			for (int i = 0; i < events.size(); i++) {
				Event theEvent = events.get(i);
				System.out.println("Event: " + theEvent.getTitle() + " Time: " + theEvent.getDate());
			}
		} else if (args[0].equals("person")) {
			Long personId = personDao.createAndStorePerson("Partha", "Saha");
			System.out.println("Created New Person : " + personId);
		} else if (args[0].equals("addpersontoevent")) {
			Long eventId = eventDao.createAndStoreEvent("Spring-Hibernate", new Date());
			System.out.println("Created New Event : " + eventId);
			
			Long personId = personDao.createAndStorePerson("Foo", "Bar");
			System.out.println("Created New Person : " + personId);
			
			personDao.addPersonToEvent(personId, eventId);
			System.out.println("Added person " + personId + " to event " + eventId);
		} else if (args[0].equals("addEmailToPerson")) {
			if (args[1] != null && args[2] != null) {
				personDao.addEmailToPerson(Long.parseLong(args[1]), args[2]);
				System.out.println("Added person " + args[1] + " with email : " + args[2]);
			}

		}

	}
}
