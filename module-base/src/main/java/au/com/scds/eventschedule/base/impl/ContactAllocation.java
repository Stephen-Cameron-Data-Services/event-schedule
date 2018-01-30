package au.com.scds.eventschedule.base.impl;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table="contact_allocation")
@DomainObject()
public class ContactAllocation {
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
	protected Contactor contactor;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter(value=AccessLevel.PRIVATE)
	protected Contactee contactee;
	
	public ContactAllocation(Contactor contactor, Contactee contactee){
		this.setContactor(contactor);
		this.setContactee(contactee);
	}
	
	@Action
	public ContactAllocation reassignTo(Contactor contactor){
		this.getContactor().removeAllocation(this);
		this.setContactor(contactor);
		contactor.addAllocation(this);
		return this;
	}
	
	@Action
	public void delete(){
		this.getContactee().removeAllocation(this);
		this.getContactor().removeAllocation(this);
		baseRepo.destroyContactAllocation(this);
	}
	
	@Inject
	EventScheduleBaseRepository baseRepo;
}
