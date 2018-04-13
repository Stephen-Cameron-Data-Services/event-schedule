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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.services.i18n.TranslatableString;

import au.com.scds.eventschedule.base.impl.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "contactor")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Contactor")
public class Contactor implements Comparable<Contactor>{

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Person person;

	@Persistent(mappedBy = "contactor")
	//@Order(column="contactor_allocations_idx")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<ContactAllocation> allocationSet = new TreeSet<>();
	
	protected Contactor() {}

	public Contactor(Person person) {
		this.setPerson(person);
	}
	
	public TranslatableString title() {
		return TranslatableString.tr("Contactor: {fullname}", "fullname", this.getPerson().getFullname());
	}

	@Action
	public void addAllocation(ContactAllocation allocation) {
		this.getAllocationSet().add(allocation);
	}

	public SortedSet<ContactAllocation> getAllocations() {
		return Collections.unmodifiableSortedSet(this.getAllocationSet());
	}

	public void removeAllocation(ContactAllocation allocation) {
		if(this.getAllocationSet().contains(allocation))
			this.getAllocationSet().remove(allocation);
	}

	@Override
	public int compareTo(Contactor other) {
		return doCompareTo(other);
	}
	
	protected int doCompareTo(Contactor other){
		return this.getPerson().compareTo(other.getPerson());
	}
}
