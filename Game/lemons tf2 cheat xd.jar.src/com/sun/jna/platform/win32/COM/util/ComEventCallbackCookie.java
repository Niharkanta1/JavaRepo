/*    */ package com.sun.jna.platform.win32.COM.util;
/*    */ 
/*    */ import com.sun.jna.platform.win32.WinDef;
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
/*    */ public class ComEventCallbackCookie
/*    */   implements IComEventCallbackCookie
/*    */ {
/*    */   WinDef.DWORD value;
/*    */   
/* 20 */   public ComEventCallbackCookie(WinDef.DWORD value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public WinDef.DWORD getValue() { return this.value; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\ComEventCallbackCookie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */