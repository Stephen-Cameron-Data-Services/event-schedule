package au.com.scds.eventschedule.base.impl;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.isisaddons.wicket.fullcalendar2.cpt.applib.CalendarEvent;
import org.isisaddons.wicket.fullcalendar2.cpt.applib.CalendarEventable;
import org.isisaddons.wicket.fullcalendar2.cpt.applib.Calendarable;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@DomainObject()
public class CalendarableScheduledEvent extends ScheduledEvent implements Calendarable {
	
	public static final String DEFAULT_CALENDAR_NAME = "Scheduled Events";

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected String calendarName;
	
	public CalendarableScheduledEvent(Organisation organisation, String name, String calendarName, Date date) {
		super(organisation, name, date);
		this.setCalendarName((calendarName != null) ? calendarName : this.DEFAULT_CALENDAR_NAME);
	}

	// Calendarable
	@Override
	public Set<String> getCalendarNames() {
		return Stream.of(this.getCalendarName()).collect(Collectors.toSet());
	}

	@Override
	public ImmutableMap<String, CalendarEventable> getCalendarEvents() {
		return ImmutableMap.of(this.getCalendarName(), new CalendarEvent2());
	}

	private class CalendarEvent2 implements CalendarEventable {

		@Override
		public String getCalendarName() {
			return CalendarableScheduledEvent.this.getCalendarName();
		}

		@Override
		public CalendarEvent toCalendarEvent() {
			return new CalendarEvent(new DateTime(CalendarableScheduledEvent.this.getDate()),
					CalendarableScheduledEvent.this.getCalendarName(), CalendarableScheduledEvent.this.getName());
		}
	}
	// end Calendarable
}
