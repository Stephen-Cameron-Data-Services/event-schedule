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

import au.com.scds.eventschedule.base.impl.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "eventfacilitator")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "EventFacilitator")
public class EventFacilitator implements Comparable<EventFacilitator> {

	@Column(allowsNull = "true")
	@Getter()
	@Setter(value = AccessLevel.PRIVATE)
	protected Person person;

	public EventFacilitator(Person person) {
		this.setPerson(person);
	}

	@NotPersistent
	public String getFullname() {
		return this.getPerson().getFullname();
	}

	public String title() {
		return this.getFullname();
	}

	@Override
	public int compareTo(EventFacilitator other) {
		return doCompareTo(other);
	}
	
	protected int doCompareTo(EventFacilitator other){
		return this.getPerson().compareTo(other.getPerson());		
	}
}
