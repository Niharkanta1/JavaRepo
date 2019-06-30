/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.WString;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
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
/*    */ public final class User32Util
/*    */ {
/*    */   public static final int registerWindowMessage(String lpString) {
/* 32 */     int messageId = User32.INSTANCE.RegisterWindowMessage(lpString);
/* 33 */     if (messageId == 0)
/* 34 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError()); 
/* 35 */     return messageId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public static final WinDef.HWND createWindow(String className, String windowName, int style, int x, int y, int width, int height, WinDef.HWND parent, WinDef.HMENU menu, WinDef.HINSTANCE instance, WinDef.LPVOID param) { return createWindowEx(0, className, windowName, style, x, y, width, height, parent, menu, instance, param); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final WinDef.HWND createWindowEx(int exStyle, String className, String windowName, int style, int x, int y, int width, int height, WinDef.HWND parent, WinDef.HMENU menu, WinDef.HINSTANCE instance, WinDef.LPVOID param) {
/* 46 */     WinDef.HWND hWnd = User32.INSTANCE.CreateWindowEx(exStyle, new WString(className), windowName, style, x, y, width, height, parent, menu, instance, param);
/* 47 */     if (hWnd == null)
/* 48 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError()); 
/* 49 */     return hWnd;
/*    */   }
/*    */   
/*    */   public static final void destroyWindow(WinDef.HWND hWnd) {
/* 53 */     if (!User32.INSTANCE.DestroyWindow(hWnd))
/* 54 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError()); 
/*    */   }
/*    */   
/*    */   public static final List<WinUser.RAWINPUTDEVICELIST> GetRawInputDeviceList() {
/* 58 */     puiNumDevices = new IntByReference(false);
/* 59 */     WinUser.RAWINPUTDEVICELIST placeholder = new WinUser.RAWINPUTDEVICELIST();
/* 60 */     int cbSize = placeholder.sizeof();
/*    */     
/* 62 */     int returnValue = User32.INSTANCE.GetRawInputDeviceList(null, puiNumDevices, cbSize);
/* 63 */     if (returnValue != 0) {
/* 64 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 67 */     int deviceCount = puiNumDevices.getValue();
/* 68 */     RAWINPUTDEVICELIST[] records = (RAWINPUTDEVICELIST[])placeholder.toArray(deviceCount);
/* 69 */     returnValue = User32.INSTANCE.GetRawInputDeviceList(records, puiNumDevices, cbSize);
/* 70 */     if (returnValue == -1) {
/* 71 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 74 */     if (returnValue != records.length) {
/* 75 */       throw new IllegalStateException("Mismatched allocated (" + records.length + ") vs. received devices count (" + returnValue + ")");
/*    */     }
/*    */     
/* 78 */     return Arrays.asList(records);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\User32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */