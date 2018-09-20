package au.com.scds.eventschedule.base.impl.activity;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.event.ScheduledEvent;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "participation")
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "Participation")
@DomainObject(objectType = "Participation")
public class Participation extends Booking {
	
	protected Participation() {
		super();
	}

	public Participation(ActivityEvent event, Attendee attendee) {
		super(event, attendee);
	}


	@NotPersistent
	public ActivityEvent getActivity() {
		return ((ActivityEvent) this.getEvent());
	}
	
	@NotPersistent
	public Attendee getAttendee() {
		return ((Attendee) this.getBooker());
	}

}
