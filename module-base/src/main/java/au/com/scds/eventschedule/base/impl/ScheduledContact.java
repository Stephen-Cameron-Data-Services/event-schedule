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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.Contactor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "scheduled_contact")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(value = "ScheduledContact")
public class ScheduledContact extends BaseEvent {

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Contactor contactor;

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Contactee contactee;

	protected ScheduledContact() {
	}

	public ScheduledContact(Contactor contactor, Contactee contactee, DateTime date) {
		super(date, null);
		this.setContactor(contactor);
		this.setContactee(contactee);
	}

	public boolean isCompleted() {
		return getStart() != null && getEnd() != null;
	}

	@Action()
	public ScheduledContact moveContact(Contactor contactor, DateTime dateTime) {
		if (!isCompleted()) {
			setContactor(contactor);
			updateStartDateTime(dateTime);
		}
		return this;
	}

	public List<Contactor> choices0MoveContact() {
		return eventsRepo.listContactors();
	}

	public String disableMoveContact() {
		if (isCompleted())
			return "Cannot move a completed Scheduled Contact ";
		else
			return null;
	}

	@Inject
	EventsRepository eventsRepo;
}
