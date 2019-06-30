/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public interface NtDll
/*    */   extends StdCallLibrary
/*    */ {
/* 28 */   public static final NtDll INSTANCE = (NtDll)Native.loadLibrary("NtDll", NtDll.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   
/*    */   int ZwQueryKey(WinNT.HANDLE paramHANDLE, int paramInt1, Structure paramStructure, int paramInt2, IntByReference paramIntByReference);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\NtDll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */