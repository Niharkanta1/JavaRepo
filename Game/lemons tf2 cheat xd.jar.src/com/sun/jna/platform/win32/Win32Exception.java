/*    */ package com.sun.jna.platform.win32;
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
/*    */ public class Win32Exception
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private WinNT.HRESULT _hr;
/*    */   
/* 33 */   public WinNT.HRESULT getHR() { return this._hr; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Win32Exception(WinNT.HRESULT hr) {
/* 42 */     super(Kernel32Util.formatMessage(hr));
/* 43 */     this._hr = hr;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public Win32Exception(int code) { this(W32Errors.HRESULT_FROM_WIN32(code)); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Win32Exception.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */