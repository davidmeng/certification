/*    */ package snail.certification.utils;
/*    */ 
/*    */ public class Utils
/*    */ {
/*    */   public static boolean isNull(String a)
/*    */   {
/*  6 */     if ((a == null) || (a.trim().length() == 0)) {
/*  7 */       return true;
/*    */     }
/*  9 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean isNotNull(String a) {
/* 13 */     return !isNull(a);
/*    */   }
/*    */ 
/*    */   public static boolean isNotNull(Integer[] a) {
/* 17 */     if ((a == null) || (a.length == 0)) {
/* 18 */       return false;
/*    */     }
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean isNotNull(Integer a) {
/* 24 */     if ((a == null) || (a.intValue() == 0)) {
/* 25 */       return false;
/*    */     }
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean isNotNull(Short a) {
/* 31 */     if ((a == null) || (a.intValue() == 0)) {
/* 32 */       return false;
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\Documents\certificate\WEB-INF\classes\
 * Qualified Name:     mfw.acegi.constants.Utils
 * JD-Core Version:    0.6.2
 */