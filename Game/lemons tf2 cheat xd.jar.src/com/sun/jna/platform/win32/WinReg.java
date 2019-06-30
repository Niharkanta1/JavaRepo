/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.ByReference;
/*    */ import com.sun.jna.win32.StdCallLibrary;
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
/*    */ public interface WinReg
/*    */   extends StdCallLibrary
/*    */ {
/*    */   public static class HKEY
/*    */     extends WinNT.HANDLE
/*    */   {
/*    */     public HKEY() {}
/*    */     
/* 31 */     public HKEY(Pointer p) { super(p); }
/* 32 */     public HKEY(int value) { super(new Pointer(value)); }
/*    */   }
/*    */   
/*    */   public static class HKEYByReference
/*    */     extends ByReference {
/* 37 */     public HKEYByReference() { this(null); }
/*    */ 
/*    */     
/*    */     public HKEYByReference(WinReg.HKEY h) {
/* 41 */       super(Pointer.SIZE);
/* 42 */       setValue(h);
/*    */     }
/*    */ 
/*    */     
/* 46 */     public void setValue(WinReg.HKEY h) { getPointer().setPointer(0L, (h != null) ? h.getPointer() : null); }
/*    */ 
/*    */     
/*    */     public WinReg.HKEY getValue() {
/* 50 */       Pointer p = getPointer().getPointer(0L);
/* 51 */       if (p == null)
/* 52 */         return null; 
/* 53 */       if (WinBase.INVALID_HANDLE_VALUE.getPointer().equals(p))
/* 54 */         return (WinReg.HKEY)WinBase.INVALID_HANDLE_VALUE; 
/* 55 */       WinReg.HKEY h = new WinReg.HKEY();
/* 56 */       h.setPointer(p);
/* 57 */       return h;
/*    */     }
/*    */   }
/*    */   
/* 61 */   public static final HKEY HKEY_CLASSES_ROOT = new HKEY(-2147483648);
/* 62 */   public static final HKEY HKEY_CURRENT_USER = new HKEY(-2147483647);
/* 63 */   public static final HKEY HKEY_LOCAL_MACHINE = new HKEY(-2147483646);
/* 64 */   public static final HKEY HKEY_USERS = new HKEY(-2147483645);
/* 65 */   public static final HKEY HKEY_PERFORMANCE_DATA = new HKEY(-2147483644);
/* 66 */   public static final HKEY HKEY_PERFORMANCE_TEXT = new HKEY(-2147483568);
/* 67 */   public static final HKEY HKEY_PERFORMANCE_NLSTEXT = new HKEY(-2147483552);
/* 68 */   public static final HKEY HKEY_CURRENT_CONFIG = new HKEY(-2147483643);
/* 69 */   public static final HKEY HKEY_DYN_DATA = new HKEY(-2147483642);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinReg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */