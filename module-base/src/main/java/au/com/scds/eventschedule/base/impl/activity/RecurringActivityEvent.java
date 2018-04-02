package au.com.scds.eventschedule.base.impl.activity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Organisation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "RecurringActivityEvent")
@DomainObject(objectType = "RecurringActivityEventt")
public class RecurringActivityEvent extends ActivityEvent{

	@Getter(value=AccessLevel.PROTECTED)
	@Setter(value=AccessLevel.PROTECTED)
	protected SortedSet<ParentedActivityEvent> childEventsSet = new TreeSet<>();
	
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
	public RecurringActivityEvent addChildEvent(DateTime date){
		ParentedActivityEvent child = activityRepo.createParentedActivityEvent(this, date);
		this.getChildEventsSet().add(child);
		return this;
	}
	
	@Action
	public RecurringActivityEvent removeChildEvent(){
		return this;
	}
	
	public SortedSet<ParentedActivityEvent> getChildEvents(){
		return Collections.unmodifiableSortedSet(this.getChildEventsSet());
	}
	
	@Inject
	ActivityRepository activityRepo;
}
