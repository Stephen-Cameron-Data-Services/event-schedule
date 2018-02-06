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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "simple_event")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@DomainObject(objectType = "SimpleEvent", editing = Editing.ENABLED)
public class SimpleEvent extends BaseEvent {

	@Column()
	@Getter
	@Setter
	private String name;
	@Column()
	@Getter
	@Setter
	private String description;

}
