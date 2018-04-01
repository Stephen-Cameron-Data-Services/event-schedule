package au.com.scds.eventschedule.fixture;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.DateTime;

//import au.com.scds.eventschedule.base.impl.activity.ActivityEvent;
import au.com.scds.eventschedule.base.menu.ActivityMenu;
import au.com.scds.eventschedule.base.menu.EventMenu;
import au.com.scds.eventschedule.fixture.generated.Activities;
import au.com.scds.eventschedule.fixture.generated.ActivityEvent;
import au.com.scds.eventschedule.fixture.generated.ObjectFactory;
import au.com.scds.eventschedule.fixture.generated.ParentedActivityEvent;
import au.com.scds.eventschedule.fixture.generated.Participation;
import au.com.scds.eventschedule.fixture.generated.RecurringActivities;
import au.com.scds.eventschedule.fixture.generated.RecurringActivityEvent;

public class CreateRecurringActivities extends FixtureScript {
	
	public CreateRecurringActivities() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private List<au.com.scds.eventschedule.base.impl.activity.RecurringActivityEvent> activities = new ArrayList<>();

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/recurring_activities.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			RecurringActivities _activities = (RecurringActivities) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (RecurringActivityEvent _parent: _activities.getRecurringActivity()) {
				au.com.scds.eventschedule.base.impl.activity.RecurringActivityEvent parent = activityMenu.createRecurringActivityEvent(_parent.getName(), new DateTime(_parent.getDate()));
				au.com.scds.eventschedule.base.impl.Attendee attendee = null;
				for(Participation _participation : _parent.getParticipation()){
					attendee = eventMenu.createEventAttendee(_participation.getAttendee().getPerson().getFullname());
					parent.addParticipation(attendee);
				}
				for(ParentedActivityEvent _child : _parent.getChildEvent()){
					parent.addChildEvent(new DateTime(_child.getDate()));
				}
				activities.add(parent);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<au.com.scds.eventschedule.base.impl.activity.RecurringActivityEvent> getActivities() {
		return this.activities;
	}

	@Inject
	ActivityMenu activityMenu;
	@Inject
	EventMenu eventMenu;
}
