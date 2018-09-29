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
import au.com.scds.eventschedule.fixture.generated.Booking;
import au.com.scds.eventschedule.fixture.generated.ObjectFactory;
import au.com.scds.eventschedule.fixture.generated.Participation;

public class CreateActivities extends FixtureScript {
	
	public CreateActivities() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private List<au.com.scds.eventschedule.base.impl.activity.ActivityEvent> activities = new ArrayList<>();

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/activities.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Activities _activities = (Activities) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (ActivityEvent _activity : _activities.getActivity()) {
				au.com.scds.eventschedule.base.impl.activity.ActivityEvent activity = activityMenu.createActivityEvent(_activity.getName(), new DateTime(_activity.getStart()));
				au.com.scds.eventschedule.base.impl.event.Attendee attendee = null;
				for(Booking _booking : _activity.getBookings().getBooking()){
					attendee = eventMenu.createEventAttendee(_booking.getBooker().getIdentifier());
					activity.addParticipation(attendee);
				}
				activities.add(activity);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<au.com.scds.eventschedule.base.impl.activity.ActivityEvent> getActivities() {
		return this.activities;
	}

	@Inject
	ActivityMenu activityMenu;
	@Inject
	EventMenu eventMenu;
}
