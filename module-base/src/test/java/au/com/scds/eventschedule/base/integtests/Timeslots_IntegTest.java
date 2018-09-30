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

import au.com.scds.eventschedule.base.impl.contact.ContactAllocation;
import au.com.scds.eventschedule.base.impl.contact.Contactee;
import au.com.scds.eventschedule.base.impl.contact.Contactor;
import au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots;
import au.com.scds.eventschedule.base.impl.timeslot.Timeslot;
import au.com.scds.eventschedule.fixture.scenarios.CreateAllocations;
import au.com.scds.eventschedule.fixture.scenarios.CreateTimeslots;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class Timeslots_IntegTest extends IntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    TransactionService transactionService;
	    
	    List<BookableWithTimeslots> bookables  =  null;

	    @Before
	    public void setUp() throws Exception {
	        // given
	    	CreateTimeslots fs = new CreateTimeslots();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        bookables = fs.getBookables();
	        assertThat(bookables).isNotNull();
	    }

	    public static class Timeslots extends Timeslots_IntegTest {

	        @Test
	        public void accessible() throws Exception {
	            // then
	        	assertThat(bookables).isNotNull();
	        	assertThat(bookables.size()).isEqualTo(1);
	        	BookableWithTimeslots bookable = bookables.get(0);
	        	assertThat(bookable.getTimeslots().size()).isEqualTo(1);
	        	Timeslot timeslot = bookable.getTimeslots().get(0);
	        	assertThat(timeslot.getBooking()).isNotNull();
	        	assertThat(timeslot.getBooking().getBooker()).isNotNull();
	        	assertThat(timeslot.getBooking().getBooker().getIdentifier()).isEqualTo("tns:identifier");
	        	assertThat(timeslot.getBooking().getBooker().getBookings().size()).isEqualTo(1);
	        	assertThat(timeslot.getBooking().getBookable()).isSameAs(bookable);
	        }
	    }
	}