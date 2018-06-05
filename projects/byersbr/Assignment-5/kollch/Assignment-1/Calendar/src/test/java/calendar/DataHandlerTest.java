
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


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
    assertTrue(dh.save());
  }

  @Test(timeout=4000)
  public void test02() throws Throwable{
    DataHandler dh=new DataHandler();
    for(int i=0; i<10;i++){
      Appt appt=new Appt(i+1,1,2014,"","","");
      dh.saveAppt(appt);
    }
    assertEquals(3, dh.getApptRange(new GregorianCalendar(2013,2,1),new GregorianCalendar(2013,2,4)).size());
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
  }

  @Test(timeout=4000)
  public void test03() throws Throwable{
    DataHandler dh=new DataHandler();
    for(int i=0; i<3;i++){
      Appt appt=new Appt(i+1,0,1,1,2014,"","","");
      dh.saveAppt(appt);
    }
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
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
    assertEquals(1, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,2)).size());
  }

  @Test(timeout=4000)
  public void test06() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 1, 1, 1);
    dh.saveAppt(appt);
    assertEquals(366, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2015,1,2)).size());    
  }

  @Test(timeout=4000)
  public void test07() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 1, 1, 2);
    dh.saveAppt(appt);
    LinkedList<CalDay> listCD=new LinkedList<CalDay>();
    try {
      listCD=dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
      assertEquals(8, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size()); 
    } catch (Exception e) {
      assertTrue(false);
    }
    for(int i=0; i<listCD.size();i++){
      assertNotNull(listCD.get(i));
    }
  }

  @Test(timeout=4000)
  public void test10() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 2, 1, 2);
    dh.saveAppt(appt);    
    LinkedList<CalDay> listCD=new LinkedList<CalDay>();
    try {
      listCD=dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
      assertEquals(8, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size()); 
    } catch (Exception e) {
      assertTrue(false);
    }    
    for(int i=0; i<listCD.size();i++){
      assertNotNull(listCD.get(i));
    }
  }

  @Test(timeout=4000)
  public void test11() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 3, 1, 2);
    dh.saveAppt(appt);
    dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));    
    LinkedList<CalDay> listCD=new LinkedList<CalDay>();
    try {
      listCD=dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
      assertEquals(8, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9)).size()); 
    } catch (Exception e) {
      assertTrue(false);
    }    
    for(int i=0; i<listCD.size();i++){
      assertNotNull(listCD.get(i));
    }     
  }

  @Test(timeout=4000)
  public void test12() throws Throwable{
    ByteArrayOutputStream data=new ByteArrayOutputStream(512);
    System.setOut(new PrintStream(data));
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{}, 2, 1, 1000);
    dh.saveAppt(appt);    
    LinkedList<CalDay> listCD=new LinkedList<CalDay>();
    try {
      listCD=dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
      assertEquals(8, listCD.size()); 
    } catch (Exception e) {
      assertTrue(false);
    }
    for(int i=0; i<listCD.size();i++){
      assertNotNull(listCD.get(i));
    }
    assertEquals("",data.toString());
  }

  @Test(timeout=4000)
  public void test13() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(-1,-1,-1,"","","");
    dh.saveAppt(appt);
    dh.deleteAppt(appt);
  }
  @Test(timeout=4000)
  public void test14() throws Throwable{
    DataHandler dh=new DataHandler();
    //dh.valid=false;
    try {
      assertEquals(null, dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9))); 
    } catch (Exception e) {
      assertTrue(false);
    } 
  }
  @Test(timeout=4000)
  public void test15() throws Throwable{
    DataHandler dh=new DataHandler();
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    appt.setRecurrence(new int[]{1}, 3, 1, 2);
    dh.saveAppt(appt);
    dh.getApptRange(new GregorianCalendar(2014,1,1),new GregorianCalendar(2014,1,9));
    try {
      assertEquals(8, dh.getApptRange(new GregorianCalendar(2019,1,1),new GregorianCalendar(2014,1,9)).size()); 
    } catch (Exception e) {
      assertTrue(true);
    }     
  }

  @Test(timeout=4000)
  public void test16() throws Throwable{
    Appt appt=new Appt(1,0,1,1,2014,"","","");
    GregorianCalendar cal=new GregorianCalendar(2014, 1, 1);
    assertNull(DataHandler.getNextApptOccurrence(appt,cal));
  }
  @Test(timeout=4000)
  public void test17() throws Throwable{
    Appt appt=new Appt(1,0,1,1,2014,"","","");    
    appt.setRecurrence(new int[0], Appt.RECUR_BY_WEEKLY, 1, 1);
    GregorianCalendar cal=new GregorianCalendar(2014, 1, 1);
    //cal.add(Calendar.DAY_OF_WEEK,7);
    assertEquals(cal.DATE,DataHandler.getNextApptOccurrence(appt,cal).DATE);
  }
  @Test(timeout=4000)
  public void test18() throws Throwable{
    Appt appt=new Appt(1,0,1,1,2014,"","","");    
    appt.setRecurrence(new int[]{1}, Appt.RECUR_BY_WEEKLY, 1, 2);
    GregorianCalendar cal=new GregorianCalendar(2014, 1, 1);
    //cal.add(Calendar.DAY_OF_WEEK,7);
    assertEquals(cal.DATE,DataHandler.getNextApptOccurrence(appt,cal).DATE);
  }
}
