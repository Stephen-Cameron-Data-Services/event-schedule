package au.com.scds.eventschedule.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Attendance;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.EventFacilitator;
import au.com.scds.eventschedule.base.impl.Attendee;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "event")
@DomainObject()
public class ScheduledEvent {

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected Organisation organisation;
	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected Date date;
	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected String name;
	@Persistent(mappedBy = "event")
	@Getter()
	@Setter()
	protected List<Booking> bookings = new ArrayList<>();
	@Persistent(mappedBy = "event")
	@Getter()
	@Setter()
	protected List<Attendance> attendances = new ArrayList<>();
	@Persistent
	@Join
	@Getter()
	@Setter()
	protected List<Attendee> waitList = new ArrayList<>();
	@Persistent
	@Join
	@Getter()
	@Setter()
	protected List<EventFacilitator> facilitators = new ArrayList<>();

	public ScheduledEvent() {
	}

	public ScheduledEvent(Organisation organisation, String name, Date date) {
		this.setOrganisation(organisation);
		this.setName(name);
		this.setDate(date);
	}

	@Action
	public ScheduledEvent addBooking(Attendee attendee) {
		this.createBooking(attendee);
		return this;
	}

	public Booking createBooking(Attendee attendee) {
		Booking booking = baseRepo.createBooking(this, attendee);
		this.getBookings().add(booking);
		return booking;
	}

	@Action
	public ScheduledEvent addAttendance(Attendee attendee) {
		this.getAttendances().add(baseRepo.createAttendance(this, attendee));
		return this;
	}

	public Attendance createAttendance(Attendee attendee) {
		Attendance attendance = baseRepo.createAttendance(this, attendee);
		this.getAttendances().add(attendance);
		return attendance;
	}

	@Action
	public ScheduledEvent addWaitListed(Attendee attendee) {
		this.getWaitList().add(attendee);
		return this;
	}
	
	@Action
	public ScheduledEvent addFacilitator(EventFacilitator facilitator) {
		this.getFacilitators().add(facilitator);
		return this;
	}

	@Inject
	EventScheduleBaseRepository baseRepo;



}
