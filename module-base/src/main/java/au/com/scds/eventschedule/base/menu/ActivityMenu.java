package au.com.scds.eventschedule.base.menu;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Organisation;
import au.com.scds.eventschedule.base.impl.activity.ActivityBaseRepository;
import au.com.scds.eventschedule.base.impl.activity.BaseActivityEvent;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "ActivityMenu")
@DomainServiceLayout(named = "Activities", menuOrder = "30")
public class ActivityMenu {

	@Action
	public BaseActivityEvent createActivityEvent(String name, DateTime date) {
		return repo.createActivityEvent(null, name, "Activities", date, null);
	}

	@Action
	public List<BaseActivityEvent> listActivityEvents() {
		return repo.listBaseActivityEvents();
	}

	@Inject
	ActivityBaseRepository repo;
}
