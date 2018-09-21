package au.com.scds.eventschedule.base.impl;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * An thing that can be booked/reserved.
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

	@Persistent(mappedBy = "booked")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingsSet = new TreeSet<>();
	
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
		return Collections.unmodifiableSortedSet(this.getBookingsSet());
	}

	void addBooking(Booking booking) {
		if (!this.getBookingsSet().contains(booking))
			this.getBookingsSet().add(booking);
	}

	void removeBooking(Booking booking) {
		if (this.getBookingsSet().contains(booking))
			this.getBookingsSet().remove(booking);
	}

	public Bookable createBooking(Booker booker, Event interval) {
		return this;
	}

	@Override
	public int compareTo(Bookable o) {
		return this.getIdentifier().compareTo(o.getIdentifier());
	}
}
