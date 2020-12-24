package tt.helpsys.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 日期工具包
 * @author TRY
 *
 */
public class DateUtil {
	public static Date getToday(Date date){		
		Calendar calendar = new GregorianCalendar();		
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date getYestoday(Date date){
		Calendar calendar = new GregorianCalendar();		
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date getTomorrow(Date date){
		Calendar calendar = new GregorianCalendar();		
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date getWeekAgo(Date date){
		Calendar calendar = new GregorianCalendar();		
		calendar.setTime(date);
		calendar.add(calendar.DATE, -7);//减7天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date getMonthAgo(Date date){
		Calendar calendar = new GregorianCalendar();		
		calendar.setTime(date);
		calendar.add(calendar.DATE, -31);//减31天 
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
}
