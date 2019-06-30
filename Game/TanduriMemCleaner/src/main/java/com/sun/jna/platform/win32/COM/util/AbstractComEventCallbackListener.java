/*    */ package com.sun.jna.platform.win32.COM.util;
/*    */ 
/*    */ import com.sun.jna.platform.win32.COM.IDispatchCallback;
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
/*    */ public abstract class AbstractComEventCallbackListener
/*    */   implements IComEventCallbackListener
/*    */ {
/* 20 */   IDispatchCallback dispatchCallback = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void setDispatchCallbackListener(IDispatchCallback dispatchCallback) { this.dispatchCallback = dispatchCallback; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\AbstractComEventCallbackListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */