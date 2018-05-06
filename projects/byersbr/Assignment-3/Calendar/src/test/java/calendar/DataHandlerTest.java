
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
    Appt appt=new Appt(1, 1, 2014, "", "", "");
    DataHandler dh=new DataHandler("fileName", false);
    //assertFalse(dh.saveAppt(new Appt(-2, -2, -2, "", "", "")));
    //assertTrue(dh.saveAppt(appt));
    dh=new DataHandler("filename",true);
    assertTrue(dh.saveAppt(appt));
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
    DataHandler dh=new DataHandler();
    assertFalse(dh.save());
  }

  @Test(timeout=4000)
  public void test02() throws Throwable{
    DataHandler dh=new DataHandler();
    for(int i=0; i<10;i++){
      Appt appt=new Appt(i+1,1,2014,"","","");
      dh.saveAppt(appt);
    }
    assertEquals(0, dh.getApptRange(new GregorianCalendar(2013,2,1),new GregorianCalendar(2013,2,4)).size());
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
  }

  @Test(timeout=4000)
  public void test03() throws Throwable{
    DataHandler dh=new DataHandler();
    for(int i=0; i<3;i++){
      Appt appt=new Appt(i+1,0,1,1,2014,"","","");
      dh.saveAppt(appt);
    }
    assertEquals(3, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
  }

  @Test(timeout=4000)
  public void test04() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(-4, 3, 2018, "", "", "");
    assertFalse(dh.deleteAppt(appt));
    assertTrue(dh.saveAppt(appt));
  }
  @Test(timeout=4000)
  public void test08() throws Throwable{
    DataHandler dh=new DataHandler("test",true);
    Appt appt=new Appt(2,3,2018,"","","");
    assertFalse(dh.deleteAppt(appt));
    dh.saveAppt(appt);
    assertTrue(dh.deleteAppt(appt));
  }

  @Test(timeout=4000)
  public void test09() throws Throwable{
    DataHandler dh=new DataHandler("test",false);
    Appt appt=new Appt(2,3,2018,"","","");
    assertFalse(dh.deleteAppt(appt));
    dh.saveAppt(appt);
    assertTrue(dh.deleteAppt(appt));
  }

  @Test(timeout=4000)
  public void test05() throws Throwable{
    DataHandler dh=new DataHandler();
    for(int i=0; i<3;i++){
      Appt appt=new Appt(i+1,0,1,1,2014,"","","");
      appt.setRecurrence(new int[]{1}, i, i, i);
      dh.saveAppt(appt);
    }
    assertEquals(3, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
  }

  @Test(timeout=4000)
  public void test06() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 1, 1, 1);
    dh.saveAppt(appt);
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2011,1,2)).size());    
  }

  @Test(timeout=4000)
  public void test07() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 1, 1, 2);
    dh.saveAppt(appt);
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size());    
  }

  @Test(timeout=4000)
  public void test10() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 2, 1, 2);
    dh.saveAppt(appt);
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size());    
  }

  @Test(timeout=4000)
  public void test11() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 3, 1, 2);
    dh.saveAppt(appt);
    dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size());    
  }

  @Test(timeout=4000)
  public void test12() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{}, 2, 1, 1000);
    dh.saveAppt(appt);
    dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
  }

  @Test(timeout=4000)
  public void test13() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(-1,-1,-1,"","","");
    dh.saveAppt(appt);
    dh.deleteAppt(appt);
  }
}
