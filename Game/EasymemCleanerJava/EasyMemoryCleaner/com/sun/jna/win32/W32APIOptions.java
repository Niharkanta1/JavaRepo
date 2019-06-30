/*    */ package com.sun.jna.win32;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public interface W32APIOptions
/*    */   extends StdCallLibrary {
/*  8 */   public static final Map UNICODE_OPTIONS = new HashMap()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */ 
/*    */   
/* 15 */   public static final Map ASCII_OPTIONS = new HashMap()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */   
/* 21 */   public static final Map DEFAULT_OPTIONS = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS : UNICODE_OPTIONS;
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\win32\W32APIOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */