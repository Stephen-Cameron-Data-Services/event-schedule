package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="person")
@DomainObject(objectType="Person")
public class Person {

	@Column(allowsNull = "true")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
    protected String fullname;
	
	public Person() {
	}

	public Person(String name) {
		this.setFullname(name);
	}
	
	public String title(){
		return this.getFullname();
	}
}
