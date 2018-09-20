package au.com.scds.eventschedule.base.menu;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.contact.ContactAllocation;
import au.com.scds.eventschedule.base.impl.contact.Contactee;
import au.com.scds.eventschedule.base.impl.contact.Contactor;
import au.com.scds.eventschedule.base.impl.contact.ContactsRepository;
import au.com.scds.eventschedule.base.impl.contact.ScheduledContact;
import au.com.scds.eventschedule.base.impl.event.EventsRepository;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "ContactsMenu")
@DomainServiceLayout(named = "Contacts", menuOrder = "40")
public class ContactMenu {

	@Action
	@MemberOrder(sequence = "1")
	public Contactor createContactor(@ParameterLayout(named="Full Name") String fullname) {
		return repo.createContactor(fullname);
	}

	@Action
	@MemberOrder(sequence = "2")
	public Contactee createContactee(@ParameterLayout(named="Full Name") String fullname) {
		return repo.createContactee(fullname);
	}

	@Action
	@MemberOrder(sequence = "3")
	public ScheduledContact createScheduledContact(Contactor contactor, Contactee contactee, 
			@ParameterLayout(named="Date-time") DateTime date) {
		return repo.createScheduledContact(contactor, contactee, date);
	}
	
	public List<Contactor> choices0CreateScheduledContact(){
		return repo.listAllContactors();
	}
	
	public List<Contactee> choices1CreateScheduledContact(){
		return repo.listAllContactees();
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
	ContactsRepository repo;
	
	
}
