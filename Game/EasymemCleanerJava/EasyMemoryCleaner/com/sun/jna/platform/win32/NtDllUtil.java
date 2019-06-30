/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NtDllUtil
/*    */ {
/*    */   public static String getKeyName(WinReg.HKEY hkey) {
/* 32 */     IntByReference resultLength = new IntByReference();
/* 33 */     int rc = NtDll.INSTANCE.ZwQueryKey(hkey, 0, null, 0, resultLength);
/*    */     
/* 35 */     if (rc != -1073741789 || resultLength.getValue() <= 0) {
/* 36 */       throw new Win32Exception(rc);
/*    */     }
/*    */     
/* 39 */     Wdm.KEY_BASIC_INFORMATION keyInformation = new Wdm.KEY_BASIC_INFORMATION(resultLength.getValue());
/* 40 */     rc = NtDll.INSTANCE.ZwQueryKey(hkey, 0, keyInformation, resultLength
/* 41 */         .getValue(), resultLength);
/* 42 */     if (rc != 0) {
/* 43 */       throw new Win32Exception(rc);
/*    */     }
/* 45 */     return keyInformation.getName();
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\NtDllUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */