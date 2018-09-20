package au.com.scds.eventschedule.base.impl.timeslot;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
	protected SortedSet<Timeslot> timeslotsSet = new TreeSet<>();

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Timeslot first;

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Timeslot last;
	
	public SortedSet<Timeslot> getTimeslots() {
		return Collections.unmodifiableSortedSet(this.getTimeslotsSet());
	}
	
	@Action
	public BookableWithTimeslots addTimeslot(Date start, Date end){
		//both not null
		//start before end
		//start and end form an interval that doesn't overlap another interval
		//create new timeslot
		//locate before and after timeslosts in list
		//set before->next to new
		//set new->prev to before
		//set after->prev to new
		//set new->next to after
		//add new to the timeslotset 
		return this;
	}
	
	@Action
	public BookableWithTimeslots removeTimeslot(Timeslot timeslot){
		//locate timeslot in the timeslotset
		//locate timeslot->prev and timeslot=>next
		//set timeslot->prev->next to timeslot->next
		//set timeslott->next->orev to timelost->prev
		//remove timeslot from timeslotset
		//delete timeslot
		return this;
	}
	
	public Set<Timeslot> choices0RemoveTimeslot(){
		return this.getTimeslots();
	}
}
