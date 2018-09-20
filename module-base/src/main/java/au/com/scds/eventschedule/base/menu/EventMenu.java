package au.com.scds.eventschedule.base.menu;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.activity.Attendee;
import au.com.scds.eventschedule.base.impl.event.CalendarableScheduledEvent;
import au.com.scds.eventschedule.base.impl.event.EventFacilitator;
import au.com.scds.eventschedule.base.impl.event.EventsRepository;
import au.com.scds.eventschedule.base.impl.event.Organisation;
import au.com.scds.eventschedule.base.impl.event.ScheduledEvent;
import au.com.scds.eventschedule.base.impl.event.SimpleEvent;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "EventsMenu")
@DomainServiceLayout(named = "Events", menuOrder = "20")
public class EventMenu {

	@Action
	@MemberOrder(sequence = "1")
	public SimpleEvent createSimpleEvent() {
		return repo.createSimpleEvent();
	}

	@Action
	@MemberOrder(sequence = "2")
	public ScheduledEvent createScheduledEvent(@ParameterLayout(named = "Name") String name, DateTime date) {
		return repo.createScheduledEvent(null, name, date);
	}

	@Action
	@MemberOrder(sequence = "3")
	public CalendarableScheduledEvent createCalendarableScheduledEvent(
			@ParameterLayout(named = "Event Name") String eventName,
			@ParameterLayout(named = "Calendar Name") String calendarName,
			@ParameterLayout(named = "Date-time") DateTime date, 
			@ParameterLayout(named = "Calendar Note") String note) {
		return repo.createCalendarableScheduledEvent(null, eventName, calendarName, date, note);
	}

	@Action
	@MemberOrder(sequence = "4")
	public Attendee createEventAttendee(@ParameterLayout(named = "Full Name") String fullname) {
		return repo.createAttendee(fullname);
	}

	@Action
	@MemberOrder(sequence = "5")
	public EventFacilitator createEventFacilitator(@ParameterLayout(named = "Full Name") String fullname) {
		return repo.createEventFacilitator(fullname);
	}

	@Action
	@MemberOrder(sequence = "6")
	public List<SimpleEvent> listSimpleEvents() {
		return repo.listSimpleEvent();
	}

	@Action
	@MemberOrder(sequence = "7")
	public List<ScheduledEvent> listScheduledEvents() {
		return repo.listScheduledEvent();
	}

	@Action
	@MemberOrder(sequence = "8")
	public List<CalendarableScheduledEvent> listCalendarableScheduledEvent() {
		return repo.listCalendarableScheduledEvent();
	}

	@Inject
	EventsRepository repo;
}
