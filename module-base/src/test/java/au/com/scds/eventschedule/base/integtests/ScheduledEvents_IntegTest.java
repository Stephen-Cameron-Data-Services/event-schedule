package au.com.scds.eventschedule.base.integtests;
/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
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


import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import au.com.scds.eventschedule.base.impl.event.Attendee;
import au.com.scds.eventschedule.base.impl.event.EventFacilitator;
import au.com.scds.eventschedule.base.impl.event.ScheduledEvent;
import au.com.scds.eventschedule.fixture.scenarios.CreateScheduledEvents;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduledEvents_IntegTest extends IntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    TransactionService transactionService;
	    
	    ScheduledEvent event  =  null;

	    @Before
	    public void setUp() throws Exception {
	        // given
	        CreateScheduledEvents fs = new CreateScheduledEvents();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        event = fs.getEvent();
	        assertThat(event).isNotNull();
	    }

	    public static class Events extends ScheduledEvents_IntegTest {

	        @Test
	        public void accessible() throws Exception {

	            // then
	            assertThat(event.getName()).isEqualTo("Test Event");
	            assertThat(event.getStart().toDate()).isCloseTo("2018-12-31T12:00:00",100);
	            
	            assertThat(event.getBookings().size()).isEqualTo(1);

	            assertThat(event.getWaitListed().size()).isEqualTo(1);
	            assertThat(event.getFacilitators().size()).isEqualTo(1);
	         
	            Attendee attendee1 = (Attendee) event.getBookings().first().getBooker();
	            //Attendee attendee2 = event.getAttendancesList().get(0).getAttendee();
	            Attendee attendee3 = event.getWaitListed().first();
	            EventFacilitator facilitator1 = event.getFacilitators().first();
	            
	            assertThat(attendee1.getFullname()).isEqualTo("Anders Zorn");
	            //assertThat(attendee2.getFullname()).isEqualTo("Pablo Picasso");
	            assertThat(attendee3.getFullname()).isEqualTo("Albert Namatjira");
	            assertThat(facilitator1.getFullname()).isEqualTo("Rembrandt van Rijn");

	            assertThat(attendee1.getBookings().size()).isEqualTo(1);
	            //assertThat(attendee2.getAttendancesList().size()).isEqualTo(1);
	            
	            event.removeBooking(event.getBookings().first());
	            //event.removeAttendance(event.getAttendancesList().get(0));
	            event.removeWaitListed(attendee3);
	            event.removeFacilitator(facilitator1);
	            
	            assertThat(event.getBookings().size()).isEqualTo(0);
	            //assertThat(event.getAttendancesList().size()).isEqualTo(0);
	            assertThat(event.getWaitListed().size()).isEqualTo(0);
	            assertThat(event.getFacilitators().size()).isEqualTo(0);
	            assertThat(attendee1.getBookings().size()).isEqualTo(0);
	            //assertThat(attendee2.getAttendancesList().size()).isEqualTo(0);
	        }
	    }

	}