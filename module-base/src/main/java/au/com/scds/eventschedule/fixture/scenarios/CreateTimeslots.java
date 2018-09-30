package au.com.scds.eventschedule.fixture.scenarios;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.DateTime;

import au.com.scds.eventschedule.base.menu.BookingMenu;
import au.com.scds.eventschedule.base.menu.TimeslotMenu;
import au.com.scds.eventschedule.fixture.generated.BookableWithTimeslots;
import au.com.scds.eventschedule.fixture.generated.ObjectFactory;
import au.com.scds.eventschedule.fixture.generated.Timeslot;
import au.com.scds.eventschedule.fixture.generated.Timeslots;

public class CreateTimeslots extends FixtureScript {

	public CreateTimeslots() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private List<au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots> bookables = new ArrayList<>();

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/timeslots.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Timeslots _timeslots = (Timeslots) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (BookableWithTimeslots _bookable : _timeslots.getBookable()) {
				au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots bookable = timeslotMenu
						.createBookableWithTimeslots(_bookable.getIdentifier());
				for (Timeslot _timeslot : _bookable.getTimeslots().getTimeslot()) {
					au.com.scds.eventschedule.base.impl.timeslot.Timeslot timeslot = bookable
							.createTimeslot(new DateTime(_timeslot.getStart()), new DateTime(_timeslot.getEnd()));
					timeslot.createBooking(
							bookingMenu.createBooker(_timeslot.getBooking().getBooker().getIdentifier()));
				}
				this.bookables.add(bookable);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots> getBookables() {
		return this.bookables;
	}

	@Inject
	TimeslotMenu timeslotMenu;
	@Inject
	BookingMenu bookingMenu;
}
