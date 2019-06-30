/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Unknown
/*    */   extends COMInvoker
/*    */   implements IUnknown
/*    */ {
/*    */   public static class ByReference
/*    */     extends Unknown
/*    */     implements Structure.ByReference {}
/*    */   
/*    */   public Unknown() {}
/*    */   
/* 47 */   public Unknown(Pointer pvInstance) { setPointer(pvInstance); }
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
/*    */   public WinNT.HRESULT QueryInterface(Guid.GUID.ByValue riid, PointerByReference ppvObject) {
/* 60 */     return (WinNT.HRESULT)_invokeNativeObject(0, new Object[] {
/* 61 */           getPointer(), riid, ppvObject }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public int AddRef() { return _invokeNativeInt(1, new Object[] { getPointer() }); }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public int Release() { return _invokeNativeInt(2, new Object[] { getPointer() }); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\Unknown.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */