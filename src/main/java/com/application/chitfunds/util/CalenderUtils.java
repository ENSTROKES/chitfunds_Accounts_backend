package com.application.chitfunds.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalenderUtils {

    public static String dateFormat = "MM/dd/yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static Date ConvertMilliSecondsToFormattedDate(Long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
       
       // return simpleDateFormat.format(calendar.getTime());
        return calendar.getTime();
    }
    public static String ConvertMilliSecondsToFormattedDateInSimpleFormate(Long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
       
       return simpleDateFormat.format(calendar.getTime());
     
    }
    
    public static Integer getDaysDifferenceBwtweenTwoDate(Long startDate, Long endDate) {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    	Date firstDate=null;
    	Date secondDate=null;
		try {
			firstDate = sdf.parse(ConvertMilliSecondsToFormattedDateInSimpleFormate(startDate) );
			 secondDate = sdf.parse(ConvertMilliSecondsToFormattedDateInSimpleFormate(endDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        System.out.println(firstDate +"*******"+secondDate);
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        Integer diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	
    	return diff;
    }
    
    public static Integer getMonthDifferenceBwtweenTwoDate(Long startDate, Long endDate) {
    	Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(startDate));
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(endDate));
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + (endCalendar.get(Calendar.MONTH)+1) - (startCalendar.get(Calendar.MONTH)+1);
		
    	
    	return diffMonth;
    }
    public static Integer getDay(Long date) {
    	Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(date));
		Integer day = startCalendar.get(Calendar.DATE);
    	return day;
    }
    
    public static Integer getDaysInMonth(Long date) {
    	Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(date));
		Integer day = startCalendar.get(Calendar.DATE);

		Integer totalsDays = startCalendar.getActualMaximum(Calendar.DATE);
		Integer remainigDays = totalsDays-day;
    	return remainigDays;
    }
    public static Long getWeekDifferenceBwtweenTwoDate(Long startDate, Long endDate) {
    	Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(startDate));
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(endDate));
		//int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.WEEK) - startCalendar.get(Calendar.MONTH);
		Instant d1i = Instant.ofEpochMilli(startCalendar.getTimeInMillis());
	    Instant d2i = Instant.ofEpochMilli(endCalendar.getTimeInMillis());
	    System.out.println("start"+startCalendar);
	    System.out.println("end"+endCalendar);
	    LocalDateTime startDate1 = LocalDateTime.ofInstant(d1i, ZoneId.systemDefault());
	    LocalDateTime endDate1 = LocalDateTime.ofInstant(d2i, ZoneId.systemDefault());
		
		Long weeks = ChronoUnit.WEEKS.between(startDate1,endDate1);
    	return weeks;
    }
    
    public static void main(String [] args) {
    	//System.out.println(ConvertMilliSecondsToFormattedDate(new Date().getTime()));
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		 * Date firstDate=null; Date secondDate=null; try { firstDate =
		 * sdf.parse(ConvertMilliSecondsToFormattedDateInSimpleFormate(1677958937000l)
		 * ); secondDate =
		 * sdf.parse(ConvertMilliSecondsToFormattedDateInSimpleFormate(1683229337000l));
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * System.out.println(firstDate +"*******"+secondDate);
		 * System.out.println("sgdb........."+getWeekDifferenceBwtweenTwoDate(
		 * 1652637191000l,1683229337000l)); long diffInMillies =
		 * Math.abs(secondDate.getTime() - firstDate.getTime()); long diff =
		 * TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		 */
    	
    	
//    	Calendar startCalendar = new GregorianCalendar();
//		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(1630866661000l));
//		System.out.println("date  : "+startCalendar.getTime());
//		Integer day = startCalendar.get(Calendar.DATE);
//		System.out.println("day : "+day);
//		System.out.println("Month : "+(startCalendar.get(Calendar.MONTH)));
//		Integer totalsDays = startCalendar.getActualMaximum(Calendar.DATE);
//		System.out.println("Total days : "+totalsDays);
//		
//		System.out.println("Remainig days : "+(totalsDays-day));
//  
//    	      Calendar cal = Calendar.getInstance();
//    	      int res = cal.getActualMaximum(Calendar.DATE);
//    	      System.out.println("Today's Date = " + cal.getTime());
//    	      System.out.println("Last Date of the current month = " + res);
    	   
		/*
		 * Calendar startCalendar = new GregorianCalendar();
		 * startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(
		 * 1680307200000l)); Calendar endCalendar = new GregorianCalendar();
		 * endCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(
		 * 1687700413149l)); int diffYear = endCalendar.get(Calendar.YEAR) -
		 * startCalendar.get(Calendar.YEAR); int diffMonth = diffYear * 12 +
		 * endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		 * System.out.println(endCalendar.get(Calendar.MONTH)+1);
		 * System.out.println(startCalendar.get(Calendar.MONTH)+1);
		 * System.out.println("month "+diffMonth+"  year : "+diffYear); Calendar cal =
		 * Calendar.getInstance();
		 * cal.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(1641407461000l))
		 * ; int month = cal.get(Calendar.YEAR); System.out.println("test month :" +
		 * month);
		 */

    	
    	Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(CalenderUtils.ConvertMilliSecondsToFormattedDate(1630866661000l));
		Integer day = startCalendar.get(Calendar.DATE);
		System.out.println("Day"+day);
    }
}
