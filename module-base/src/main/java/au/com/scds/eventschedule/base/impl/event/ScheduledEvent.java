/*
 *
 *  Copyright 2018 Stephen Cameron Data Services
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package au.com.scds.eventschedule.base.impl.event;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.joda.time.DateTime;
import au.com.scds.eventschedule.base.impl.Booking;
import au.com.scds.eventschedule.base.impl.BookingsRepository;
import au.com.scds.eventschedule.base.impl.activity.Attendee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "event")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "ScheduledEvent")
public class ScheduledEvent extends MutableEvent  {

	@Column(allowsNull = "true")
	@Getter()
	@Setter(value = AccessLevel.PACKAGE)
	protected Organisation organisation;
	@Column(allowsNull = "false")
	@Title()
	@Getter()
	@Setter()
	protected String name;
	@Column(allowsNull = "true")
	@Title()
	@Getter()
	@Setter()
	protected String codeName;
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected String description;
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected Integer cutoffLimit;
	@Column(allowsNull = "false")
	@Getter()
	@Setter()
	protected boolean cancelled;
	@Column(allowsNull = "true")
	@Getter()
	@Setter()
	protected Address address;
	@Persistent
	@Join
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<Attendee> waitListedSet = new TreeSet<>();
	@Persistent
	@Join
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected SortedSet<EventFacilitator> facilitatorSet = new TreeSet<>();

	protected ScheduledEvent() {
		super();
	}

	public ScheduledEvent(Organisation organisation, String name, DateTime date) {
		super(date, null);
		this.setOrganisation(organisation);
		this.setName(name);
		this.setStart(date);
	}

	public TranslatableString title() {
		return TranslatableString.tr("{name}", "name", this.getName());
	}

	@Action
	public ScheduledEvent addBooking(Attendee attendee) {
		this.createBooking(attendee);
		return this;
	}

	public List<Attendee> choices0AddBooking() {
		return eventsRepo.listAttendees();
	}

	public String disableAddBooking() {
		if (this.getCutoffLimit() != null && this.getBookingSet().size() == this.getCutoffLimit()) {
			return "Count has reached Cut-off Limit";
		} else {
			return null;
		}
	}

	@Action
	public ScheduledEvent removeBooking(Booking booking) {
		if (this.getBookingSet().contains(booking)) {
			this.getBookingSet().remove(booking);
			// baseRepo.destroyBooking(booking);
		}
		return this;
	}

	public Set<Booking> choices0RemoveBooking() {
		return this.getBookingSet();
	}

	public Booking createBooking(Attendee attendee) {
		Booking booking = bookingsRepo.createBooking(null, attendee, this);
		this.getBookingSet().add(booking);
		return booking;
	}

	public SortedSet<Booking> getBookings() {
		return Collections.unmodifiableSortedSet(this.getBookingSet());
	}

	@Action
	public ScheduledEvent addWaitListed(Attendee attendee) {
		this.getWaitListedSet().add(attendee);
		return this;
	}

	public List<Attendee> choices0AddWaitListed() {
		return eventsRepo.listAttendees();
	}

	@Action
	public ScheduledEvent removeWaitListed(Attendee attendee) {
		if (this.getWaitListedSet().contains(attendee))
			this.getWaitListedSet().remove(attendee);
		return this;
	}

	public Set<Attendee> choices0RemoveWaitListed() {
		return this.getWaitListedSet();
	}

	public SortedSet<Attendee> getWaitListed() {
		return Collections.unmodifiableSortedSet(this.getWaitListedSet());
	}

	@Action
	public ScheduledEvent moveWaitListed(Attendee attendee) {
		if (getWaitListed().contains(attendee)) {
			this.addBooking(attendee);
			this.removeWaitListed(attendee);
		}
		return this;
	}

	public Set<Attendee> choices0MoveWaitListed() {
		return getWaitListed();
	}

	public String disableMoveWaitListed() {
		if (this.getCutoffLimit() != null && this.getBookingSet().size() >= this.getCutoffLimit()) {
			return "Count has reached Cut-off Limit";
		} else {
			return null;
		}
	}

	@Action
	public ScheduledEvent addFacilitator(EventFacilitator facilitator) {
		this.getFacilitatorSet().add(facilitator);
		return this;
	}

	public List<EventFacilitator> choices0AddFacilitator() {
		return eventsRepo.listEventFacilitators();
	}

	@Action
	public ScheduledEvent removeFacilitator(EventFacilitator facilitator) {
		if (this.getFacilitatorSet().contains(facilitator))
			this.getFacilitatorSet().remove(facilitator);
		return this;
	}

	public Set<EventFacilitator> choices0RemoveFacilitator() {
		return this.getFacilitatorSet();
	}

	public SortedSet<EventFacilitator> getFacilitators() {
		return Collections.unmodifiableSortedSet(this.getFacilitatorSet());
	}

	@NotPersistent
	public String getLocationName() {
		return (getAddress() != null) ? getAddress().getName() : null;
	}

	@NotPersistent
	public String getStreetAddress() {
		return (getAddress() != null) ? getAddress().getFullStreetAddress() : null;
	}
	
	@Inject
	BookingsRepository bookingsRepo;

	@Inject
	EventsRepository eventsRepo;

}
