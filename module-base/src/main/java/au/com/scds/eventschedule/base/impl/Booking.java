package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "booking")
@DomainObject()
public class Booking {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected ScheduledEvent event;

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	public Attendee attendee;

	public Booking(ScheduledEvent event, Attendee attendee) {
		this.event = event;
		this.attendee = attendee;
	}
}
