package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.Person;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="contactor")
@DomainObject()
public class Contactor {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
    protected Person person;


}
