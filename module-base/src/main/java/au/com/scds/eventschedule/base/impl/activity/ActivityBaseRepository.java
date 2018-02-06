package au.com.scds.eventschedule.base.impl.activity;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.impl.Attendee;
import au.com.scds.eventschedule.base.impl.Organisation;

@DomainService(nature = NatureOfService.DOMAIN)
public class ActivityBaseRepository {

	public BaseActivityEvent createActivityEvent(Organisation organisation, String eventName, String calendarName,
			DateTime date, String note) {
		if (eventName == null || date == null)
			return null;
		BaseActivityEvent object = new BaseActivityEvent(organisation, eventName, calendarName, date, note);
		repositoryService.persistAndFlush(object);
		return object;
	}

	public List<BaseActivityEvent> listBaseActivityEvents() {
		return repositoryService.allInstances(BaseActivityEvent.class);
	}

	public BaseParticipation createParticipation(BaseActivityEvent event, Attendee attendee) {
		if (event == null || attendee == null)
			return null;
		BaseParticipation object = new BaseParticipation(event, attendee);
		repositoryService.persistAndFlush(object);
		return object;
	}

	@Inject
	RepositoryService repositoryService;
}
