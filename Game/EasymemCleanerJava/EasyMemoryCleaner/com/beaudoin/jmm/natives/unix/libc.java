/*    */ package com.beaudoin.jmm.natives.unix;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.NativeLibrary;
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
/*    */ public final class libc
/*    */ {
/*    */   public static native int getuid();
/*    */   
/*    */   public static native int getpid();
/*    */   
/*    */   static  {
/* 36 */     Native.register(NativeLibrary.getInstance("c"));
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\native\\unix\libc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */