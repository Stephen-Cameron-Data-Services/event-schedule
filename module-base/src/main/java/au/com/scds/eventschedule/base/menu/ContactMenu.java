package au.com.scds.eventschedule.base.menu;

import java.util.Date;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.ContactAllocation;
import au.com.scds.eventschedule.base.impl.Contactor;
import au.com.scds.eventschedule.base.impl.EventsRepository;
import au.com.scds.eventschedule.base.impl.ScheduledContact;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "ContactsMenu")
@DomainServiceLayout(named = "Contacts", menuOrder = "40")
public class ContactMenu {

	@Action
	@MemberOrder(sequence = "1")
	public Contactor createContactor(String fullname) {
		return repo.createContactor(fullname);
	}

	@Action
	@MemberOrder(sequence = "2")
	public Contactee createContactee(String fullname) {
		return repo.createContactee(fullname);
	}

	@Action
	@MemberOrder(sequence = "3")
	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, DateTime date) {
		return repo.createScheduledContact(contactor, contactee, date);
	}

	@Action
	@MemberOrder(sequence = "4")
	public ContactAllocation createContactAllocation(Contactor contactor,
			Contactee contactee) {
		ContactAllocation allocation = repo.createContactAllocation(contactor, contactee);
		contactee.addAllocation(allocation);
		contactor.addAllocation(allocation);
		return allocation;
	}
	
	@Inject
	EventsRepository repo;
}
