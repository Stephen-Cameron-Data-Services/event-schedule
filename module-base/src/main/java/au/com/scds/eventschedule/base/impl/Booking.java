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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.services.i18n.TranslatableString;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "booking")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Booking")
@DomainObject(objectType = "Booking")
public class Booking implements Comparable<Booking> {

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected ScheduledEvent event;

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	public Attendee attendee;

	protected Booking() {
	}

	public Booking(ScheduledEvent event, Attendee attendee) {
		setEvent(event);
		setAttendee(attendee);
	}

	public TranslatableString title() {
		return TranslatableString.tr("{attendee}-at-{event}", "attendee", this.getAttendeeName(), "event",
				this.getEvent().getName());
	}

	@NotPersistent
	public String getAttendeeName() {
		return this.getAttendee().getFullname();
	}

	@Override
	public int compareTo(Booking other) {
		return doCompareTo(other);
	}
	
	protected int doCompareTo(Booking other){
		int result = this.getEvent().compareTo(other.getEvent());
		if (result != 0) {
			return result;
		} else {
			return this.getAttendee().compareTo(other.getAttendee());
		}		
	}
}
