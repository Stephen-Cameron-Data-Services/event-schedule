package au.com.scds.eventschedule.base.impl;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Attendance;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="attendee")
@DomainObject()
public class Attendee {

	@Persistent(mappedBy="attendee")
	@Getter()
	@Setter()
    protected List<Booking> bookings;

	@Persistent(mappedBy="attendee")
	@Getter()
	@Setter()
    protected List<Attendance> attendances;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
    protected Person person;
	
	public Attendee(Person person) {
		this.setPerson(person);
	}
}
