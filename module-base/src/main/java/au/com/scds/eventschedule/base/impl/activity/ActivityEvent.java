/*
 *
 *  Copyright 2018 Stephen Cameron Data Services
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package au.com.scds.eventschedule.base.impl.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.CalendarableScheduledEvent;
import au.com.scds.eventschedule.base.impl.EventsRepository;
import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "ActivityEvent")
@Queries({ @Query(name = "findActivityByUpperCaseName", language = "JDOQL", value = "SELECT "
		+ "FROM au.com.scds.eventschedule.base.impl.activity.ActivityEvent "
		+ "WHERE name.trim().toUpperCase() == :name") })
public class ActivityEvent extends CalendarableScheduledEvent {

	@Persistent(mappedBy = "event")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Attendance> attendancesSet = new TreeSet<>();

	protected ActivityEvent() {
		super();
	}

	public ActivityEvent(Organisation organisation, String name, String calendarName, DateTime date, String note) {
		super(organisation, name, calendarName, date, note);
	}

	// Activities have Participations instead of Bookings
	@Action
	public ActivityEvent addParticipation(Attendee attendee) {
		this.createParticipation(attendee);
		return this;
	}

	public List<Attendee> choices0AddParticipation() {
		return baseRepo.listAttendees();
	}
	
	protected Participation createParticipation(Attendee attendee) {
		Participation participation = activityRepo.createParticipation(this, attendee);
		this.getBookingSet().add(participation);
		return participation;
	}

	@Action
	public ActivityEvent removeParticipation(Participation participation) {
		this.removeBooking(participation);
		return this;
	}

	public List<Participation> choices0RemoveParticipation() {
		List<Participation> list = new ArrayList();
		for (Booking booking : this.getBookingSet()) {
			list.add((Participation) booking);
		}
		return list;
	}

	public SortedSet<Participation> getParticipations() {
		SortedSet<Participation> set = new TreeSet();
		for (Booking booking : this.getBookingSet()) {
			set.add((Participation) booking);
		}
		return Collections.unmodifiableSortedSet(set);
	}

	@Override
	public ActivityEvent addBooking(Attendee attendee) {
		this.addParticipation(attendee);
		return this;
	}

	// Attendances
	@Action
	public ActivityEvent addAttendance(Attendee attendee) {
		this.createAttendance(attendee);
		return this;
	}

	public List<Attendee> choices0AddAttendance() {
		return baseRepo.listAttendees();
	}

	@Action
	public ActivityEvent removeAttendance(Attendance attendance) {
		if (this.getAttendancesSet().contains(attendance)) {
			this.getAttendancesSet().remove(attendance);
			// baseRepo.destroyAttendance(attendance);
		}
		return this;
	}

	public Set<Attendance> choices0RemoveAttendance() {
		return this.getAttendancesSet();
	}

	protected Attendance createAttendance(Attendee attendee) {
		Attendance attendance = activityRepo.createAttendance(this, attendee);
		this.getAttendancesSet().add(attendance);
		attendee.addAttendance(attendance);
		return attendance;
	}

	public SortedSet<Attendance> getAttendances() {
		return Collections.unmodifiableSortedSet(this.getAttendancesSet());
	}

	@Override
	public String doValidateStartAndFinishDateTimes(DateTime start, DateTime finish) {
		if (start != null && finish != null) {
			if (finish.isBefore(start) || finish.equals(start))
				return "End is before or equal to Start";
			else {
				Duration duration = new Duration(start, finish);
				if (duration.getStandardMinutes() == 0)
					return "End is equal to Start";
				if (duration.getStandardHours() > 12)
					return "End and Start are not in the same 12 hour period";
				if (finish.getDayOfWeek() != start.getDayOfWeek()) {
					return "End and Start are on different days of the week";
				}
			}
		}
		return null;
	}

	@Inject
	EventsRepository baseRepo;

	@Inject
	ActivityRepository activityRepo;

	protected void createAttendanceSetFromParticipantSet() {
		// TODO Auto-generated method stub
	}
}
