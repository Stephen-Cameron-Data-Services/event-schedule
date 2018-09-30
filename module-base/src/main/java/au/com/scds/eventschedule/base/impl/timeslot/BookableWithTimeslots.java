package au.com.scds.eventschedule.base.impl.timeslot;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import au.com.scds.eventschedule.base.impl.Bookable;
import au.com.scds.eventschedule.base.impl.Booking;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "event_schedule", table = "TimeslotList")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, value = "TimeslotList")
public class BookableWithTimeslots extends Bookable {

	@Persistent(mappedBy = "bookable")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected LinkedList<Timeslot> timeslotsList = new LinkedList<>();

	protected BookableWithTimeslots() {
		super();
	}

	public BookableWithTimeslots(String identifier) {
		super(identifier);
	}

	public List<Timeslot> getTimeslots() {
		return Collections.unmodifiableList(this.getTimeslotsList());
	}

	public Timeslot createTimeslot(DateTime start, DateTime end) {
		if (start == null || end == null)
			return null;
		if (start.isAfter(end))
			return null;
		synchronized (this.timeslotsList) {
			if (findFirstOverlapWith(start, end) != null) {
				return null;
			}
			Timeslot timeslot = timeslotRepo.createTimeslot(this, start, end);
			Timeslot prev = null;
			if (this.getTimeslotsList().isEmpty()) {
				this.getTimeslotsList().add(0, timeslot);
			} else {
				for (int i = 0; i < this.getTimeslotsList().size(); i++) {
					prev = this.getTimeslotsList().get(i);
					if (prev.getEnd().isBefore(start) || prev.getEnd().equals(start)) {
						this.getTimeslotsList().add(i + 1, timeslot);
					}
				}
			}
			return timeslot;
		}
	}

	@Action
	public BookableWithTimeslots addTimeslot(DateTime start, DateTime end) {
		createTimeslot(start, end);
		return this;
	}

	public String validateAddTimeslot(DateTime start, DateTime end) {
		if (start.isAfter(end))
			return "Start must be before End";
		else
			return "";
	}

	private Timeslot findFirstOverlapWith(DateTime start, DateTime end) {
		if (start == null || end == null)
			throw new IllegalArgumentException("start and end cannot be null");
		if (this.getTimeslotsList().isEmpty())
			return null;
		Interval interval = new Interval(start, end);
		for (Timeslot timeslot : this.getTimeslotsList()) {
			if (timeslot.asInterval().overlaps(interval))
				return timeslot;
		}
		return null;
	}

	@Action
	public BookableWithTimeslots removeTimeslot(Timeslot timeslot) {
		if (timeslot == null)
			return this;
		synchronized (this.timeslotsList) {
			if (this.getTimeslotsList().contains(timeslot)) {
				this.getTimeslotsList().remove(timeslot);
				timeslotRepo.destroyTimeslot(timeslot);
			}
		}
		return this;
	}

	public List<Timeslot> choices0RemoveTimeslot() {
		return this.getTimeslots();
	}

	@Inject
	TimeslotRepository timeslotRepo;

}
