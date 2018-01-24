package au.com.scds.eventschedule.base.impl;

import java.util.Date;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(nature = NatureOfService.DOMAIN)
public class EventScheduleBaseRepository {

	public Attendance createAttendance(ScheduledEvent event, Attendee attendee) {
		Attendance object = new Attendance(event, attendee);
		repositoryService.persist(object);
		return object;
	}

	public Attendee createAttendee(String name) {
		Person person = new Person(name);
		repositoryService.persist(person);
		Attendee object = new Attendee(person);
		repositoryService.persist(object);
		return object;
	}

	public Booking createBooking(ScheduledEvent event, Attendee attendee) {
		Booking object = new Booking(event, attendee);
		repositoryService.persist(object);
		return object;
	}

	public Contactee createContactee(String name, Date date) {
		Contactee object = new Contactee();
		repositoryService.persist(object);
		return object;
	}

	public Contactor createContactor() {
		Contactor object = new Contactor();
		repositoryService.persist(object);
		return object;
	}

	public EventFacilitator createEventFacilitator(String name) {
		Person person = new Person(name);
		repositoryService.persist(person);
		EventFacilitator object = new EventFacilitator(person);
		repositoryService.persist(object);
		return object;
	}

	public Organisation createOrganisation() {
		Organisation object = new Organisation();
		repositoryService.persist(object);
		return object;
	}

	public Person createPerson(String name) {
		Person object = new Person(name);
		repositoryService.persist(object);
		return object;
	}
 
	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, Date date) {
		ScheduledContact object = new ScheduledContact(contactor, contactee, date);
		repositoryService.persist(object);
		return object;
	}

	public ScheduledEvent createScheduledEvent(Organisation organisation, String name, Date date) {
		ScheduledEvent object = new ScheduledEvent(organisation, name, date);
		repositoryService.persist(object);
		return object;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
}
