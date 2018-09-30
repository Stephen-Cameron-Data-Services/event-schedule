package au.com.scds.eventschedule.base.impl.timeslot;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import au.com.scds.eventschedule.base.impl.Bookable;
import au.com.scds.eventschedule.base.impl.Booker;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.BookingsRepository;
import au.com.scds.eventschedule.base.impl.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An extension of Event, that is a member of a linked-list usually associated
 * with a Bookable, cannot have overlapping Timeslots intervals in such a list.
 */

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "timeslot")
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Timeslot")
public class Timeslot extends Event {

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Bookable bookable;

	Timeslot(Bookable bookable, DateTime start, DateTime end) {
		super(start, end);
		// enforce end not null
		if (end == null)
			throw new IllegalArgumentException("End cannot be null");
		this.setBookable(bookable);
	}

	public Interval asInterval() {
		return new Interval(getStart(), getEnd());
	}

	public boolean isBooked() {
		return !this.getBookingSet().isEmpty();
	}

	@NotPersistent
	public Booking getBooking() {
		if (isBooked())
			return this.getBookingSet().first();
		else
			return null;
	}

	@Override
	public Event createBooking(Booker booker, Bookable bookable) {
		throw new UnsupportedOperationException("Use alernative single parameter method provided");
	}

	@Action
	public Timeslot createBooking(Booker booker) {
		if (!isBooked())
			super.createBooking(booker, this.getBookable());
		return this;
	}

	public String disableCreateBooking() {
		if (isBooked())
			return "A Timeslot can have only one Booking";
		else
			return "";
	}

	@Action
	public Timeslot removeBooking() {
		if (isBooked())
			bookingsRepo.destroyBooking(this.getBooking());
		return this;
	}

	@Inject
	BookingsRepository bookingsRepo;
}
