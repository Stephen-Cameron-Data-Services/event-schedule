package au.com.scds.eventschedule.fixture.scenarios;

import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.menu.BookingMenu;
import au.com.scds.eventschedule.base.menu.EventMenu;
import au.com.scds.eventschedule.fixture.generated.*;

public class CreateEvents extends FixtureScript {

	public CreateEvents() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private au.com.scds.eventschedule.base.impl.Event event = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/events.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Events _events = (Events) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (Event _event : _events.getEvent()) {
				this.event = bookingMenu.createEvent( new DateTime(_event.getStart()),  new DateTime(_event.getEnd()));
				au.com.scds.eventschedule.base.impl.Booking booking = null;
				for(Booking _booking : _event.getBookings().getBooking()){
					au.com.scds.eventschedule.base.impl.Booker booker = bookingMenu.createBooker(_booking.getBooker().getIdentifier());
					au.com.scds.eventschedule.base.impl.Bookable bookable = bookingMenu.createBookable(_booking.getBookable().getIdentifier());
					this.event.createBooking(booker, bookable);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public au.com.scds.eventschedule.base.impl.Event getEvent() {
		return this.event;
	}
	
	@Inject
	BookingMenu bookingMenu;
}
