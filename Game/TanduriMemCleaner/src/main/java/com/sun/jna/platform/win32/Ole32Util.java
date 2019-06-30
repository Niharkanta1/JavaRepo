/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
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
/*    */ 
/*    */ public abstract class Ole32Util
/*    */ {
/*    */   public static Guid.GUID getGUIDFromString(String guidString) {
/* 33 */     Guid.GUID lpiid = new Guid.GUID();
/* 34 */     WinNT.HRESULT hr = Ole32.INSTANCE.IIDFromString(guidString, lpiid);
/* 35 */     if (!hr.equals(W32Errors.S_OK)) {
/* 36 */       throw new RuntimeException(hr.toString());
/*    */     }
/* 38 */     return lpiid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getStringFromGUID(Guid.GUID guid) {
/* 49 */     Guid.GUID pguid = new Guid.GUID(guid.getPointer());
/* 50 */     int max = 39;
/* 51 */     char[] lpsz = new char[max];
/* 52 */     int len = Ole32.INSTANCE.StringFromGUID2(pguid, lpsz, max);
/* 53 */     if (len == 0) {
/* 54 */       throw new RuntimeException("StringFromGUID2");
/*    */     }
/* 56 */     lpsz[len - 1] = Character.MIN_VALUE;
/* 57 */     return Native.toString(lpsz);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Guid.GUID generateGUID() {
/* 66 */     pguid = new Guid.GUID();
/* 67 */     WinNT.HRESULT hr = Ole32.INSTANCE.CoCreateGuid(pguid);
/* 68 */     if (!hr.equals(W32Errors.S_OK)) {
/* 69 */       throw new RuntimeException(hr.toString());
/*    */     }
/* 71 */     return pguid;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Ole32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */