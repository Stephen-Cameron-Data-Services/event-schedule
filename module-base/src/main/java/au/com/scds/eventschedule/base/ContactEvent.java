package au.com.scds.eventschedule.base;

public interface ContactEvent extends EventBase{

	public Contactee getContactee();
	public Contactor getContactor();
}
