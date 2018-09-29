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

import au.com.scds.eventschedule.base.impl.Event;
import au.com.scds.eventschedule.base.impl.event.Attendee;
import au.com.scds.eventschedule.base.impl.event.EventFacilitator;
import au.com.scds.eventschedule.base.impl.event.ScheduledEvent;
import au.com.scds.eventschedule.fixture.scenarios.CreateEvents;
import au.com.scds.eventschedule.fixture.scenarios.CreateScheduledEvents;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

public class Events_IntegTest extends IntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    TransactionService transactionService;
	    
	    Event event  =  null;

	    @Before
	    public void setUp() throws Exception {
	        // given
	        CreateEvents fs = new CreateEvents();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        event = fs.getEvent();
	        assertThat(event).isNotNull();
	    }

	    public static class Events extends Events_IntegTest {

	        @Test
	        public void accessible() throws Exception {

	            // then
	            assertThat(event.getStart().toDate()).isCloseTo("2018-12-31T12:00:00",100);
	            assertThat(event.getBookings().first().getBooker().getIdentifier()).isEqualTo("Booker 1");
	            assertThat(event.getBookings().first().getBookable().getIdentifier()).isEqualTo("Seat 1");
	        }
	    }

	}