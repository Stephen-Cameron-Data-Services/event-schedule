package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendance")
@DomainObject()
public class Attendance {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	public ScheduledEvent event;

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	public Attendee attendee;

	public Attendance() {
	}

	public Attendance(ScheduledEvent event, Attendee attendee) {
		this.setEvent(event);
		this.setAttendee(attendee);
	}

}
