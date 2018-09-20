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

package au.com.scds.eventschedule.base.impl.event;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * An event having a start date-time, possibly an mutable Interval if having a finish date-time.
 * 
 */
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "event")
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Event")
@DomainObject(objectType = "Event")
public abstract class MutableEvent extends Event {

	@Persistent(mappedBy = "event")
	@Order(column = "event_booking_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingSet = new TreeSet<>();

	public MutableEvent() {
		super();
	}

	public MutableEvent(DateTime start, DateTime end) {
		super(start, end);
	}

	@NotPersistent
	public String getIntervalLengthFormatted() {
		if (this.getStart() != null && this.getEnd() != null) {
			Duration duration = new Duration(this.getStart(), this.getEnd());
			Long hours = duration.getStandardHours();
			Long minutes = duration.getStandardMinutes();
			if (hours > 0)
				minutes = minutes - hours * 60;
			return String.format("%01d:%02d", hours, minutes);
		} else
			return null;
	}

	@NotPersistent
	public Integer getIntervalLengthInMinutes() {
		if (this.getStart() != null && this.getEnd() != null) {
			Duration duration = new Duration(this.getStart(), this.getEnd());
			return (int) duration.getStandardMinutes();
		} else
			return null;
	}

	public String doValidateStartAndFinishDateTimes(DateTime start, DateTime finish) {
		if (start != null && finish != null) {
			if (finish.isBefore(start) || finish.equals(start))
				return "End is before or equal to Start";
		}
		return null;
	}

	@Action()
	public MutableEvent updateStartDateTime(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Start Time") DateTime start) {
		this.setStart(trimSeconds(start));
		return this;
	}

	public DateTime default0UpdateStartDateTime() {
		if (this.getStart() == null)
			return this.getEnd();
		else
			return this.getStart();
	}

	public String validateUpdateStartDateTime(DateTime start) {
		return doValidateStartAndFinishDateTimes(start, this.getEnd());
	}

	// NOTE Must keep end date time optional to be able to change start date
	// time to anything
	@Action()
	public MutableEvent updateEndDateTime(
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "End Time") DateTime end) {
		this.setEnd(trimSeconds(end));
		return this;
	}

	public DateTime default0UpdateEndDateTime() {
		if (this.getEnd() == null)
			return this.getStart();
		else
			return this.getEnd();
	}

	public String validateUpdateEndDateTime(DateTime end) {
		return doValidateStartAndFinishDateTimes(this.getStart(), end);
	}

	@Action()
	public MutableEvent updateEndDateTimeOffStart(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Add N Minutes to Start Date Time") Integer minutes) {
		this.setEnd(this.getStart().plusMinutes(minutes));
		return this;
	}

	public String validateUpdateEndDateTimeOffStart(Integer minutes) {
		return doValidateStartAndFinishDateTimes(this.getStart(), this.getStart().plusMinutes(minutes));
	}

	public String disableUpdateEndDateTimeOffStart() {
		if (this.getStart() == null)
			return "Start Date Time is Not Set";
		else
			return null;
	}

	protected DateTime trimSeconds(DateTime dateTime) {
		if (dateTime == null)
			return null;
		final DateTime hour = dateTime.hourOfDay().roundFloorCopy();
		final long millisSinceHour = new Duration(hour, dateTime).getMillis();
		final int roundedMinutes = ((int) Math.round(millisSinceHour / 60000.0));
		return hour.plusMinutes(roundedMinutes);
	}
	
	public MutableEvent addBooking(Booking booking) {
		if (!this.getBookingSet().contains(booking))
			this.getBookingSet().add(booking);
		return this;
	}

	public MutableEvent removeBooking(Booking booking) {
		if (this.getBookingSet().contains(booking))
			this.getBookingSet().remove(booking);
		return this;
	}
}
