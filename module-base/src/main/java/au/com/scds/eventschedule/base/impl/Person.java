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
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="person")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "Person")
public class Person implements Comparable<Person>{
	
	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	String firstname;
	@Column(allowsNull = "true")
	@Getter
	@Setter
	private String middlename;
	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private String surname;
	@Column(allowsNull = "true")
	@Getter
	@Setter
	private String preferredname;
	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private LocalDate birthdate;
	@Column(allowsNull = "true")
	@Getter()
	@Setter(value=AccessLevel.PROTECTED)
    protected String fullname;
	
	public Person() {
	}

	public Person(String name) {
		this.setFullname(name);
	}
	
	public String title(){
		return this.getFullname();
	}

	@Override
	public int compareTo(Person person) {
		return doCompareTo(person);
	}
	
	protected int doCompareTo(Person person){
		return this.getFullname().compareTo(person.getFullname());		
	}
}
