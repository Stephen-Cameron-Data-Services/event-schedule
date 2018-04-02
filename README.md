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

### SimpleEvent

A minimal event, an extension of the abstract BaseEvent class

![Organisation Screen](https://github.com/Stephen-Cameron-Data-Services/event-schedule/blob/master/event-schedule-module-base/images/screen/SimpleEvent.png)


## Implementation

This project uses the Java web-application framework [Apache Isis](http://isis.apache.org).

It can be enhanced relatively easily by customising its Java Domain Model and/or integrating standard modules from the [Incode Platform](http://platform.incode.org).



