package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Person;
import lombok.Getter;
import lombok.Setter;


@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="eventfacilitator")
@DomainObject()
public class EventFacilitator {

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
    protected Person person;
	
	public EventFacilitator(Person person2) {
		super();
		this.setPerson(person);
	}

}
