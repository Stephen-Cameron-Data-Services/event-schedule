package au.com.scds.eventschedule.base;

//import javax.jdo.annotations.IdentityType;
//import javax.jdo.annotations.PersistenceCapable;
//import javax.jdo.annotations.Persistent;

//@PersistenceCapable(identityType=IdentityType.DATASTORE)
public interface EventAttendance {

	//@Persistent
	public Event getEvent();
	//@Persistent
	public Attendee getAttendee();
}
