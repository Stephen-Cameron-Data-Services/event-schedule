package au.com.scds.eventschedule.base.menu;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.activity.ActivityRepository;
import au.com.scds.eventschedule.base.impl.activity.ActivityEvent;
import au.com.scds.eventschedule.base.impl.activity.RecurringActivityEvent;
import au.com.scds.eventschedule.base.impl.event.Organisation;
import au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots;
import au.com.scds.eventschedule.base.impl.timeslot.TimeslotRepository;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "TimeslotMenu")
@DomainServiceLayout(named = "Timeslots", menuOrder = "50")
public class TimeslotMenu {

	@Action
	@MemberOrder(sequence = "1")
	public BookableWithTimeslots createBookableWithTimeslots(String identifier) {
		return repo.createBookableWithTimeslots(identifier);
	}

	@Inject
	TimeslotRepository repo;
}
