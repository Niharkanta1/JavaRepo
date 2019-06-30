/*    */ package com.sun.jna;
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
/*    */ public class LastErrorException
/*    */   extends RuntimeException
/*    */ {
/*    */   private int errorCode;
/*    */   
/* 25 */   private static String formatMessage(int code) { return Platform.isWindows() ? ("GetLastError() returned " + code) : ("errno was " + code); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String parseMessage(String m) {
/*    */     try {
/* 32 */       return formatMessage(Integer.parseInt(m));
/*    */     }
/* 34 */     catch (NumberFormatException e) {
/* 35 */       return m;
/*    */     } 
/*    */   }
/*    */   
/*    */   public LastErrorException(String msg) {
/* 40 */     super(parseMessage(msg.trim()));
/*    */     try {
/* 42 */       if (msg.startsWith("[")) {
/* 43 */         msg = msg.substring(1, msg.indexOf("]"));
/*    */       }
/* 45 */       this.errorCode = Integer.parseInt(msg);
/*    */     }
/* 47 */     catch (NumberFormatException e) {
/* 48 */       this.errorCode = -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public int getErrorCode() { return this.errorCode; }
/*    */ 
/*    */   
/*    */   public LastErrorException(int code) {
/* 62 */     super(formatMessage(code));
/* 63 */     this.errorCode = code;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\LastErrorException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */