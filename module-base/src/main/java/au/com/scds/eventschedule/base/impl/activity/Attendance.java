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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.ScheduledEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendance")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.VALUE_MAP, value="Attendance")
public class Attendance implements Comparable<Attendance>{

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PROTECTED)
	public ScheduledEvent event;
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PROTECTED)
	public Attendee attendee;
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	public Booking booking;
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	public Boolean attended;

	protected Attendance() {
	}

	public Attendance(ScheduledEvent event, Attendee attendee) {
		this.setEvent(event);
		this.setAttendee(attendee);
	}
	
	public Attendance(Booking booking) {
		this.setEvent(booking.getEvent());
		this.setAttendee(booking.getAttendee());
		this.setBooking(booking);
	}
	
	public String title(){
		return this.getAttendee().getFullname() +  "-in-" + this.getEvent().getName();
	}
	
	@NotPersistent
	public String getAttendeeName() {
		return this.getAttendee().getFullname();
	}

	@Override
	public int compareTo(Attendance other) {
		return doCompareTo(other);
	}
	
	protected int doCompareTo(Attendance other) {
		return this.getAttendee().compareTo(other.getAttendee());
	}
	
}
