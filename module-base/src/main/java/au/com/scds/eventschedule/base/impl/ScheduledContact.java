package au.com.scds.eventschedule.base.impl;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.Contactor;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "contact_event")
@DomainObject()
public class ScheduledContact {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected Contactor contactor;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected Contactee contactee;
	
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected Date date;

	public ScheduledContact(Contactor contactor, Contactee contactee, Date date) {
		this.setContactor(contactor);
		this.setContactee(contactee);
		this.setDate(date);
	}

}
