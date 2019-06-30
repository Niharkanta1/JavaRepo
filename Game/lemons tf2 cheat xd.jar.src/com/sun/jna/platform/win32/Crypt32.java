/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.PointerByReference;
/*    */ import com.sun.jna.win32.StdCallLibrary;
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
/*    */ 
/*    */ 
/*    */ public interface Crypt32
/*    */   extends StdCallLibrary
/*    */ {
/* 29 */   public static final Crypt32 INSTANCE = (Crypt32)Native.loadLibrary("Crypt32", Crypt32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   
/*    */   boolean CryptProtectData(WinCrypt.DATA_BLOB paramDATA_BLOB1, String paramString, WinCrypt.DATA_BLOB paramDATA_BLOB2, Pointer paramPointer, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT paramCRYPTPROTECT_PROMPTSTRUCT, int paramInt, WinCrypt.DATA_BLOB paramDATA_BLOB3);
/*    */   
/*    */   boolean CryptUnprotectData(WinCrypt.DATA_BLOB paramDATA_BLOB1, PointerByReference paramPointerByReference, WinCrypt.DATA_BLOB paramDATA_BLOB2, Pointer paramPointer, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT paramCRYPTPROTECT_PROMPTSTRUCT, int paramInt, WinCrypt.DATA_BLOB paramDATA_BLOB3);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Crypt32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */