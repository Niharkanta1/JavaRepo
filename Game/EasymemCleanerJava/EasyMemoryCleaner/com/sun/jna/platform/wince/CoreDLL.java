/*    */ package com.sun.jna.platform.wince;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.win32.W32APIOptions;
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
/*    */ public interface CoreDLL
/*    */   extends WinNT
/*    */ {
/* 25 */   public static final CoreDLL INSTANCE = (CoreDLL)Native.loadLibrary("coredll", CoreDLL.class, W32APIOptions.UNICODE_OPTIONS);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\wince\CoreDLL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */