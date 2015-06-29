/*   */ package snail.certification.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*   */ 
/*   */ public class Constants
/*   */ {
/* 7 */   public static String companyName = "歌山";
/* 8 */   public static String companyIcon = "images/geshan.jpg";
/* 9 */   public static String admin = "david";
			public static Date DATE_FOR_NULL = new Date();
			static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			static {
				try {
					DATE_FOR_NULL = sdf.parse("19710101");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
/*   */ }

/* Location:           D:\Documents\certificate\WEB-INF\classes\
 * Qualified Name:     mfw.acegi.constants.Constants
 * JD-Core Version:    0.6.2
 */