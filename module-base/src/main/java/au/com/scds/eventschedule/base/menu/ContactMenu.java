package au.com.scds.eventschedule.base.menu;

import java.util.Date;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.ContactAllocation;
import au.com.scds.eventschedule.base.impl.Contactor;
import au.com.scds.eventschedule.base.impl.EventScheduleBaseRepository;
import au.com.scds.eventschedule.base.impl.ScheduledContact;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "EventSchedule.ContactMenu")
@DomainServiceLayout(named = "Contacts", menuOrder = "20")
public class ContactMenu {

	@Action
	public Contactor createContactor(String fullname) {
		return repo.createContactor(fullname);
	}

	@Action
	public Contactee createContactee(String fullname) {
		return repo.createContactee(fullname);
	}

	@Action
	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, Date date) {
		return repo.createScheduledContact(contactor, contactee, date);
	}

	public ContactAllocation createContactAllocation(Contactor contactor,
			Contactee contactee) {
		ContactAllocation allocation = repo.createContactAllocation(contactor, contactee);
		contactee.addAllocation(allocation);
		contactor.addAllocation(allocation);
		return allocation;
	}
	
	@Inject
	EventScheduleBaseRepository repo;

}
