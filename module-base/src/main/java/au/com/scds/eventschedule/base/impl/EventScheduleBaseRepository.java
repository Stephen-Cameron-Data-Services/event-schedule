/*
 *
 *  Copyright 2015 Stephen Cameron Data Services
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package au.com.scds.eventschedule.base.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;


@DomainService(nature = NatureOfService.DOMAIN)
public class EventScheduleBaseRepository {

	public Attendee createAttendee(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		Attendee object = new Attendee(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Attendance createAttendance(ScheduledEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		Attendance object = new Attendance(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public void destroyAttendance(Attendance attendance) {
		attendance.getEvent().getAttendances().remove(attendance);
		attendance.getAttendee().getAttendances().remove(attendance);
		repositoryService.removeAndFlush(attendance);
	}

	public Booking createBooking(ScheduledEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		Booking object = new Booking(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public void destroyBooking(Booking booking) {
		booking.getEvent().getBookings().remove(booking);
		booking.getAttendee().getBookings().remove(booking);
		repositoryService.removeAndFlush(booking);
	}

	public Contactee createContactee(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		Contactee object = new Contactee(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactee createContactee(Person person) {
		if (person == null)
			return null;
		Contactee object = new Contactee(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactor createContactor(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		Contactor object = new Contactor(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactor createContactor(Person person) {
		if (person == null)
			return null;
		Contactor object = new Contactor(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public EventFacilitator createEventFacilitator(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		EventFacilitator object = new EventFacilitator(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Organisation createOrganisation(String name) {
		if (name == null)
			return null;
		Organisation object = new Organisation(name);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Person createPerson(String name) {
		if (name == null)
			return null;
		Person object = new Person(name);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, DateTime date) {
		if (contactor == null || contactee == null || date == null)
			return null;
		ScheduledContact object = new ScheduledContact(contactor, contactee, date);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public SimpleEvent createSimpleEvent() {
		SimpleEvent object = new SimpleEvent();
		repositoryService.persistAndFlush(object);
		return object;
	}

	public ScheduledEvent createScheduledEvent(Organisation organisation, String name, DateTime date) {
		if (name == null || date == null)
			return null;
		ScheduledEvent object = new ScheduledEvent(organisation, name, date);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public CalendarableScheduledEvent createCalendarableScheduledEvent(Organisation organisation, String eventName, String calendarName, DateTime date, String note) {
		if (eventName == null || date == null)
			return null;
		CalendarableScheduledEvent object = new CalendarableScheduledEvent(organisation, eventName, calendarName, date, note);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public ContactAllocation createContactAllocation(Contactor contactor, Contactee contactee) {
		if (contactor == null || contactee == null)
			return null;
		ContactAllocation object = new ContactAllocation(contactor, contactee);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public void destroyContactAllocation(ContactAllocation allocation) {
		repositoryService.removeAndFlush(allocation);
	}
	
	public List<ScheduledEvent> listScheduledEvent() {
		return repositoryService.allInstances(ScheduledEvent.class);
	}

	public List<CalendarableScheduledEvent> listCalendarableScheduledEvent() {
		return repositoryService.allInstances(CalendarableScheduledEvent.class);
	}

	public List<EventFacilitator> listEventFacilitators() {
		return repositoryService.allInstances(EventFacilitator.class);
	}

	public List<Attendee> listAttendees() {
		return repositoryService.allInstances(Attendee.class);
	}

	@Inject
	RepositoryService repositoryService;
}
