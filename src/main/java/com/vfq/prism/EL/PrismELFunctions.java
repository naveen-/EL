package com.vfq.prism.EL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class will contain EL functions which are used in Oozie server.
 * jar file to be copied into /var/lib/oozie/  path and oozie server needs to be restarted for oozie to picup this jar.
 *
 */
public class PrismELFunctions 
{
	/**
	 * This function returns list of dates  in yyyyMMdd,yyyyMMdd,yyyyMMdd format.
	 * Inputs current_date in yyyyMMdd format and number_of_days.
	 * Number of days can be –ve or +ve.
	 * Example:
	 * getDates(‘20150902’,-5) will return  ‘20150902,20150901,20150831,20150830,20150829’
	 * getDates(‘20150912’,5) will return ‘20150912,20150913,20150914,20150915,20150916’
	 * @param currDate
	 * @param numOfDays
	 * @return
	 */
	public static String getDates( String currDate, int numOfDays )
	{		
		SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = new GregorianCalendar();
		StringBuilder sb = new StringBuilder();
		String sep="", commaSep =",";
		try {
			Date currentDate = dtFormatter.parse(currDate);

			if (numOfDays<0){
				for (int i=0; i<Math.abs(numOfDays); i++){		
					cal.setTime(currentDate);		 
					cal.add(cal.DATE, -i);					
					sb.append(sep).append(dtFormatter.format(cal.getTime()));
					sep = commaSep;
					
				}
			}else{
				for (int i=0; i<numOfDays; i++){		
					cal.setTime(currentDate);		 
					cal.add(cal.DATE, i);					
					sb.append(sep).append(dtFormatter.format(cal.getTime()));
					sep = commaSep;
				}
			}
						

		} catch (ParseException e) { 
			e.printStackTrace();
		}

		return sb.toString();
	}
}
