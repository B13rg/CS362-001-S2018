package calendar;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Random Test Generator for Appt class.
 */

public class ApptRandomTest {
	@Test
	public void setRecurDaysTest(){
		Appt appt=new Appt(1,1,1,"","","");		
		for(int i=0; i<10; i++){
			int[] arr=null;
			if(ValGen.RandBool()){
				arr=ValGen.RandIntArray(3, 8);
			}
			appt.setRecurrence(arr, 1, 1, 1);
		}
		
	}

	@Test
	public void setValidRandomTest(){
		for (int i=0; i < 90000; i++) {
			int startHour = ValGen.RandInt(25,-3);
			int startMinute = ValGen.RandInt(70,-5);
            int startDay = ValGen.RandInt(40,-8);
            int startMonth = ValGen.RandInt(14,-2);
            int startYear = ValGen.RandInt(300,-10);
            String title = "Birthday Party";
            String description = "This is my birthday party.";
			String emailAddress = "xyz@gmail.com";
			for(int k=0; k<5; k++){
				Appt appt = new Appt(startHour,startMinute,startDay, startMonth, startYear, 
					title, description, emailAddress
				);
				try{
					appt.setValid();
				}
				catch(Exception e){};
            }
        }
	}

	private static final long TestTimeout = 60; /* Timeout at not 30 seconds */
	private static final int NUM_TESTS = 100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random) {
		String[] methodArray = new String[] { "setTitle", "setRecurrence" };// The list of the of methods to be tested
																			// in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and methodArray.length
													// (exclusive)

		return methodArray[n]; // return the method name
	}

	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
	public static int RandomSelectRecur(Random random) {
		// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
		int[] RecurArray = new int[] { Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY };
		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and RecurArray.length
													// (exclusive)
		return RecurArray[n]; // return the value of the appointments to recur
	}

	/**
	 * Return a randomly selected appointments to recur forever or Never recur !.
	 */
	public static int RandomSelectRecurForEverNever(Random random) {
		int[] RecurArray = new int[] { Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER };// The list of the of
																							// setting appointments to
																							// recur
																							// RECUR_NUMBER_FOREVER, or
																							// RECUR_NUMBER_NEVER

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and RecurArray.length
													// (exclusive)
		return RecurArray[n]; // return appointments to recur forever or Never recur
	}

	/**
	 * Generate Random Tests that tests Appt Class.
	 */
	@Test
	public void randomtest() throws Throwable {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		//System.out.println("Start testing...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
				String title = "Birthday Party";
				String description = "This is my birthday party.";
				String emailAddress = "xyz@gmail.com";

				// Construct a new Appointment object with the initial data
				// Construct a new Appointment object with the initial data
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description,
						emailAddress);

				if (!appt.getValid())
					continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setTitle")) {
						String newTitle = (String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);
					} else if (methodName.equals("setRecurrence")) {
						int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
						int recur = ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					}
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
					//System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {

		}

		//System.out.println("Done testing...");
	}

}
