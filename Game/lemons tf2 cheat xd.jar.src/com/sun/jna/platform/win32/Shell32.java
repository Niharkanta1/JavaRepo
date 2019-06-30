/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Shell32
/*    */   extends ShellAPI, StdCallLibrary
/*    */ {
/* 32 */   public static final Shell32 INSTANCE = (Shell32)Native.loadLibrary("shell32", Shell32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   
/*    */   int SHFileOperation(ShellAPI.SHFILEOPSTRUCT paramSHFILEOPSTRUCT);
/*    */   
/*    */   WinNT.HRESULT SHGetFolderPath(WinDef.HWND paramHWND, int paramInt, WinNT.HANDLE paramHANDLE, WinDef.DWORD paramDWORD, char[] paramArrayOfChar);
/*    */   
/*    */   WinNT.HRESULT SHGetKnownFolderPath(Guid.GUID paramGUID, int paramInt, WinNT.HANDLE paramHANDLE, PointerByReference paramPointerByReference);
/*    */   
/*    */   WinNT.HRESULT SHGetDesktopFolder(PointerByReference paramPointerByReference);
/*    */   
/*    */   WinDef.INT_PTR ShellExecute(WinDef.HWND paramHWND, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt);
/*    */   
/*    */   boolean SHGetSpecialFolderPath(WinDef.HWND paramHWND, char[] paramArrayOfChar, int paramInt, boolean paramBoolean);
/*    */   
/*    */   WinDef.UINT_PTR SHAppBarMessage(WinDef.DWORD paramDWORD, ShellAPI.APPBARDATA paramAPPBARDATA);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Shell32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */