package au.com.scds.eventschedule.base.impl;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * A thing that can be booked/reserved.
 * 
 */
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "bookable")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Bookable")
@DomainObject(objectType = "Bookable")
public class Bookable implements Comparable<Bookable> {

	@Column(allowsNull = "true")
	@Unique()
	@Getter()
	@Setter()
	private String identifier;

	@Persistent(mappedBy = "bookable")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingSet = new TreeSet<>();
	
	protected Bookable(){
		this.setIdentifier("");
	}
	
	public Bookable(String identifier){
		this.setIdentifier(identifier);
	}

	public String title() {
		return getIdentifier();
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
			bookingsRepo.destroyBooking(booking);
	}

	@Action
	public Bookable createBooking(Booker booker, Event event) {
		bookingsRepo.createBooking(this, booker, event);
		return this;
	}
	
	public List<Booker> choices0CreateBooking(){
		return bookingsRepo.listBookers();
	}
	
	public List<Event> choices1CreateBooking(){
		return bookingsRepo.listEvents();
	}
	
	@Override
	public int compareTo(Bookable o) {
		return this.getIdentifier().compareTo(o.getIdentifier());
	}
	
	@Inject
	BookingsRepository bookingsRepo;
}
