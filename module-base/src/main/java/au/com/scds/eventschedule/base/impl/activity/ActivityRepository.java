package au.com.scds.eventschedule.base.impl.activity;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;

@DomainService(nature = NatureOfService.DOMAIN)
public class ActivityRepository {

	public ActivityEvent createActivityEvent(Organisation organisation, String eventName, String calendarName,
			DateTime date, String note) {
		if (eventName == null || date == null)
			return null;
		ActivityEvent object = new ActivityEvent(organisation, eventName, calendarName, date, note);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public RecurringActivityEvent createRecurringActivityEvent(Organisation organisation, String eventName, String calendarName,
			DateTime date, String note) {
		if (eventName == null || date == null)
			return null;
		RecurringActivityEvent object = new RecurringActivityEvent(organisation, eventName, calendarName, date, note);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public ParentedActivityEvent createParentedActivityEvent(RecurringActivityEvent recurringActivityEvent, DateTime date) {
		ParentedActivityEvent child = new ParentedActivityEvent(recurringActivityEvent, recurringActivityEvent.getName(), "Activity", date, null);
		repositoryService.persistAndFlush(child);
		return child;
	}

	public List<ActivityEvent> listActivityEvents() {
		return repositoryService.allInstances(ActivityEvent.class);
	}
	
	public List<RecurringActivityEvent> listRecurringActivityEvents() {
		return repositoryService.allInstances(RecurringActivityEvent.class);
	}

	public Participation createParticipation(ActivityEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		Participation object = new Participation(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public Attendance createAttendance(ScheduledEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		Attendance object = new Attendance(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public void destroyAttendance(Attendance attendance) {
		//attendance.getEvent().getAttendances().remove(attendance);
		//attendance.getAttendee().getAttendances().remove(attendance);
		repositoryService.removeAndFlush(attendance);
	}

	@Inject
	RepositoryService repositoryService;

	public Booking createBooking(RecurringActivityEvent baseRecurringActivityEvent, Attendee attendee) {
		// TODO Auto-generated method stub
		return null;
	}



}
