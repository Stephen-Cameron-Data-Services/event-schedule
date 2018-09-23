package au.com.scds.eventschedule.base.impl;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.event.MutableEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An Immutable Event (Interval).
 */

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "interval")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Interval")
@DomainObject(objectType = "Interval")
public class Event implements Comparable<Event> {
	
	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	protected DateTime start;
	
	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	protected DateTime end;

	@Persistent(mappedBy = "event")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingSet = new TreeSet<>();
	
	protected Event() {
		setStart(new DateTime());
	}

	public Event(DateTime start, DateTime end) {
		if(start == null)
			throw new IllegalArgumentException("start parameter cannot be null");
		setStart(start);
		setEnd(end);
	}
	
	public SortedSet<Booking> getBookings() {
		return Collections.unmodifiableSortedSet(this.getBookingSet());
	}
	
	void addBooking(Booking booking) {
		if (!this.getBookingSet().contains(booking))
			this.getBookingSet().add(booking);
	}

	void removeBooking(Booking booking) {
		if (this.getBookingSet().contains(booking))
			bookingRepo.destroyBooking(booking);
	}
	
	@Action
	public Event createBooking(Booker booker, Bookable bookable) {
		bookingRepo.createBooking(bookable, booker, this);
		return this;
	}

	@Override
	public int compareTo(Event other) {
		if (this == other) {
			return 0;
		} else {
			// most recent first
			int result = other.getStart().compareTo(this.getStart());
			if (result != 0) {
				return result;
			} else {
				return other.getEnd().compareTo(this.getEnd());
			}
		}
	}
	
	@Inject
	BookingsRepository bookingRepo;

}
