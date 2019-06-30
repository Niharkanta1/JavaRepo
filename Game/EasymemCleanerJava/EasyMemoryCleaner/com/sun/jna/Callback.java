/*    */ package com.sun.jna;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Callback
/*    */ {
/*    */   public static final String METHOD_NAME = "callback";
/* 47 */   public static final Collection FORBIDDEN_NAMES = Arrays.asList(new String[] { "hashCode", "equals", "toString" });
/*    */   
/*    */   public static interface UncaughtExceptionHandler {
/*    */     void uncaughtException(Callback param1Callback, Throwable param1Throwable);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\Callback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */