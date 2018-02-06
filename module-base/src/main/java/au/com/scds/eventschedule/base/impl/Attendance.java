/*
 *
 *  Copyright 2015 Stephen Cameron Data Services
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


package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendance")
@DomainObject(objectType="Attendance")
public class Attendance {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	public ScheduledEvent event;

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	public Attendee attendee;
	
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	public Booking booking;

	public Attendance() {
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
}
