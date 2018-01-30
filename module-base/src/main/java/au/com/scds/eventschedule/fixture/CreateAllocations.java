package au.com.scds.eventschedule.fixture;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.eventschedule.base.menu.ContactMenu;
import au.com.scds.eventschedule.fixture.generated.Allocations;
import au.com.scds.eventschedule.fixture.generated.ContactAllocation;
import au.com.scds.eventschedule.fixture.generated.ObjectFactory;

public class CreateAllocations extends FixtureScript {

	public CreateAllocations() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private List<au.com.scds.eventschedule.base.impl.ContactAllocation> allocations = new ArrayList<>();

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/eventschedule/fixture/allocations.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Allocations _allocations = (Allocations) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (ContactAllocation _allocation : _allocations.getAllocation()) {
				au.com.scds.eventschedule.base.impl.Contactee contactee = contactMenu
						.createContactee(_allocation.getContactee().getPerson().getFullname());
				au.com.scds.eventschedule.base.impl.Contactor contactor = contactMenu
						.createContactor(_allocation.getContactor().getPerson().getFullname());
				au.com.scds.eventschedule.base.impl.ContactAllocation allocation = contactMenu
						.createContactAllocation(contactor, contactee);
				this.allocations.add(allocation);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<au.com.scds.eventschedule.base.impl.ContactAllocation> getAllocations() {
		return this.allocations;
	}

	@Inject
	ContactMenu contactMenu;
}
