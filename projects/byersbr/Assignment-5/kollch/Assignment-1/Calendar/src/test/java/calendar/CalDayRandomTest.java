package calendar;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Random;
import static org.junit.Assert.*;
/**
 * Random Test Generator for CalDay class.
 */
public class CalDayRandomTest {
    /**
     * Generate Random Tests that tests CalDay Class.
     */
    @Test
    public void randomtest() throws Throwable {
        for (int i=0; i < 30000; i++) {
            int startDay = ValGen.RandInt(1,31);
            int startMonth = ValGen.RandInt(1,12);
            int startYear = 2018;
            String title = "Birthday Party";
            String description = "This is my birthday party.";
            String emailAddress = "xyz@gmail.com";
            CalDay calDay=new CalDay(new GregorianCalendar(startYear, startMonth, startDay));
            for(int k=0; k<5; k++){
                int startHour = ValGen.RandInt(11, 1);
                int startMinute = ValGen.RandInt(62,1);
                Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
                    startYear, title, description, emailAddress
                );
                //appt.setValid();
                //calDay.addAppt(appt);
            }
        }
    }

}
