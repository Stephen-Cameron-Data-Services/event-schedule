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

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="contact_allocation")
@DomainObject(objectType="ContactAllocation")
public class ContactAllocation {
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
	protected Contactor contactor;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
	protected Contactee contactee;
	
	public ContactAllocation(Contactor contactor, Contactee contactee){
		this.setContactor(contactor);
		this.setContactee(contactee);
	}
	
	@Action
	public ContactAllocation reassignTo(Contactor contactor){
		this.getContactor().removeAllocation(this);
		this.setContactor(contactor);
		contactor.addAllocation(this);
		return this;
	}
	
	@Action
	public void delete(){
		this.getContactee().removeAllocation(this);
		this.getContactor().removeAllocation(this);
		baseRepo.destroyContactAllocation(this);
	}
	
	@Inject
	EventScheduleBaseRepository baseRepo;
}
