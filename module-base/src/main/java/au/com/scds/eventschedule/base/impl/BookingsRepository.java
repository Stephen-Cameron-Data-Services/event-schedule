package au.com.scds.eventschedule.base.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

@DomainService(nature = NatureOfService.DOMAIN)
public class BookingsRepository {

	public Booking createBooking(Bookable bookable, Booker booker, Event event) {
		if (booker == null || event == null)
			return null;
		Booking booking = new Booking(bookable, booker, event);
		repositoryService.persistAndFlush(booking);
		if (bookable != null)
			bookable.addBooking(booking);
		booker.addBooking(booking);
		event.addBooking(booking);
		return booking;
	}

	public void destroyBooking(Booking booking) {
		// booking.getEvent().removeBooking(booking);
		// booking.getBooker().removeBooking(booking);
		repositoryService.removeAndFlush(booking);
	}

	public Booker createBooker(String identifier) {
		if (identifier == null)
			return null;
		Booker booker = new Booker(identifier);
		repositoryService.persistAndFlush(booker);
		return booker;
	}

	public Bookable createBookable(String identifier) {
		if (identifier == null)
			return null;
		Bookable bookable = new Bookable(identifier);
		repositoryService.persistAndFlush(bookable);
		return bookable;
	}

	public Event createEvent(DateTime start, DateTime end) {
		if (start == null)
			return null;
		Event bookable = new Event(start, end);
		repositoryService.persistAndFlush(bookable);
		return bookable;
	}

	public List<Booker> listBookers() {
		return repositoryService.allInstances(Booker.class);
	}

	public List<Bookable> listBookables() {
		return repositoryService.allInstances(Bookable.class);
	}

	public List<Event> listEvents() {
		return repositoryService.allInstances(Event.class);
	}
	
	public List<Booking> listBookings() {
		return repositoryService.allInstances(Booking.class);
	}

	@Inject
	RepositoryService repositoryService;
}
