package cn.com.zsyk.crm.common.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import cn.com.zsyk.crm.common.exception.ServiceException;

/**
 * 描述：日期工具类
 * @version 1.0
 */
public class DateUtil {

	public static final String TIME_FORMAT_Y_M_D = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YMD_LONG = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";
	public static final String DATE_FORMAT_YM = "yyyyMM";
	public static final String DATE_FORMAT_Y = "yyyy";

	@SuppressWarnings("unused")
	private static int datePart(String type, Date date) {
		Calendar cal = getCalendarInstance();
		cal.setTime(date);
		if (("day".equals(type)) || ("dd".equals(type)))
			return cal.get(5);
		if (("week".equals(type)) || ("ww".equals(type))) {
			int dayOfWeek = cal.get(7);
			cal.clear();
			return dayOfWeek;
		}
		if (("month".equals(type)) || ("mm".equals(type))) {
			return (cal.get(2) + 1);
		}
		if (("quarter".equals(type)) || ("qq".equals(type))) {
			int month = cal.get(2);
			return ((month + 3) / 3);
		}
		if (("year".equals(type)) || ("yy".equals(type))) {
			return cal.get(1);
		}
		throw new IllegalArgumentException("传入参数" + type + "不合法!");
	}

	public static String getNow(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String stringDate = simpleDateFormat.format(new Date());
		return stringDate;
	}

