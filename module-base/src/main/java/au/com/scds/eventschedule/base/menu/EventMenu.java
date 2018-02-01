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
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.CalendarableScheduledEvent;
import au.com.scds.eventschedule.base.impl.EventFacilitator;
import au.com.scds.eventschedule.base.impl.EventScheduleBaseRepository;
import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;
//import domainapp.modules.simple.dom.impl.SimpleObject;
import au.com.scds.eventschedule.base.impl.SimpleEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "EventsMenu"
)
@DomainServiceLayout(
        named = "Events",
        menuOrder = "20"
)
public class EventMenu {
	
	@Action
	public Attendee createAttendee(String fullname) {
		return repo.createAttendee(fullname);
	}
	
	@Action
	public EventFacilitator createFacilitator(String fullname) {
		return repo.createEventFacilitator(fullname);
	}

	@Action
	public ScheduledEvent createScheduledEvent( String name, DateTime date) {
		return repo.createScheduledEvent(null, name, date);
	}
	
	@Action
	public List<ScheduledEvent> listScheduledEvents() {
		return repo.listScheduledEvent();
	}
	
	@Action
	public SimpleEvent createSimpleEvent() {
		return repo.createSimpleEvent();
	}
	
	@Action
	public CalendarableScheduledEvent createCalendarableScheduledEvent( String eventName, String calendarName, DateTime date, String note) {
		return repo.createCalendarableScheduledEvent(null, eventName, calendarName, date, note);
	}
	
	@Action
	public List<CalendarableScheduledEvent> listCalendarableScheduledEvent() {
		return repo.listCalendarableScheduledEvent();
	}

	@Inject
	EventScheduleBaseRepository repo;
}
