package au.com.scds.eventschedule.fixture.scenarios;

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
import au.com.scds.eventschedule.fixture.generated.Attendance;
import au.com.scds.eventschedule.fixture.generated.Attendee;
import au.com.scds.eventschedule.fixture.generated.EventFacilitator;
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
				for(Attendee _attendee : _parent.getWaitList().getAttendee()){
					attendee = eventMenu.createEventAttendee(_attendee.getPerson().getFullname());
					parent.addWaitListed(attendee);
				}
				au.com.scds.eventschedule.base.impl.EventFacilitator facilitator = null;
				for(EventFacilitator _facilitator : _parent.getEventFacilitator()){
					facilitator = eventMenu.createEventFacilitator(_facilitator.getPerson().getFullname());
					parent.addFacilitator(facilitator);
				}
				//same for child events
				for(ParentedActivityEvent _child : _parent.getChildEvent()){
					parent.addChildEvent(new DateTime(_child.getDate()));
					au.com.scds.eventschedule.base.impl.activity.ParentedActivityEvent child = null;
					for(au.com.scds.eventschedule.base.impl.activity.ParentedActivityEvent e : parent.getChildEvents()){
						if(e.getStart().equals(new DateTime(_child.getDate()))){
							child = e;
							break;
						}
					}
					for(Participation _participation : _child.getParticipation()){
						attendee = eventMenu.createEventAttendee(_participation.getAttendee().getPerson().getFullname());
						child.addParticipation(attendee);
					}
					for(Attendee _attendee : _child.getWaitList().getAttendee()){
						attendee = eventMenu.createEventAttendee(_attendee.getPerson().getFullname());
						child.addWaitListed(attendee);
					}
					for(EventFacilitator _facilitator : _child.getEventFacilitator()){
						facilitator = eventMenu.createEventFacilitator(_facilitator.getPerson().getFullname());
						child.addFacilitator(facilitator);
					}
					for(Attendance _attendance : _child.getAttendance()){
						attendee = eventMenu.createEventAttendee(_attendance.getAttendee().getPerson().getFullname());
						child.addAttendance(attendee);
					}
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
