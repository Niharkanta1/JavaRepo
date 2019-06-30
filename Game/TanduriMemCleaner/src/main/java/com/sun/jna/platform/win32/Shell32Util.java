/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Shell32Util
/*     */ {
/*     */   public static String getFolderPath(WinDef.HWND hwnd, int nFolder, WinDef.DWORD dwFlags) {
/*  43 */     char[] pszPath = new char[260];
/*  44 */     WinNT.HRESULT hr = Shell32.INSTANCE.SHGetFolderPath(hwnd, nFolder, null, dwFlags, pszPath);
/*     */ 
/*     */     
/*  47 */     if (!hr.equals(W32Errors.S_OK)) {
/*  48 */       throw new Win32Exception(hr);
/*     */     }
/*  50 */     return Native.toString(pszPath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static String getFolderPath(int nFolder) { return getFolderPath(null, nFolder, ShlObj.SHGFP_TYPE_CURRENT); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getKnownFolderPath(Guid.GUID guid) throws Win32Exception {
/*  76 */     int flags = ShlObj.KNOWN_FOLDER_FLAG.NONE.getFlag();
/*  77 */     PointerByReference outPath = new PointerByReference();
/*  78 */     WinNT.HANDLE token = null;
/*  79 */     WinNT.HRESULT hr = Shell32.INSTANCE.SHGetKnownFolderPath(guid, flags, token, outPath);
/*     */     
/*  81 */     if (!W32Errors.SUCCEEDED(hr.intValue()))
/*     */     {
/*  83 */       throw new Win32Exception(hr);
/*     */     }
/*     */     
/*  86 */     String result = outPath.getValue().getWideString(0L);
/*  87 */     Ole32.INSTANCE.CoTaskMemFree(outPath.getValue());
/*     */     
/*  89 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String getSpecialFolderPath(int csidl, boolean create) {
/* 103 */     char[] pszPath = new char[260];
/* 104 */     if (!Shell32.INSTANCE.SHGetSpecialFolderPath(null, pszPath, csidl, create))
/* 105 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError()); 
/* 106 */     return Native.toString(pszPath);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Shell32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */