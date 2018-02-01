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
import org.apache.isis.applib.services.i18n.TranslatableString;

import au.com.scds.eventschedule.base.impl.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "contactor")
@DomainObject(objectType="Contactor")
public class Contactor {

	@Column(allowsNull = "false")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected Person person;

	@Persistent(mappedBy = "contactor")
	@Order(column="contactor_allocations_idx")
	@Getter()
	@Setter(value = AccessLevel.PROTECTED)
	protected List<ContactAllocation> allocations = new ArrayList<>();

	public Contactor(Person person) {
		this.setPerson(person);
	}
	
	public TranslatableString title() {
		return TranslatableString.tr("Contactor: {fullname}", this.getPerson().getFullname());
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
