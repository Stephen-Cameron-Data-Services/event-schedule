package au.com.scds.eventschedule.base.impl.timeslot;

import javax.inject.Inject;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.BaseEvent;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.EventsRepository;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "timeslot")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Timeslot")
public class Timeslot extends BaseEvent {

	@Getter
	@Setter
	protected Booking booking;

	@Action
	public Timeslot createBooking(Attendee attendee) {
		this.setBooking(events.createBooking(this, attendee));
		return this;
	}

	public String disableCreateBooking() {
		if (getBooking() != null) {
			return "Booked";
		} else {
			return "";
		}
	}

	@Inject
	protected EventsRepository events;

}
