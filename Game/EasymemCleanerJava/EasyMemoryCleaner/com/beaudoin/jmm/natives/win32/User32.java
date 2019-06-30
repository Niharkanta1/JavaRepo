/*    */ package com.beaudoin.jmm.natives.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import com.sun.jna.platform.win32.WinUser;
/*    */ 
/*    */ public final class User32
/*    */ {
/*    */   static  {
/* 10 */     Native.register("user32");
/*    */   }
/*    */   
/*    */   public static native int GetMessageW(WinUser.MSG paramMSG, WinDef.HWND paramHWND, int paramInt1, int paramInt2);
/*    */   
/*    */   public static native short GetKeyState(int paramInt);
/*    */   
/*    */   public static native WinDef.HWND GetForegroundWindow();
/*    */   
/*    */   public static native int GetWindowTextW(WinDef.HWND paramHWND, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   public static native boolean UnhookWindowsHookEx(WinUser.HHOOK paramHHOOK);
/*    */   
/*    */   public static native WinUser.HHOOK SetWindowsHookExW(int paramInt1, WinUser.HOOKPROC paramHOOKPROC, WinDef.HINSTANCE paramHINSTANCE, int paramInt2);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\natives\win32\User32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */