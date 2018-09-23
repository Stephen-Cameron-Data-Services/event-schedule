package au.com.scds.eventschedule.base.menu;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Bookable;
import au.com.scds.eventschedule.base.impl.Booker;
import au.com.scds.eventschedule.base.impl.BookingsRepository;
import au.com.scds.eventschedule.base.impl.Event;
import au.com.scds.eventschedule.base.impl.contact.ContactAllocation;
import au.com.scds.eventschedule.base.impl.contact.Contactee;
import au.com.scds.eventschedule.base.impl.contact.Contactor;
import au.com.scds.eventschedule.base.impl.contact.ContactsRepository;
import au.com.scds.eventschedule.base.impl.contact.ScheduledContact;
import au.com.scds.eventschedule.base.impl.event.EventsRepository;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "BookingsMenu")
@DomainServiceLayout(named = "Bookings", menuOrder = "10")
public class BookingMenu {

	@Action
	@MemberOrder(sequence = "1")
	public Booker createBooker(@ParameterLayout(named = "Identifier") String identifier) {
		return repo.createBooker(identifier);
	}

	@Action
	@MemberOrder(sequence = "2")
	public Bookable createBookable(@ParameterLayout(named = "Full Name") String identifier) {
		return repo.createBookable(identifier);
	}

	@Action
	@MemberOrder(sequence = "3")
	public Event createEvent(@ParameterLayout(named = "Start date-time") DateTime date,
			@ParameterLayout(named = "End date-time") DateTime end) {
		return repo.createEvent(date, end);
	}

	@Action
	@MemberOrder(sequence = "4")
	public List<Booker> listBookers() {
		return repo.listBookers();
	}

	@Action
	@MemberOrder(sequence = "5")
	public List<Bookable> ListBookables() {
		return repo.listBookables();
	}

	@Action
	@MemberOrder(sequence = "6")
	public List<Event> ListEvents() {
		return repo.listEvents();
	}

	@Inject
	BookingsRepository repo;
}
