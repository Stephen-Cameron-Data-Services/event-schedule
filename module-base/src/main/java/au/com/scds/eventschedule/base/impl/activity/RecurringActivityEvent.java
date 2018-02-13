package au.com.scds.eventschedule.base.impl.activity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "BaseRecurringActivityEvent")
@DomainObject(objectType = "BaseRecurringActivityEventt")
public class RecurringActivityEvent extends ActivityEvent{

	@Getter()
	@Setter()
	protected List<ParentedActivityEvent> childEvents = new ArrayList<>();
	
	public RecurringActivityEvent(Organisation organisation, String name, String calendarName, DateTime date,
			String note) {
		super(organisation, name, calendarName, date, note);
	}
	
	@Override
	public ActivityEvent addAttendance(Attendee attendee) {
		throw new java.lang.UnsupportedOperationException("Add Attendances to child Activities only");
	}

	@Override
	public ActivityEvent removeAttendance(Attendance attendance) {
		throw new java.lang.UnsupportedOperationException("Remove Attendances from child Activities only");
	}
	
	@Action
	public RecurringActivityEvent addChildEvent(){
		ParentedActivityEvent child = activityRepo.createParentedActivityEvent(this);
		this.getChildEvents().add(child);
		return this;
	}
	
	@Action
	public RecurringActivityEvent removeChildEvent(){
		return this;
	}
	
	@Inject
	ActivityBaseRepository activityRepo;
}
