package au.com.scds.eventschedule.base;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

public interface Attendee {

    public String getName();
    public List<EventBooking> getBookings();
    public List<EventAttendance> getAttendances();
    public Person getPerson();
}
