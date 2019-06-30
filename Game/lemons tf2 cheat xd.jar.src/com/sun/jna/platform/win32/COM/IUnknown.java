/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.platform.win32.Guid;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.ptr.PointerByReference;
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
/*    */ public interface IUnknown
/*    */ {
/* 32 */   public static final Guid.IID IID_IUNKNOWN = new Guid.IID("{00000000-0000-0000-C000-000000000046}");
/*    */   
/*    */   WinNT.HRESULT QueryInterface(Guid.GUID.ByValue paramByValue, PointerByReference paramPointerByReference);
/*    */   
/*    */   int AddRef();
/*    */   
/*    */   int Release();
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\IUnknown.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */