package au.com.scds.eventschedule.base.impl.activity;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Organisation;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "BaseRecurringActivityEvent")
@DomainObject(objectType = "BaseRecurringActivityEventt")
public class BaseRecurringActivityEvent extends BaseActivityEvent{

	@Getter()
	@Setter()
	protected List<BaseParentedActivityEvent> childEvents = new ArrayList<>();
	
	public BaseRecurringActivityEvent(Organisation organisation, String name, String calendarName, DateTime date,
			String note) {
		super(organisation, name, calendarName, date, note);
	}
}
