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

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;
import au.com.scds.eventschedule.base.impl.Organisation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "BaseParentedActivityEvent")
@DomainObject(objectType = "BaseParentedActivityEvent")
public class BaseParentedActivityEvent extends BaseActivityEvent {
	
	@Getter(value=AccessLevel.PRIVATE)
	@Setter(value=AccessLevel.PRIVATE)
	protected BaseRecurringActivityEvent parent;

	public BaseParentedActivityEvent(BaseRecurringActivityEvent parent, String name, String calendarName, DateTime date,
			String note) {
		super(null, name, calendarName, date, note);
		this.setParent(parent);
	}
}
