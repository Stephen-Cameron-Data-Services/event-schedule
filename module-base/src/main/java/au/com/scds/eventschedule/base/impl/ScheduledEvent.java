package au.com.scds.eventschedule.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;


import au.com.scds.eventschedule.base.impl.Attendance;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.EventFacilitator;
import au.com.scds.eventschedule.base.impl.Attendee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "event")
@DomainObject()
public class ScheduledEvent  {

	@Column(allowsNull = "true")
	@Getter()
	@Setter(value = AccessLevel.PACKAGE)
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
	@Order(column = "event_booking_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Booking> bookings = new ArrayList<>();
	@Persistent(mappedBy = "event")
	@Order(column = "event_attendance_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Attendance> attendances = new ArrayList<>();
	@Persistent
	@Join
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Attendee> waitList = new ArrayList<>();
	@Persistent
	@Join
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
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

	@Action
	public ScheduledEvent removeBooking(Booking booking) {
		if (this.getBookings().contains(booking)) {
			baseRepo.destroyBooking(booking);
		}
		return this;
	}

	public Booking createBooking(Attendee attendee) {
		Booking booking = baseRepo.createBooking(this, attendee);
		this.getBookings().add(booking);
		return booking;
	}

	public List<Booking> getBookingsList() {
		return Collections.unmodifiableList(this.getBookings());
	}

	@Action
	public ScheduledEvent addAttendance(Attendee attendee) {
		this.createAttendance(attendee);
		return this;
	}

	@Action
	public ScheduledEvent removeAttendance(Attendance attendance) {
		if (this.getAttendances().contains(attendance))
			baseRepo.destroyAttendance(attendance);
		return this;
	}

	public Attendance createAttendance(Attendee attendee) {
		Attendance attendance = baseRepo.createAttendance(this, attendee);
		this.getAttendances().add(attendance);
		attendee.getAttendances().add(attendance);
		return attendance;
	}

	public List<Attendance> getAttendancesList() {
		return Collections.unmodifiableList(this.getAttendances());
	}

	@Action
	public ScheduledEvent addWaitListed(Attendee attendee) {
		this.getWaitList().add(attendee);
		return this;
	}

	@Action
	public ScheduledEvent removeWaitListed(Attendee attendee) {
		if (this.getWaitList().contains(attendee))
			this.getWaitList().remove(attendee);
		return this;
	}

	public final List<Attendee> getWaitListed() {
		return Collections.unmodifiableList(this.getWaitList());
	}

	@Action
	public ScheduledEvent addFacilitator(EventFacilitator facilitator) {
		this.getFacilitators().add(facilitator);
		return this;
	}

	@Action
	public ScheduledEvent removeFacilitator(EventFacilitator facilitator) {
		if (this.getFacilitators().contains(facilitator))
			this.getFacilitators().remove(facilitator);
		return this;
	}

	public final List<EventFacilitator> getFacilitatorsList() {
		return Collections.unmodifiableList(this.getFacilitators());
	}



	@Inject
	EventScheduleBaseRepository baseRepo;

}
