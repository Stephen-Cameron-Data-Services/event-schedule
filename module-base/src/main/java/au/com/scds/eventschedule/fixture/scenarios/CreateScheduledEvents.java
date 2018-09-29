package au.com.scds.eventschedule.fixture.scenarios;

import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.menu.EventMenu;
import au.com.scds.eventschedule.fixture.generated.*;

public class CreateScheduledEvents extends FixtureScript {

	public CreateScheduledEvents() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private au.com.scds.eventschedule.base.impl.event.ScheduledEvent event = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/scheduled_events.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			ScheduledEvents _events = (ScheduledEvents) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (ScheduledEvent _event : _events.getEvent()) {
				this.event = eventMenu.createScheduledEvent(_event.getName(), new DateTime(_event.getStart()));
				au.com.scds.eventschedule.base.impl.event.Attendee attendee = null;
				for(Booking _booking : _event.getBookings().getBooking()){
					attendee = eventMenu.createEventAttendee(_booking.getBooker().getIdentifier());
					event.addBooking(attendee);
				}
				for(Attendee _attendee : _event.getWaitList().getAttendee()){
					attendee = eventMenu.createEventAttendee(_attendee.getPerson().getFullname());
					event.addWaitListed(attendee);
				}
				au.com.scds.eventschedule.base.impl.event.EventFacilitator facilitator = null;
				for(EventFacilitator _facilitator : _event.getEventFacilitator()){
					facilitator = eventMenu.createEventFacilitator(_facilitator.getPerson().getFullname());
					event.addFacilitator(facilitator);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public au.com.scds.eventschedule.base.impl.event.ScheduledEvent getEvent() {
		return this.event;
	}
	
	@Inject
	EventMenu eventMenu;


}