	private static Calendar getCalendarInstance() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.SIMPLIFIED_CHINESE);
		return cal;
	}

	public static Timestamp getTimestamp(Date value) {
		if (value == null)
			return null;
		return new Timestamp(value.getTime());
	}


	public static Date getDate(Date sDate) {
		Date tDate = (sDate == null) ? new Date() : sDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tDate);
		calendar.set(10, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(5);
	}

	public static String complexFormatChineseDate(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		String timeStr = calendar.get(1) + "年" + (calendar.get(2) + 1) + "月" + calendar.get(5) + "日";
		timeStr = timeStr + calendar.get(11) + "时" + calendar.get(12) + "分" + calendar.get(13) + "秒";
		calendar.clear();
		return timeStr;
	}

	public static String formatDate(Date _date, String _pattern) {
		if (_date == null) {
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_pattern);
		String stringDate = simpleDateFormat.format(_date);
		return stringDate;
	}

	public static String formatDate(Date _date) {
		return formatDate(_date, "yyyyMMdd");
	}
	
	public static String nowDate() {
		return formatDate(new Date());
	}

	public static String nowTime() {
		return formatDate(new Date(), "HHmmss");
	}
	
	public static String nowTimeStamp() {
		return formatDate(new Date(), "yyyyMMddHHmmss.S");
	}

	public static String nowDateTimeStamp() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	public static int getAgeFromBirthday(Date _birthday, Date _fromDate) {
		if (_fromDate == null) {
			_fromDate = new Date(System.currentTimeMillis());
		}
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_birthday);
		int birthdayYear = calendar.get(1);
		int birthdayMonth = calendar.get(2);
		int birthdayDay = calendar.get(5);
		calendar.clear();
		calendar.setTime(_fromDate);
		int currentYear = calendar.get(1);
		int currentMonth = calendar.get(2);
		int currentDay = calendar.get(5);
		calendar.clear();
		int age = currentYear - birthdayYear;
		if ((currentMonth < birthdayMonth) || (currentDay < birthdayDay)) {
			--age;
		}
		return age;
	}

	public static int getAgeFromBirthday(Date _birthday) {
		return getAgeFromBirthday(_birthday, new Date(System.currentTimeMillis()));
	}

	public static int getAgeFromBirthday(Timestamp _birthday) {
		return getAgeFromBirthday(new Date(_birthday.getTime()), new Date(System.currentTimeMillis()));
	}

	public static int getDay(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int day = calendar.get(5);
		calendar.clear();
		return day;
	}

	public static int getDayCount(Date _startDate, Date _endDate) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_startDate);
		int startDay = calendar.get(6);
		int startYear = calendar.get(1);
		calendar.clear();
		calendar.setTime(_endDate);
		int endDay = calendar.get(6);
		int endYear = calendar.get(1);
		calendar.clear();
		return ((endYear - startYear) * 365 + endDay - startDay);
	}

	public static int getHours(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int value = calendar.get(10);
		calendar.clear();
		return value;
	}

	public static int getMinutes(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int value = calendar.get(12);
		calendar.clear();
		return value;
	}

	public static int getMonth(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int month = calendar.get(2);
		calendar.clear();
		return (month + 1);
	}

	public static int getMonthCount(Date _startDate, Date _endDate) {
		Date startDate = _startDate;
		Date endDate = _endDate;
		boolean afterFlag = false;
		if (_startDate.after(_endDate)) {
			startDate = _endDate;
			endDate = _startDate;
			afterFlag = true;
		}
		Calendar calendar = getCalendarInstance();
		calendar.setTime(startDate);
		int startDay = calendar.get(5);
		int startMonth = calendar.get(2);
		int startYear = calendar.get(1);
		int countDayOfStartMonth = calendar.getActualMaximum(5);
		calendar.clear();
		calendar.setTime(endDate);
		int endDay = calendar.get(5);
		int endMonth = calendar.get(2);
		int endYear = calendar.get(1);
		calendar.clear();
		int result = (endYear - startYear) * 12 + endMonth - (startMonth + 1) + (endDay + countDayOfStartMonth
						- startDay) / countDayOfStartMonth;
		if (afterFlag) {
			return (-result);
		}
		return result;
	}

	public static int getSeconds(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int value = calendar.get(13);
		calendar.clear();
		return value;
	}

	public static int getYear(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		int year = calendar.get(1);
		calendar.clear();
		return year;
	}

	public static String simpleFormatChineseDate(Date _date) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(_date);
		String timeStr = calendar.get(1) + "年" + (calendar.get(2) + 1) + "月" + calendar.get(5) + "日";
		calendar.clear();
		return timeStr;
	}

	public static Timestamp getCurTime() {
		Timestamp sdate = new Timestamp(System.currentTimeMillis());
		return sdate;
	}

	@SuppressWarnings("unused")
	public static Date getPeriodEnd(Date thedate, char period) {
		Calendar cal = getCalendarInstance();
		cal.setTime(thedate);
		int day = cal.get(5);
		int month = cal.get(2);
		switch (period) {
			case 'M':
				int monthendday = cal.getActualMaximum(5);
				cal.set(5, monthendday);
				break;
			case 'Q':
				cal.add(2, 3 - (month % 3));
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'Y':
				cal.add(2, 12 - month);
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'H':
				cal.add(2, 6 - (month % 6));
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'T':
				if (day < 11) {
					cal.set(5, 10);
					break;
				}
				if (day < 21) {
					cal.set(5, 20);
					break;
				}
				cal.add(2, 1);
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'm':
				cal.add(5, 0 - day);
				break;
			case 'q':
				cal.add(2, 0 - (month % 3));
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'y':
				cal.add(2, 0 - month);
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 'h':
				cal.add(2, 0 - (month % 6));
				day = cal.get(5);
				cal.add(5, 0 - day);
				break;
			case 't':
				if (day < 11) {
					cal.add(5, 0 - day);
					break;
				}
				if (day < 21) {
					cal.set(5, 10);
					break;
				}
				cal.set(5, 20);
		}
		label406: return cal.getTime();
	}

	@SuppressWarnings("unused")
	public static Date getPeriodBegin(Date thedate, char period) {
		Calendar cal = getCalendarInstance();
		cal.setTime(thedate);
		int day = cal.get(5);
		int month = cal.get(2);
		switch (period) {
			case 'M':
				cal.add(5, 1 - day);
				break;
			case 'Q':
				cal.add(2, 0 - ((month - 1) % 3));
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'Y':
				cal.add(2, 1 - month);
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'H':
				cal.add(2, 0 - ((month - 1) % 6));
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'T':
				if (day < 11) {
					cal.set(5, 1);
					break;
				}
				if (day < 21) {
					cal.set(5, 11);
					break;
				}
				cal.set(5, 21);
				break;
			case 'm':
				cal.add(2, -1);
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'q':
				cal.add(2, -3 - ((month - 1) % 3));
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'y':
				cal.add(2, -11 - month);
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 'h':
				cal.add(2, -6 - ((month - 1) % 6));
				day = cal.get(5);
				cal.add(5, 1 - day);
				break;
			case 't':
				if (day < 11) {
					cal.add(2, -1);
					cal.set(5, 21);
					break;
				}
				if (day < 21) {
					cal.set(5, 1);
					break;
				}
				cal.set(5, 11);
		}
		label411: return cal.getTime();
	}

	public static Time getTimeByTimestamp(Timestamp _timesmp) {
		Calendar cal = getCalendarInstance();
		cal.setTimeInMillis(_timesmp.getTime());
		String str = String.valueOf(cal.get(11)) + ":" + String.valueOf(cal.get(12)) + ":" + String.valueOf(cal.get(
						13));
		return Time.valueOf(str);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareDate(Date date1, Date date2, int precision) {
		Calendar c = Calendar.getInstance();
		List fields = new ArrayList();
		fields.add(new Integer(1));
		fields.add(new Integer(2));
		fields.add(new Integer(5));
		fields.add(new Integer(12));
		fields.add(new Integer(13));
		fields.add(new Integer(14));
		Date d1 = date1;
		Date d2 = date2;
		if (fields.contains(new Integer(precision))) {
			c.setTime(date1);
			int field;
			for (int i = 0; i < fields.size(); ++i) {
				field = ((Integer) fields.get(i)).intValue();
				if (field > precision)
					c.set(field, 0);
			}
			d1 = c.getTime();
			c.setTime(date2);
			for (int i = 0; i < fields.size(); ++i) {
				field = ((Integer) fields.get(i)).intValue();
				if (field > precision)
					c.set(field, 0);
			}
			d2 = c.getTime();
		}
		return d1.compareTo(d2);
	}

	public static Calendar convertTimeZone(Calendar cal, String zone) {
		Calendar ret = Calendar.getInstance();
		TimeZone toZone = TimeZone.getTimeZone(zone);
		long curmillis = cal.getTimeInMillis();
		ret.setTimeInMillis(curmillis + toZone.getRawOffset() - cal.getTimeZone().getRawOffset());
		return ret;
	}

	public static Date dateAdd(int precision, int amount, Date d1) {
		if (d1 == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d1.getTime());
		cal.add(precision, amount);
		return cal.getTime();
	}

	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + day * 24L * 3600L * 1000L);
		return c.getTime();
	}

	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat(format);
			date = df.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

	// 通过出生日期获取年龄
	public static int getAge(Date input) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (cal.before(input)) {
			throw new ServiceException("出生日期在当前日期之后");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(input);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	public static String date2FormatString(Date dateStr) {
		if (dateStr == null)
			return null;
	    DateFormat format2 = new SimpleDateFormat(TIME_FORMAT_Y_M_D);  
	    return format2.format(dateStr);
	}

	public static String date2FormatStringYMDLong(Date dateStr) {
		if (dateStr == null)
			return null;
	    DateFormat format2 = new SimpleDateFormat(DATE_FORMAT_YMD_LONG);  
	    return format2.format(dateStr);
	}

	public static Date formatString2Date(String dateStr) throws ParseException {
		if (dateStr == null)
			return null;
	    DateFormat format2 = new SimpleDateFormat(TIME_FORMAT_Y_M_D);  
	    return format2.parse(dateStr);
	}

	
	public static String date2FormatStringYMD(String date) throws ParseException {
		String retString = null;
		if (StringUtils.isNotEmpty(date)) {
			if (date.length() ==8) {
				 date= date.substring(0,4) + "-" +  date.substring(4,6) + "-" +  date.substring(6,8);
			} else {
				date = date.substring(0,10);
			}
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YMD_LONG);
				formatter.setLenient(false);
				Date newDate = formatter.parse(date);
				formatter = new SimpleDateFormat(DATE_FORMAT_YMD);
				retString = formatter.format(newDate);
			} catch (Exception e) {
				throw new ServiceException("不是有效的日期");
			}
		}
		return retString;
	}
	
	/** 
    * 获取未来 第 past 天的日期 
    * @param past 
    * @return 
    */  
   public static String getFetureDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
   } 
   
}
