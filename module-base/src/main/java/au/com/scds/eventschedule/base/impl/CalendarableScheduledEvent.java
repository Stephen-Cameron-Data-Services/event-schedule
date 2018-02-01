package au.com.scds.eventschedule.base.impl;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
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
@Discriminator(value="CalendarableScheduledEvent")
@DomainObject(objectType="CalendarableScheduledEvent")
public class CalendarableScheduledEvent extends ScheduledEvent implements CalendarEventable /*Calendarable*/ {

	public static final String DEFAULT_CALENDAR_NAME = "Scheduled Events";

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected String calendarName;

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected String calendarNote;

	public CalendarableScheduledEvent(Organisation organisation, String name, String calendarName, DateTime date,
			String note) {
		super(organisation, name, date);
		this.setCalendarName((calendarName != null) ? calendarName : this.DEFAULT_CALENDAR_NAME);
		this.setCalendarNote(note);
	}

	@Override
	public CalendarEvent toCalendarEvent() {
		return new CalendarEvent(this.getStart(),
				this.getCalendarName(), this.getName(),
				this.getCalendarNote());
	}
	
/*
	@Override
	@NotPersistent
	public Set<String> getCalendarNames() {
		return Stream.of(this.getCalendarName()).collect(Collectors.toSet());
	}

	@Override
	@NotPersistent
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
					CalendarableScheduledEvent.this.getCalendarName(), CalendarableScheduledEvent.this.getName(),
					CalendarableScheduledEvent.this.getCalendarNote());
		}
	}*/
}
