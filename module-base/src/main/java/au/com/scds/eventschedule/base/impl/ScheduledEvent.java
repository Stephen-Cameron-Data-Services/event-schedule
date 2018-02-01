package au.com.scds.eventschedule.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendance;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.EventFacilitator;
import au.com.scds.eventschedule.base.impl.Attendee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "event")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.VALUE_MAP, column="type", value="ScheduledEvent")
@DomainObject(objectType="ScheduledEvent")
public class ScheduledEvent extends BaseEvent {

	@Column(allowsNull = "true")
	@Getter()
	@Setter(value = AccessLevel.PACKAGE)
	protected Organisation organisation;
	@Column(allowsNull = "false")
	@Title()
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

	public ScheduledEvent(Organisation organisation, String name, DateTime date) {
		this.setOrganisation(organisation);
		this.setName(name);
		this.setStart(date);
	}

	public TranslatableString title() {
		return TranslatableString.tr("{name}", "name", this.getName());
	}
	
	@Action
	public ScheduledEvent addBooking(Attendee attendee) {
		this.createBooking(attendee);
		return this;
	}
	
	public List<Attendee> choices0AddBooking(){
		return baseRepo.listAttendees();
	}

	@Action
	public ScheduledEvent removeBooking(Booking booking) {
		if (this.getBookings().contains(booking)) {
			this.getBookings().remove(booking);
			//baseRepo.destroyBooking(booking);
		}
		return this;
	}
	
	public List<Booking> choices0RemoveBooking(){
		return this.getBookings();
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
	
	public List<Attendee> choices0AddAttendance(){
		return baseRepo.listAttendees();
	}

	@Action
	public ScheduledEvent removeAttendance(Attendance attendance) {
		if (this.getAttendances().contains(attendance)){
			this.getAttendances().remove(attendance);
			//baseRepo.destroyAttendance(attendance);
		}
		return this;
	}
	
	public List<Attendance> choices0RemoveAttendance(){
		return this.getAttendances();
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
	
	public List<Attendee> choices0AddWaitListed(){
		return baseRepo.listAttendees();
	}

	@Action
	public ScheduledEvent removeWaitListed(Attendee attendee) {
		if (this.getWaitList().contains(attendee))
			this.getWaitList().remove(attendee);
		return this;
	}
	
	public List<Attendee> choices0RemoveWaitListed(){
		return this.getWaitList();
	}

	public final List<Attendee> getWaitListed() {
		return Collections.unmodifiableList(this.getWaitList());
	}

	@Action
	public ScheduledEvent addFacilitator(EventFacilitator facilitator) {
		this.getFacilitators().add(facilitator);
		return this;
	}
	
	public List<EventFacilitator> choices0AddFacilitator(){
		return baseRepo.listEventFacilitators();
	}

	@Action
	public ScheduledEvent removeFacilitator(EventFacilitator facilitator) {
		if (this.getFacilitators().contains(facilitator))
			this.getFacilitators().remove(facilitator);
		return this;
	}
	
	public List<EventFacilitator> choices0RemoveFacilitator(){
		return this.getFacilitators();
	}

	public final List<EventFacilitator> getFacilitatorsList() {
		return Collections.unmodifiableList(this.getFacilitators());
	}

	@Inject
	EventScheduleBaseRepository baseRepo;

}
