/*    */ package com.beaudoin.jmm.natives.win32;
/*    */ 
/*    */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.NativeLibrary;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.platform.win32.Tlhelp32;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public final class Kernel32
/*    */ {
/*    */   public static native Pointer CreateToolhelp32Snapshot(WinDef.DWORD paramDWORD, int paramInt);
/*    */   
/*    */   public static native boolean CloseHandle(Pointer paramPointer);
/*    */   
/*    */   public static native Pointer OpenProcess(int paramInt1, boolean paramBoolean, int paramInt2);
/*    */   
/*    */   public static native boolean Process32Next(Pointer paramPointer, Tlhelp32.PROCESSENTRY32 paramPROCESSENTRY32);
/*    */   
/*    */   public static native long ReadProcessMemory(Pointer paramPointer1, Pointer paramPointer2, MemoryBuffer paramMemoryBuffer, int paramInt1, int paramInt2);
/*    */   
/*    */   public static native long WriteProcessMemory(Pointer paramPointer1, Pointer paramPointer2, MemoryBuffer paramMemoryBuffer, int paramInt1, int paramInt2);
/*    */   
/*    */   public static native WinDef.HMODULE GetModuleHandle(String paramString);
/*    */   
/*    */   public static native boolean GetExitCodeProcess(Pointer paramPointer, IntByReference paramIntByReference);
/*    */   
/*    */   static  {
/* 39 */     Native.register(NativeLibrary.getInstance("Kernel32", W32APIOptions.UNICODE_OPTIONS));
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\natives\win32\Kernel32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */