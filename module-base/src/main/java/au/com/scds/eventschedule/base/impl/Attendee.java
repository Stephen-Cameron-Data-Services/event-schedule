package au.com.scds.eventschedule.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.fixturescripts.FixtureResult;

import au.com.scds.eventschedule.base.impl.Attendance;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendee")
@DomainObject()
public class Attendee {

	@Persistent(mappedBy = "attendee")
	@Order(column = "attendee_bookings_idx")
	@Getter(value = AccessLevel.PACKAGE)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Booking> bookings = new ArrayList<>();

	@Persistent(mappedBy = "attendee")
	@Order(column = "attendee_attendances_idx")
	@Getter(value = AccessLevel.PACKAGE)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Attendance> attendances = new ArrayList<>();

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PRIVATE)
	protected Person person;

	private Attendee() {
	}

	public Attendee(Person person) {
		this.setPerson(person);
	}

	@NotPersistent
	public String getFullname() {
		return this.getPerson().getFullname();
	}

	public List<Booking> getBookingsList() {
		return Collections.unmodifiableList(this.getBookings());
	}

	public List<Attendance> getAttendancesList() {
		return Collections.unmodifiableList(this.getAttendances());
	}

	public void removeBooking(Booking booking) {
		if (this.getBookings().contains(booking))
			this.getBookings().remove(booking);
	}

	public void removeAttendance(Attendance attendance) {
		if (this.getAttendances().contains(attendance))
			this.getAttendances().remove(attendance);
	}
}
