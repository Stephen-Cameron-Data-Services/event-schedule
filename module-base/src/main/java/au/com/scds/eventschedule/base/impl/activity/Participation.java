package au.com.scds.eventschedule.base.impl.activity;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "participation")
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "BaseParticipation")
@DomainObject(objectType = "BaseParticipation")
public class Participation extends Booking {

	public Participation(ScheduledEvent event, Attendee attendee) {
		super(event, attendee);
	}

}