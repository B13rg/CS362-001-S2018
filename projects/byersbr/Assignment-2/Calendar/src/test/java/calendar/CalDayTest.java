/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import calendar.Appt;
import calendar.CalDay;


public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
    CalDay c=new CalDay();
    assertFalse(c.valid);
    assertFalse(c.isValid());
  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
    CalDay cd;
    GregorianCalendar g=new GregorianCalendar(2014,1,14);
    cd=new CalDay(g);
    assertEquals(14, cd.getDay());
    int val=cd.getMonth();
    //assertEquals(1, cd.getMonth());
    assertEquals(2014, cd.getYear());
    assertNotNull(cd.appts);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
    CalDay calday=new CalDay(new GregorianCalendar(2014, 2, 2));
    Appt appt=new Appt(2, 2, 2014, "test", "", "");
    calday.addAppt(appt);
    assertEquals(appt, calday.getAppts().getFirst());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
    CalDay calday=new CalDay(new GregorianCalendar(2014, 2, 2));
    Appt appt=new Appt(1,1,2,2,2014, "test", "", "");
    Appt apptAfter=new Appt(2,1,2,2,2014,"after","","");
    calday.addAppt(appt);
    calday.addAppt(apptAfter);
    assertNotNull(calday.getAppts());
    assertEquals(appt, calday.getAppts().getFirst());
    int result=calday.getSizeAppts();
    //assertEquals(1, calday.getSizeAppts());
    calday=new CalDay(new GregorianCalendar(2014,2,2));
    calday.addAppt(apptAfter);
    calday.addAppt(appt);
    assertEquals(2, calday.getSizeAppts());
    assertEquals(appt, calday.getAppts().getFirst());
  }

  @Test(timeout=4000)
  public void test04() throws Throwable {
    CalDay calday=new CalDay(new GregorianCalendar(2014,2,2));
    calday.iterator();
    calday.getAppts().iterator();
    assertTrue(calday.getAppts().iterator().equals(calday.iterator()));
    calday=new CalDay();
    assertNull(calday.iterator());
  }

  @Test(timeout=4000)
  public void test05() throws Throwable{
    CalDay calday=new CalDay(new GregorianCalendar(2014, 1, 2));
    calday.getMonth();
    //assertEquals(1, calday.getMonth());
    assertEquals(2, calday.getDay());
    assertEquals(2014, calday.getYear());
  }

  @Test(timeout=4000)
  public void test06() throws Throwable{
    CalDay calday=new CalDay();
    assertEquals("", calday.toString());
  }

  @Test(timeout=4000)
  public void test07() throws Throwable{
    CalDay holder=new CalDay();
    CalDay calday=new CalDay();
    assertSame("0-0-0 ",calday.getFullInfomrationApp(calday));
    calday=new CalDay(new GregorianCalendar(2014,1,1));
    for(int i=0; i<5;i++){
      Appt appt=new Appt(5-i, 5, 1, 1, 2014, "", "", "");
      calday.addAppt(appt);
    }
    Appt appt=new Appt(15,1,1,1,2014,"","","");
    calday.addAppt(appt);

    assertNotNull(holder.getFullInfomrationApp(calday));
  }

  @Test(timeout=4000)
  public void test08() throws Throwable{
    CalDay cd=new CalDay(new GregorianCalendar(2014,2,2));
    assertTrue(cd.toString().length()>0);
  }

  @Test(timeout=4000)
  public void test09()throws Throwable{
    CalDay cd=new CalDay(new GregorianCalendar(2014,2,2));
    for(int i=0; i<5;i++){
      Appt appt=new Appt(i+1, 0, 2, 2, 2014, "", "", "");
      cd.addAppt(appt);
    }
    assertTrue(cd.toString().length()>0);
  }

  //Check the calday iterator() funciton, may be retrning things incorrectly.
  @Test(timeout=4000)
  public void test10() throws Throwable{
    CalDay cd=new CalDay(new GregorianCalendar(2014,2,2));
    for(int i=0; i<5;i++){
      Appt appt=new Appt(i+8, 0, 2, 2, 2014, "", "", "");
      cd.addAppt(appt);
    }
    cd.valid=true;
    cd.getFullInfomrationApp(cd);
  }

  @Test(timeout=4000)
  public void test11(){
    CalDay cd=new CalDay(new GregorianCalendar(2014,2,2));
    Appt appt=new Appt(13, 0, 2, 2, 2014, "", "", "");
    cd.addAppt(appt);
    appt=new Appt(0, 20, 2, 2, 2014, "", "", "");
    cd.addAppt(appt);
    cd.valid=true;
    assertTrue(cd.getFullInfomrationApp(cd).length()>0);
  }
}
