package au.com.scds.eventschedule.base.test.timeslot;


import org.junit.Before;
import org.junit.Test;

import au.com.scds.eventschedule.base.impl.timeslot.BookableWithTimeslots;
import au.com.scds.eventschedule.base.impl.timeslot.Timeslot;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;

public class Timeslot_Test {

    BookableWithTimeslots bookable;

    @Before
    public void setUp() throws Exception {
    	bookable = new BookableWithTimeslots("testing");
    }

    public static class Name extends Timeslot_Test {

        @Test
        public void happyCase() throws Exception {
            // given
            assertThat(bookable.getIdentifier()).isEqualTo("testing");
            assertThat(bookable.getTimeslots().size()).isEqualTo(0);

            // when
            String name = "Foobar - updated";
            bookable.setIdentifier(name);
            DateTime start = new DateTime();
            DateTime end1 = start.plusHours(1);
            Timeslot timeslot1 = new Timeslot(bookable, start, end1);
            bookable.addTimeslot(timeslot1);
            DateTime end2 = end1.plusHours(1);
            Timeslot timeslot2 = new Timeslot(bookable, end1, end2);
            bookable.addTimeslot(timeslot2);

            // then
            assertThat(bookable.getIdentifier()).isEqualTo(name);
            assertThat(bookable.getTimeslots().size()).isEqualTo(2);
        }
        
        @Test
        public void overlappingTimeslotsNotAdded() throws Exception {
        	
            // given
            assertThat(bookable.getTimeslots().size()).isEqualTo(0);

            // when
            DateTime start = new DateTime();
            DateTime end = start.plusHours(1);
            Timeslot timeslot1 = new Timeslot(bookable, start, end);
            bookable.addTimeslot(timeslot1);

            Timeslot timeslot2 = new Timeslot(bookable, start.plusMinutes(30), end.plusMinutes(30));
            bookable.addTimeslot(timeslot2);

            // then
            assertThat(bookable.getTimeslots().size()).isEqualTo(1);
        }
        
        @Test
        public void findFirstOverlapWith() throws Exception {
        	
            // given
            assertThat(bookable).isNotNull();

            // when same as existing Timeslot
            DateTime start = new DateTime();
            DateTime end = start.plusHours(1);
            bookable.addTimeslot(new Timeslot(bookable, start, end));

            // then
            assertThat(bookable.findFirstOverlapWith(start, end)).isNotNull();

            // when touching ok
            DateTime start2 = new DateTime(end.getMillis());
            DateTime end2 = start2.plusHours(1);

            // then
            assertThat(bookable.findFirstOverlapWith(start2, end2)).isNull();
            
            // when slight overlap
            DateTime start3 = start2.minusMillis(1);

            // then
            assertThat(bookable.findFirstOverlapWith(start3, end2)).isNotNull();
        }
    }

}