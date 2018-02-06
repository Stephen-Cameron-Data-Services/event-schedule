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

package au.com.scds.eventschedule.base.impl.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.CalendarableScheduledEvent;
import au.com.scds.eventschedule.base.impl.Organisation;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "BaseActivityEvent")
@DomainObject(objectType = "BaseActivityEvent")
public class BaseActivityEvent extends CalendarableScheduledEvent {

	public BaseActivityEvent(Organisation organisation, String name, String calendarName, DateTime date, String note) {
		super(organisation, name, calendarName, date, note);
	}

	@Action
	public BaseActivityEvent addParticipation(Attendee attendee) {
		BaseParticipation participation = activityRepo.createParticipation(this, attendee);
		this.getBookings().add(participation);
		return this;
	}

	@Action
	public BaseActivityEvent removeParticipation(BaseParticipation participation) {
		this.removeBooking(participation);
		return this;
	}

	public List<BaseParticipation> choices0RemoveParticipation() {
		List<BaseParticipation> list = new ArrayList();
		for (Booking booking : this.getBookings()) {
			list.add((BaseParticipation) booking);
		}
		return list;
	}
	
	public List<BaseParticipation> getParticipationsList(){
		List<BaseParticipation> list = new ArrayList();
		for (Booking booking : this.getBookings()) {
			list.add((BaseParticipation) booking);
		}
		return Collections.unmodifiableList(list);
	}
	
	@Inject
	ActivityBaseRepository activityRepo;
}
