package snail.certification.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static java.sql.Date formatToSqlDate(String dateStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(dateStr);
			java.sql.Date staDate = new java.sql.Date(date.getTime());
			return staDate;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return new java.sql.Date(new Date().getTime());
	}
	
	
	public static java.sql.Date formatToSqlDate(String dateStr){
		return formatToSqlDate(dateStr,"yyyy-MM-dd");
	}

}
