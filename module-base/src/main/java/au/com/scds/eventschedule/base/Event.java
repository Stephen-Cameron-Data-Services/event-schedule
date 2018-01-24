package au.com.scds.eventschedule.base;

import java.util.List;

public interface Event extends EventBase{
	
    public String getName();
    public List<Attendee> getWaitList();
    public List<EventBooking> getBookings();
    public List<EventAttendance> getAttendances();
    public List<EventFacilitator> getFacilitators();

}
