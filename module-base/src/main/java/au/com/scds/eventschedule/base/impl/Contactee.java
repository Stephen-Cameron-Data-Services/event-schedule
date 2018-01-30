package au.com.scds.eventschedule.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Person;
import lombok.Getter;
import lombok.Setter;


@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="contactee")
@DomainObject()
public class Contactee{

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
    protected Person person;
	
	@Persistent(mappedBy="contactee")
	@Order(column="contactee_allocations_idx")
	@Getter()
	@Setter()
    protected List<ContactAllocation> allocations  = new ArrayList<>();	

	@Persistent
	@Getter()
	@Setter()
	protected List<ScheduledContact> contacts = new ArrayList<>();
	
	public Contactee(Person person){
		this.setPerson(person);
	}

	@Action
	public void addAllocation(ContactAllocation allocation) {
		this.getAllocations().add(allocation);
	}

	public List<ContactAllocation> getAllocationsList() {
		return Collections.unmodifiableList(this.getAllocations());
	}

	public void removeAllocation(ContactAllocation allocation) {
		if(this.getAllocations().contains(allocation))
			this.getAllocations().remove(allocation);
	}
}
