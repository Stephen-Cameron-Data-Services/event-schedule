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

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.Organisation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "ParentedActivityEvent")
@DomainObject(objectType = "ParentedActivityEvent")
public class ParentedActivityEvent extends ActivityEvent {
	
	@Getter
	@Setter(value=AccessLevel.PRIVATE)
	protected RecurringActivityEvent parent;
	
	protected ParentedActivityEvent() {
		super();
	}
	
	public ParentedActivityEvent(RecurringActivityEvent parent, String name, String calendarName, DateTime date,
			String note) {
		super(null, name, calendarName, date, note);
		this.setParent(parent);
	}

	@Override
	public SortedSet<Participation> getParticipations() {
		SortedSet<Participation> set = new TreeSet();
		for (Participation participation : this.getParent().getParticipations()) {
			set.add(participation);
		}
		for (Booking booking : this.getBookingSet()) {
			set.add((Participation) booking);
		}
		return Collections.unmodifiableSortedSet(set);
	}
}
