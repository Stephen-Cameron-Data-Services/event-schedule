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

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "ActivityMenu")
@DomainServiceLayout(named = "Activities", menuOrder = "30")
public class ActivityMenu {

	@Action
	@MemberOrder(sequence = "1")
	public ActivityEvent createActivityEvent(@ParameterLayout(named="Name") String name, @ParameterLayout(named="Date-time") DateTime date) {
		return repo.createActivityEvent(null, name, "Activities", date, null);
	}

	@Action
	@MemberOrder(sequence = "2")
	public RecurringActivityEvent createRecurringActivityEvent(@ParameterLayout(named="Name") String name, @ParameterLayout(named="Date-time") DateTime date) {
		return repo.createRecurringActivityEvent(null, name, "Activities", date, null);
	}

	@Action
	@MemberOrder(sequence = "3")
	public List<ActivityEvent> listActivityEvents() {
		return repo.listActivityEvents();
	}

	@Action
	@MemberOrder(sequence = "4")
	public List<RecurringActivityEvent> listRecurringActivityEvents() {
		return repo.listRecurringActivityEvents();
	}

	@Inject
	ActivityRepository repo;
}
