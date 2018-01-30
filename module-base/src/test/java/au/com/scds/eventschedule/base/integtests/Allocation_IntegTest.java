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

import au.com.scds.eventschedule.base.impl.ContactAllocation;
import au.com.scds.eventschedule.base.impl.Contactee;
import au.com.scds.eventschedule.base.impl.Contactor;
import au.com.scds.eventschedule.fixture.CreateAllocations;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class Allocation_IntegTest extends IntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    TransactionService transactionService;
	    
	    List<ContactAllocation> allocations  =  null;

	    @Before
	    public void setUp() throws Exception {
	        // given
	        CreateAllocations fs = new CreateAllocations();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        allocations = fs.getAllocations();
	        assertThat(allocations).isNotNull();
	    }

	    public static class Name extends Allocation_IntegTest {

	        @Test
	        public void accessible() throws Exception {

	            // then
	        	assertThat(allocations).isNotNull();
	        	assertThat(allocations.size()).isEqualTo(2);
	        	ContactAllocation allocation1 = allocations.get(0);
	        	assertThat(allocation1.getContactee()).isNotNull();
	        	assertThat(allocation1.getContactor()).isNotNull();
	        	Contactee contactee1 = allocation1.getContactee();
	        	Contactor contactor1 = allocation1.getContactor();
	        	assertThat(contactee1.getPerson()).isNotNull();
	        	assertThat(contactor1.getPerson()).isNotNull();
	        	assertThat(contactee1.getPerson().getFullname()).isEqualTo("Ian Fleming");
	        	assertThat(contactor1.getPerson().getFullname()).isEqualTo("James Bond");
	        	assertThat(contactee1.getAllocations().size()).isEqualTo(1);
	        	assertThat(contactor1.getAllocations().size()).isEqualTo(1);
	        	
	        	ContactAllocation allocation2 = allocations.get(1);
	        	assertThat(allocation2.getContactee()).isNotNull();
	        	assertThat(allocation2.getContactor()).isNotNull();
	        	Contactee contactee2 = allocation2.getContactee();
	        	Contactor contactor2 = allocation2.getContactor();
	        	assertThat(contactee2.getPerson()).isNotNull();
	        	assertThat(contactor2.getPerson()).isNotNull();
	        	assertThat(contactee2.getPerson().getFullname()).isEqualTo("Joanne Rowling");
	        	assertThat(contactor2.getPerson().getFullname()).isEqualTo("Harry Potter");
	        	assertThat(contactee2.getAllocations().size()).isEqualTo(1);
	        	assertThat(contactor2.getAllocations().size()).isEqualTo(1);

	        	allocation2.reassignTo(contactor1);
	        	assertThat(contactor1.getAllocations().size()).isEqualTo(2);
	        	assertThat(contactor2.getAllocations().size()).isEqualTo(0);
	        	assertThat(allocation2.getContactee()).isNotNull();
	        	assertThat(allocation2.getContactor()).isNotNull();
	        	assertThat(allocation2.getContactee()).isSameAs(contactee2);
	        	assertThat(allocation2.getContactor()).isSameAs(contactor1);
	        	assertThat(allocation1.getContactee()).isSameAs(contactee1);
	        	assertThat(allocation1.getContactor()).isSameAs(contactor1);
	        	
	        	allocation2.delete();
	        	assertThat(contactor1.getAllocations().size()).isEqualTo(1);
	        	assertThat(contactor2.getAllocations().size()).isEqualTo(0);
	        }
	    }

	}