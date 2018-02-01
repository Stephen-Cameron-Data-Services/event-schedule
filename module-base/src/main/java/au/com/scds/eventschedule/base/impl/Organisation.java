package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.Getter;
import lombok.Setter;


@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="organisation")
@DomainObject(objectType="Organisation")
public class Organisation {

	@Column(allowsNull = "true")
	@Getter()
	@Setter()
    protected String name;

	public Organisation(String name) {
		this.setName(name);
	}
}
