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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An entity that holds bookings of specific <class>Bookable</class> entities.
 */
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "booker")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Booker")
@DomainObject(objectType = "Booker")
public class Booker implements Comparable<Booker> {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	private String identifier;

	@Persistent(mappedBy = "booker")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingsSet = new TreeSet<>();
	
	protected Booker(){
		this.setIdentifier("");
	}
	
	public Booker(String identifier){
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

	@Action
	public Booker removeBooking(Booking booking) {
		if (this.getBookingsSet().contains(booking))
			bookingsRepo.destroyBooking(booking);
		return this;
	}

	@Override
	public int compareTo(Booker o) {
		return this.getIdentifier().compareTo(o.getIdentifier());
	}

	@Inject
	BookingsRepository bookingsRepo;
}
