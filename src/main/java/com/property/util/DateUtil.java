package com.property.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {


	public static Date removeTimeFromDate(Date date) {
		 
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
	
	public static Date removeDayTimeFromDate(Date date) {
		 
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
	
	public static Integer getCurrentDayOfMonth() {
		Date today = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getCurrentYear() {
		Date today = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		return cal.get(Calendar.YEAR);
	}
	
	public static Integer getCurrentMonth() {
		Date today = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		return cal.get(Calendar.MONTH)+1;
	}
	
	public static Date getPreviousMonth(Date date) { 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,-1);
		return cal.getTime();
	}
	
	public static Date getNextMonth(Date date) { 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,1);
		return cal.getTime();
	}
	
	public static List<Date> getMonthsBetweenDateRange(Date startDate, Date endDate){
		Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();
        List<Date> finalList = new ArrayList<Date>();
        beginCalendar.setTime(startDate);
        finishCalendar.setTime(endDate);
        while (beginCalendar.before(finishCalendar)) {
        	finalList.add(beginCalendar.getTime());
        	beginCalendar.add(Calendar.MONTH, 1);
        }
		return finalList;
	}
	
	public static Boolean isDateBetweenRange(Date actualDate,Date startDate,Date endDate) 
    {
		return startDate.compareTo(actualDate) * actualDate.compareTo(endDate) >= 0;
    }
	

}
