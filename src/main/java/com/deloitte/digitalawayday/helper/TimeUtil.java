/**
 * 
 */
package com.deloitte.digitalawayday.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
/**
 * @author raviranjan
 *
 */
@Service
public class TimeUtil {


	private SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");


	public String getEndTime(String myTime, int duration) throws ParseException
	{

		Date date = df.parse(myTime); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, duration);
		String newTime = df.format(cal.getTime());
		return newTime;
	}


	public String get12HrTime(String myTime) throws ParseException
	{
		Date date = df.parse(myTime); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String newTime = _12HourSDF.format(cal.getTime());
		return newTime;
	}

	public boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
		return !candidate.isBefore(start) && !candidate.isAfter(end);  // Inclusive.
	}

}
