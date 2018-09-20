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


package au.com.scds.eventschedule.base.impl.contact;

import java.util.List;

import javax.inject.Inject;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.event.Person;

@DomainService(nature = NatureOfService.DOMAIN)
public class ContactsRepository {
	
	public Contactee createContactee(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		Contactee object = new Contactee(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactee createContactee(Person person) {
		if (person == null)
			return null;
		Contactee object = new Contactee(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactor createContactor(String name) {
		if (name == null)
			return null;
		Person person = new Person(name);
		repositoryService.persistAndFlush(person);
		Contactor object = new Contactor(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public Contactor createContactor(Person person) {
		if (person == null)
			return null;
		Contactor object = new Contactor(person);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, DateTime date) {
		if (contactor == null || contactee == null || date == null)
			return null;
		ScheduledContact object = new ScheduledContact(contactor, contactee, date);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public ContactAllocation createContactAllocation(Contactor contactor, Contactee contactee) {
		if (contactor == null || contactee == null)
			return null;
		ContactAllocation object = new ContactAllocation(contactor, contactee);
		repositoryService.persistAndFlush(object);
		return object;
	}
	
	public void destroyContactAllocation(ContactAllocation allocation) {
		repositoryService.removeAndFlush(allocation);
	}

	public List<Contactor> listAllContactors() {
		return repositoryService.allInstances(Contactor.class);
		
	}

	public List<Contactee> listAllContactees() {
		return repositoryService.allInstances(Contactee.class);
	}
	
	@Inject
	RepositoryService repositoryService;
}
