# event-schedule
Generic Event Scheduling &amp; Booking/Attendance Application based on Apache Isis.

## Description

A general purpose application with events and attendees booking to attend those Events. 

Also provides for contacts, with contactees being contacted by contactors.

This application will generally be extended to suite the specific needs of an organisation. 
The origin of the application is from the Chats application developed for Lifeline Tasmania.
The generic functionality of Chats has been extracted into this application, and Chats then refactored to be a extension of this.

To demonstrate, activities are an extension of scheduled events. Chats then builds on the several activities classes of this module.

## Entities

### Simple Event

A minimal event, an extension of the abstract BaseEvent class

![Simple Event Screen](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/SimpleEvent.png)

### Scheduled Event

An event for scheduling with attendees having bookings. 

![Scheduled Event Screen](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/ScheduledEvent.png)

### Calendarable Scheduled Event

A Scheduled Event as previous but with the addition of being able to be displayed in a calendar. This demonstrates the use of the Incode Calendar Module.

![Calendarable Scheduled Event Screen](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/CalendarableScheduledEvent.png)

![Calendarable Scheduled Events in Tabular View](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/CalendarableScheduledEventsInTableView.png)

![Calendarable Scheduled Events in Calendar View](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/CalendarableScheduledEventsInCalendarView.png)

### Activity (Event)

An Activity Event is an extension of Calendarable Scheduled Event, but have Participations and Attendances instead of Bookings. Participations are an intention to attend an activity, Attendances are an after the fact record of attendance.

![Activity Event](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/ActivityEvent.png)

### Recurring Activity

A Recurring Activity (parent) has child activities, any Participations created against the parent are seen in all child activities, together with those created in each child individually.

![Recurring Activity Event](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/RecurringActivityEvent.png)

A child activity, with a link back to its parent Recurring Activity and with two Participations, one from its parent and one created for this child.

![Parented Activity Event](https://github.com/Stephen-Cameron-Data-Services/event-schedule/raw/master/module-base/images/screen/ParentedActivityEvent.png)

## Implementation

This project uses the Java web-application framework [Apache Isis](http://isis.apache.org).

It is based on the Apache Isis [SimpleApp Maven Archetype](https://isis.apache.org/guides/ugfun/ugfun.html#_ugfun_getting-started_simpleapp-archetype), see that guide for installation instructions.  

It can be enhanced relatively easily by customising its Java Domain Model and/or integrating standard modules from the [Incode Platform](http://platform.incode.org).



