/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public interface Version
/*    */   extends StdCallLibrary
/*    */ {
/* 26 */   public static final Version INSTANCE = (Version)Native.loadLibrary("version", Version.class, W32APIOptions.DEFAULT_OPTIONS);
/*    */   
/*    */   int GetFileVersionInfoSize(String paramString, IntByReference paramIntByReference);
/*    */   
/*    */   boolean GetFileVersionInfo(String paramString, int paramInt1, int paramInt2, Pointer paramPointer);
/*    */   
/*    */   boolean VerQueryValue(Pointer paramPointer, String paramString, PointerByReference paramPointerByReference, IntByReference paramIntByReference);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */