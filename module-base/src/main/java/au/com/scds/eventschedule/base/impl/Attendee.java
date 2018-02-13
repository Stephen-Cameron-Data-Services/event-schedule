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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.fixturescripts.FixtureResult;

import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Person;
import au.com.scds.eventschedule.base.impl.activity.Attendance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendee")
@DomainObject(objectType = "Attendee")
public class Attendee {

	@Persistent(mappedBy = "attendee")
	@Order(column = "attendee_bookings_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Booking> bookings = new ArrayList<>();

	@Persistent(mappedBy = "attendee")
	@Order(column = "attendee_attendances_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected List<Attendance> attendances = new ArrayList<>();

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PRIVATE)
	protected Person person;

	private Attendee() {
	}

	public Attendee(Person person) {
		this.setPerson(person);
	}

	public String title() {
		return this.getFullname();
	}

	@NotPersistent
	public String getFullname() {
		return this.getPerson().getFullname();
	}

	public List<Booking> getBookingsList() {
		return Collections.unmodifiableList(this.getBookings());
	}

	public List<Attendance> getAttendancesList() {
		return Collections.unmodifiableList(this.getAttendances());
	}
	
	public void addBooking(Booking booking) {
		if (!this.getBookings().contains(booking))
			this.getBookings().add(booking);
	}

	public void removeBooking(Booking booking) {
		if (this.getBookings().contains(booking))
			this.getBookings().remove(booking);
	}

	public void addAttendance(Attendance attendance) {
		if (!this.getAttendances().contains(attendance))
			this.getAttendances().add(attendance);
	}
	
	public void removeAttendance(Attendance attendance) {
		if (this.getAttendances().contains(attendance))
			this.getAttendances().remove(attendance);
	}
}
