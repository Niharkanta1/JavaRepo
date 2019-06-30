/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
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
/*    */ 
/*    */ public interface Psapi
/*    */   extends StdCallLibrary
/*    */ {
/* 29 */   public static final Psapi INSTANCE = (Psapi)Native.loadLibrary("psapi", Psapi.class, W32APIOptions.DEFAULT_OPTIONS);
/*    */   
/*    */   int GetModuleFileNameExA(WinNT.HANDLE paramHANDLE1, WinNT.HANDLE paramHANDLE2, byte[] paramArrayOfByte, int paramInt);
/*    */   
/*    */   int GetModuleFileNameExW(WinNT.HANDLE paramHANDLE1, WinNT.HANDLE paramHANDLE2, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   int GetModuleFileNameEx(WinNT.HANDLE paramHANDLE1, WinNT.HANDLE paramHANDLE2, Pointer paramPointer, int paramInt);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Psapi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */