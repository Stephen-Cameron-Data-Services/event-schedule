package au.com.scds.eventschedule.base.menu;

import java.util.Date;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.EventFacilitator;
import au.com.scds.eventschedule.base.impl.EventScheduleBaseRepository;
import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;
//import domainapp.modules.simple.dom.impl.SimpleObject;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "EventSchedule.EventMenu"
)
@DomainServiceLayout(
        named = "Events",
        menuOrder = "10"
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
	public ScheduledEvent createScheduledEvent( String name, Date date) {
		return repo.createScheduledEvent(null, name, date);
	}

	@Inject
	EventScheduleBaseRepository repo;



}
