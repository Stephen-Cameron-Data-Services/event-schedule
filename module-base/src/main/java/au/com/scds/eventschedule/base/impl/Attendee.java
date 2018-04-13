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

package au.com.scds.eventschedule.base.impl;

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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Person;
import au.com.scds.eventschedule.base.impl.activity.Attendance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "attendee")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Attendee")
public class Attendee implements Comparable<Attendee> {

	@Persistent(mappedBy = "attendee")
	// @Order(column = "attendee_bookings_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Booking> bookingsSet = new TreeSet<>();

	@Persistent(mappedBy = "attendee")
	// @Order(column = "attendee_attendances_idx")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Attendance> attendancesSet = new TreeSet<>();

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Person person;

	protected Attendee() {
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

	public SortedSet<Booking> getBookings() {
		return Collections.unmodifiableSortedSet(this.getBookingsSet());
	}

	public SortedSet<Attendance> getAttendances() {
		return Collections.unmodifiableSortedSet(this.getAttendancesSet());
	}

	public void addBooking(Booking booking) {
		if (!this.getBookingsSet().contains(booking))
			this.getBookingsSet().add(booking);
	}

	public void removeBooking(Booking booking) {
		if (this.getBookingsSet().contains(booking))
			this.getBookingsSet().remove(booking);
	}

	public void addAttendance(Attendance attendance) {
		if (!this.getAttendancesSet().contains(attendance))
			this.getAttendancesSet().add(attendance);
	}

	public void removeAttendance(Attendance attendance) {
		if (this.getAttendancesSet().contains(attendance))
			this.getAttendancesSet().remove(attendance);
	}

	@Override
	public int compareTo(Attendee other) {
		return doCompareTo(other); 
	}

	protected int doCompareTo(Attendee other) {
		return this.getPerson().compareTo(other.getPerson());
	}
}
