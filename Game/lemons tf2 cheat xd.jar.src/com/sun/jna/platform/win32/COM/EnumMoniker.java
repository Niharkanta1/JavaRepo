/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.platform.win32.WinDef;
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
/*    */ public class EnumMoniker
/*    */   extends Unknown
/*    */   implements IEnumMoniker
/*    */ {
/* 25 */   public EnumMoniker(Pointer pointer) { super(pointer); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Next(WinDef.ULONG celt, PointerByReference rgelt, WinDef.ULONGByReference pceltFetched) {
/* 34 */     int vTableId = 3;
/*    */     
/* 36 */     return (WinNT.HRESULT)_invokeNativeObject(3, new Object[] { getPointer(), celt, rgelt, pceltFetched }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Skip(WinDef.ULONG celt) {
/* 44 */     int vTableId = 4;
/*    */     
/* 46 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] { getPointer(), celt }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Reset() {
/* 54 */     int vTableId = 5;
/*    */     
/* 56 */     return (WinNT.HRESULT)_invokeNativeObject(5, new Object[] { getPointer() }, WinNT.HRESULT.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinNT.HRESULT Clone(PointerByReference ppenum) {
/* 64 */     int vTableId = 6;
/*    */     
/* 66 */     return (WinNT.HRESULT)_invokeNativeObject(6, new Object[] {
/* 67 */           getPointer(), ppenum }, WinNT.HRESULT.class);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\EnumMoniker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */