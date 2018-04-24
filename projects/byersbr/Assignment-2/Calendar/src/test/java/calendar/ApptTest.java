/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.text.ElementIterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.hamcrest.Description;

import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  Document helperBuildDoc(){
    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;
    Document ret;

    try {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      ret = builder.newDocument();
    } catch(Exception e) {
      e.printStackTrace();
      ret=null;
    }
    return ret;
  }

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      appt0.getRecurBy();
      //assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
@Test(timeout = 4000)
public void test01()  throws Throwable  {
  String title="This is a title";
  String description="This is a small description";
  String email="Email@null.com";
  Appt apptCur=new Appt(15,2,2018,title,description,email);
  String apptString=apptCur.toString();
  //Check that the object was initialized correctly.
  assertEquals(15,apptCur.getStartDay());
  assertEquals(2,apptCur.getStartMonth());
  assertEquals(2018, apptCur.getStartYear());
  assertEquals(title, apptCur.getTitle());
  assertEquals(description, apptCur.getDescription());
  assertEquals(email, apptCur.getEmailAddress());
  //assertEquals(apptString, 
  //  "\t15/12/2018 at \t");
  assertTrue(apptCur.getValid());
}

@Test(timeout=4000)
//Tests both the setXmlElement(Element) method and getXmlElement()
public void test02() throws Throwable{
  Appt appt=new Appt(15,2,2018,"title","desc","email");
  Document doc;
  //Use our helper to create our document, so we can make an element
  doc=helperBuildDoc();
  Element xml;
  xml=doc.createElement("item");

  appt.setXmlElement(xml);
  assertEquals(xml, appt.getXmlElement());
}

@Test(timeout=4000)
//This will test checking if the appointment is valid.
public void test03() throws Throwable{
  //First create the appointments
  int[][] values={ {1,1,2018,1},
                    {32,1,2018,0},
                    {1,13,2018,0},
                    {28,2,2001,0},
                    {29,2,2000,0},
                    {1,1,-2000,0},
                    {-1,1,1,0},
                    {1,-1,2012,0},
                    {67,1,2018,0},
                    {-2,1,2018,0}};

  for(int i=0; i<values.length;i++){
    Appt appt=new Appt(values[i][0],values[i][1],values[i][2],"","","");
    appt.setValid();
    if(values[i][3]==1){
      appt.getValid();
      //assertTrue(appt.getValid());
    }else{
      appt.getValid();
      //assertFalse(appt.getValid());
    }
  }
}

  @Test(timeout=4000)
  public void test04() throws Throwable{
    String newEmail="testemail@null.com";
    Appt appt=new Appt(1,1,2018,"","",newEmail);
    int newHour=2;
    int newMinute=34;
    int newDay=14;
    int newMonth=5;
    int newYear=2045;
    String newTitle="NewTitle";
    String newDesc="Decsription";

    appt.setStartHour(newHour);
    appt.setStartMinute(newMinute);
    appt.setStartDay(newDay);
    appt.setStartMonth(newMonth);
    appt.setStartYear(newYear);
    appt.setTitle(newTitle);
    appt.setDescription(newDesc);
    
    assertEquals(newHour, appt.getStartHour());
    assertEquals(newMinute, appt.getStartMinute());
    assertEquals(newDay, appt.getStartDay());
    assertEquals(newMonth, appt.getStartMonth());
    assertEquals(newYear, appt.getStartYear());
    assertEquals(newTitle, appt.getTitle());
    assertEquals(newDesc, appt.getDescription());
    assertEquals(newEmail, appt.getEmailAddress());
  }

  @Test(timeout=4000)
  public void test05() throws Throwable{
    int day=4;
    int month=5;
    int year=2004;
    Appt appt=new Appt(day,month,year,"","","");

    assertTrue(appt.isOn(day, month, year));
    assertFalse(appt.isOn(day, month, year+1));
    assertFalse(appt.isOn(day, month+1, year));
    assertFalse(appt.isOn(day+1, month, year));
  }

  @Test(timeout=4000)
  public void test06() throws Throwable{
    Appt timer=new Appt(3, 2, 5, 3, 4, "", "", "");
    Appt edgeCase=new Appt(0, 2, 5, 6, 2783, "", "", "");
    Appt noTimer=new Appt(4, 3, 4, "", "", "");

    assertTrue(timer.hasTimeSet());
    assertFalse(noTimer.hasTimeSet());
    assertTrue(edgeCase.hasTimeSet());
  }

  @Test(timeout=4000)
  public void test07() throws Throwable{
    Appt appt=new Appt(10, 10, 2010, "", "", "");
    int recurBy=4;
    int recurIncrement=3;
    int recurNumber=6;
    int[] recurDays=new int[0];
    
    appt.setRecurrence(null, recurBy, recurIncrement, recurNumber);
    assertEquals(recurBy, appt.getRecurBy());
    assertEquals(recurIncrement, appt.getRecurIncrement());
    assertEquals(recurNumber, appt.getRecurNumber());
    assertEquals(recurDays, appt.getRecurDays());
  }

  @Test(timeout=4000)
  public void test11() throws Throwable{
    Appt appt=new Appt(1,1,2018,"","","");
    int[]recurDays={3,4,5};
    appt.setRecurrence(recurDays,4, 3, 6);
    assertEquals(recurDays, appt.getRecurDays());
    assertTrue(appt.isRecurring());
  }

  @Test(timeout=4000)
  public void test08() throws Throwable{
    Appt appt=new Appt(3, 5, 2016, "", "", "");
    assertFalse(appt.isRecurring());
  }

  @Test(timeout=4000)
  //This will test checking if the appointment is valid.
  public void test09() throws Throwable{
    //First create the appointments
    int[][] values={ {0,1,1,1,2018,1},
                      {-1,1,1,1,2018,0},
                      {25,2,32,1,2018,0},
                      {3,-1,28,2,2001,0},
                      {3,67,2,2,2000,0}};

    for(int i=0; i<values.length;i++){
      Appt appt=new Appt(values[i][0],values[i][1],values[i][2],values[i][3],values[i][4],"","","");
      appt.setValid();
      if(values[i][5]==1){
        assertTrue(appt.getValid());
        appt.toString();
      }else{
        assertFalse(appt.getValid());
        
      }
    }
  }

  @Test(timeout=4000)
  public void test10() throws Throwable{
    Appt appt=new Appt(1,1,2014,null,null,null);
    assertEquals("", appt.getTitle());
    assertEquals("", appt.getDescription());
    assertEquals("", appt.getEmailAddress());
  }
}

