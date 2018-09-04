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
public class Booking {
	
	@Column(allowsNull = "true")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Bookable booked;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Booker booker;

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected BaseEvent event;

	protected Booking() {
	}

	public Booking(BaseEvent event, Booker attendee) {
		setEvent(event);
		setBooker(attendee);
	}
	
	public Booking(BaseEvent event, Booker attendee, Bookable booked) {
		setEvent(event);
		setBooker(attendee);
		setBooked(booked);
	}

	public TranslatableString title() {
		return TranslatableString.tr("{booker}-has-{bookable}-at-{event}", 
				"booker", this.getBooker().title(), 
				"bookable", this.getBooked().title(),
				"event", this.getEvent().getStart());
	}
}
