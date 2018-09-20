package au.com.scds.eventschedule.base.impl;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.eventschedule.base.impl.activity.Attendee;
import au.com.scds.eventschedule.base.impl.event.MutableEvent;

@DomainService(nature = NatureOfService.DOMAIN)
public class BookingsRepository {

	public Booking createBooking(MutableEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		Booking object = new Booking(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public void destroyBooking(Booking booking) {
		booking.getEvent().removeBooking(booking);
		booking.getBooker().removeBooking(booking);
		repositoryService.removeAndFlush(booking);
	}

	@Inject
	RepositoryService repositoryService;
}
