package au.com.scds.eventschedule.base.impl;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An entity that holds bookings of <class>Bookable</class>.
 */
@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Booker {
	
	public abstract String title();
	
	@Persistent(mappedBy = "booker")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingsSet = new TreeSet<>();
	
	public SortedSet<Booking> getBookings() {
		return Collections.unmodifiableSortedSet(this.getBookingsSet());
	}

	public void addBooking(Booking booking) {
		if (!this.getBookingsSet().contains(booking))
			this.getBookingsSet().add(booking);
	}

	public void removeBooking(Booking booking) {
		if (this.getBookingsSet().contains(booking))
			this.getBookingsSet().remove(booking);
	}

}
