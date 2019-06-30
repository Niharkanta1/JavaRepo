/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.platform.win32.Guid;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import com.sun.jna.platform.win32.WinNT;
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
/*    */ public class ConnectionPoint
/*    */   extends Unknown
/*    */   implements IConnectionPoint
/*    */ {
/* 24 */   public ConnectionPoint(Pointer pointer) { super(pointer); }
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT GetConnectionInterface(Guid.IID iid) {
/* 29 */     int vTableId = 3;
/* 30 */     return (WinNT.HRESULT)_invokeNativeObject(3, new Object[] { getPointer(), iid }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */   
/* 34 */   void GetConnectionPointContainer() { int vTableId = 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Advise(IUnknownCallback pUnkSink, WinDef.DWORDByReference pdwCookie) {
/* 40 */     int vTableId = 5;
/*    */     
/* 42 */     return (WinNT.HRESULT)_invokeNativeObject(5, new Object[] { getPointer(), pUnkSink.getPointer(), pdwCookie }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Unadvise(WinDef.DWORD dwCookie) {
/* 48 */     int vTableId = 6;
/*    */     
/* 50 */     return (WinNT.HRESULT)_invokeNativeObject(6, new Object[] { getPointer(), dwCookie }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */   
/* 54 */   void EnumConnections() { int vTableId = 7; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\ConnectionPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */