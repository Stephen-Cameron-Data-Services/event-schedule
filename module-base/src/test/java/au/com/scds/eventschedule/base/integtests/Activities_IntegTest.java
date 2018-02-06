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
import au.com.scds.eventschedule.base.impl.activity.BaseActivityEvent;
import au.com.scds.eventschedule.fixture.CreateActivities;
import au.com.scds.eventschedule.fixture.CreateAllocations;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class Activities_IntegTest extends IntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    TransactionService transactionService;
	    
	    List<BaseActivityEvent> activities  =  null;

	    @Before
	    public void setUp() throws Exception {
	        // given
	        CreateActivities fs = new CreateActivities();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        activities = fs.getActivities();
	        assertThat(activities).isNotNull();
	    }

	    public static class Activities extends Activities_IntegTest {

	        @Test
	        public void accessible() throws Exception {
	            // then
	        	assertThat(activities.get(0)).isNotNull();
	        	assertThat(activities.get(0).getParticipationsList()).isNotNull();
	        	assertThat(activities.get(0).getParticipationsList().size()).isEqualTo(1);
	        }
	    }

	}