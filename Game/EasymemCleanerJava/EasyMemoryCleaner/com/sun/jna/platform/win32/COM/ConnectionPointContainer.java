/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
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
/*    */ public class ConnectionPointContainer
/*    */   extends Unknown
/*    */   implements IConnectionPointContainer
/*    */ {
/* 24 */   public ConnectionPointContainer(Pointer pointer) { super(pointer); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT EnumConnectionPoints() {
/* 31 */     int vTableId = 3;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT FindConnectionPoint(Guid.REFIID riid, PointerByReference ppCP) {
/* 45 */     int vTableId = 4;
/* 46 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] {
/* 47 */           getPointer(), riid, ppCP }, WinNT.HRESULT.class);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\ConnectionPointContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */