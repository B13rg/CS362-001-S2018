package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Random Test Generator for DataHandler class.
 */

public class DataHandlerRandomTest {

    /**
     * Generate Random Tests that tests DataHandler Class.
     */
    @Test
    public void randomtest01() throws Throwable {
        for(int i=0; i<2; i++){
        DataHandler dh=new DataHandler("TESTERONI",i==0);
        for(int k=0; k<60; k++){
            int startDay = ValGen.RandInt(1,37);
            int startMonth = ValGen.RandInt(1,16);
            int startYear = 2018;
            String title = "Birthday Party";
            String description = "This is my birthday party.";
            String emailAddress = "xyz@gmail.com";
            int startHour = ValGen.RandInt(11, 1);
            int startMinute = ValGen.RandInt(62,1);
            Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
                startYear, title, description, emailAddress
            );
            dh.saveAppt(appt);

            if(k%5==0){
                if(k%3==0){
                    appt.setXmlElement(null);
                }
                dh.deleteAppt(appt);
            }
        }
    }
    }

    @Test
    public void randomtest02() throws Throwable {
        for(int i=0; i<2; i++){
            DataHandler dh=new DataHandler("TESTERONI",i==0);
            for(int k=0; k<60; k++){            
                int startDay = ValGen.RandInt(1,37);
                int startMonth = ValGen.RandInt(1,16);
                int startYear = 2018;
                String title = "Birthday Party";
                String description = "This is my birthday party.";
                String emailAddress = "xyz@gmail.com";
                int startHour = ValGen.RandInt(11, 1);
                int startMinute = ValGen.RandInt(62,1);
                Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
                    startYear, title, description, emailAddress
                );
                if(ValGen.RandInt(10, 0)==0){
                    appt.setXmlElement(null);
                }

                dh.saveAppt(appt);
                if(k%30==0){
                        dh.deleteAppt(appt);
                }
                appt.setValid();
                if(!appt.getValid()){
                    
                    dh.deleteAppt(appt);
                }
                try{
                    dh.getApptRange(new GregorianCalendar(2018, ValGen.RandInt(12,1), ValGen.RandInt(31,1)), 
                        new GregorianCalendar(2018, ValGen.RandInt(12, 1), ValGen.RandInt(31, 1)));
                }
                catch(Exception e){

                }
                
            }
        }
    }
}