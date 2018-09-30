package au.com.scds.eventschedule.base.impl.timeslot;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Bookable;

@DomainService(nature = NatureOfService.DOMAIN)
public class TimeslotRepository {

	public Timeslot createTimeslot(Bookable bookable, DateTime start, DateTime end) {
		if (start == null)
			return null;
		Timeslot timeslot = new Timeslot(bookable, start, end);
		repositoryService.persistAndFlush(timeslot);
		return timeslot;
	}

	public void destroyTimeslot(Timeslot timeslot) {
		if (timeslot == null)
			return;
		if (timeslot.isBooked())
			timeslot.removeBooking();
		repositoryService.removeAndFlush(timeslot);
	}

	public BookableWithTimeslots createBookableWithTimeslots(String identifier) {
		BookableWithTimeslots bookable = new BookableWithTimeslots(identifier);
		repositoryService.persistAndFlush(bookable);
		return bookable;
	}
	
	@Inject
	RepositoryService repositoryService;
}
