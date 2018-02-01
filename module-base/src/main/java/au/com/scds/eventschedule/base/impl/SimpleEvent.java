package au.com.scds.eventschedule.base.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "simple_event")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@DomainObject(objectType = "SimpleEvent", editing = Editing.ENABLED)
public class SimpleEvent extends BaseEvent {

	@Column()
	@Getter
	@Setter
	private String name;
	@Column()
	@Getter
	@Setter
	private String description;

}
