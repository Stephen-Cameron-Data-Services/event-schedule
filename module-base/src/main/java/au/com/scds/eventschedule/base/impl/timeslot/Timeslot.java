package au.com.scds.eventschedule.base.impl.timeslot;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Bookable;
import au.com.scds.eventschedule.base.impl.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An extension of Event, that is a member of a linked-list usually associated
 * with a Bookable, cannot have overlapping Timeslots intervals in such a list.
 *
 */

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "timeslot")
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Timeslot")
public class Timeslot extends Event {

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Bookable bookable;

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Timeslot next;

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Timeslot prev;

	Timeslot() {
		super();
	}
	
	public Timeslot(Bookable bookable){
		this.setBookable(bookable);
	}
}
