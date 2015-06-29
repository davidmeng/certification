/*    */ package snail.certification.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class NameUtils
/*    */ {
/*  7 */   private static String[] firstNames = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康" };
/*  8 */   private static String[] secondNames = { "子", "浩", "俊", "泽", "博", "思", "海", "珍", "嘉", "宇", "明", "浩", 
/*  9 */     "伟", "磊", "勇", "瑞", "蕊", "赛男", "宏", "晨", "浩南", "轶", "云", "旭", "坤", "昊", "晶晶", "静", "靖", "玮", 
/* 10 */     "玉", "莹莹", "玉莹", "家宝", "云", "林", "志林", "志英", "新颖", "峰", "洋", "亮", "小辉", "子龙", "云长", 
/* 11 */     "懿德", "泽楷", "景云", "泽类", "名寺", "明远", "郅治", "函", "炅", "大飞", "坤", "鹏", "小鹏", "史", "子然", "紫萱", "炫", "悬", "轩", "旋", "于强", "建国", "怡" };
/*    */ 
/* 14 */   private static int firstNameLength = firstNames.length;
/* 15 */   private static int secondNameLength = secondNames.length;
/*    */ 
/*    */   public static String getName()
/*    */   {
/* 19 */     Random rnd = new Random();
/* 20 */     String name = firstNames[rnd.nextInt(firstNameLength)] + secondNames[rnd.nextInt(secondNameLength)];
/*    */ 
/* 22 */     return name;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 26 */     for (int i = 0; i < 100; i++)
/* 27 */       System.out.println(getPersonId());
/*    */   }
/*    */ 
/*    */   public static String getPersonId()
/*    */   {
/* 32 */     Random rnd = new Random();
/* 33 */     return ""+rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10);
/*    */   }
/*    */ }

/* Location:           D:\Documents\certificate\WEB-INF\classes\
 * Qualified Name:     mfw.acegi.constants.NameUtils
 * JD-Core Version:    0.6.2
 */