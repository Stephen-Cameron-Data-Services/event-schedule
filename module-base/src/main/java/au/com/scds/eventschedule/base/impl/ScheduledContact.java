package au.com.scds.eventschedule.base.impl;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.Contactor;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "contact_event")
@DomainObject(objectType="ScheduledContact")
public class ScheduledContact extends BaseEvent {

	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected Contactor contactor;
	
	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected Contactee contactee;

	public ScheduledContact(Contactor contactor, Contactee contactee, DateTime date) {
		this.setContactor(contactor);
		this.setContactee(contactee);
		this.setStart(date);
	}
}
