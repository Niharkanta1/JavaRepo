/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.platform.win32.COM.COMUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class OleAutoUtil
/*    */ {
/*    */   public static OaIdl.SAFEARRAY.ByReference createVarArray(int size) {
/* 38 */     OaIdl.SAFEARRAYBOUND[] arrayOfSAFEARRAYBOUND = new OaIdl.SAFEARRAYBOUND[1];
/* 39 */     arrayOfSAFEARRAYBOUND[0] = new OaIdl.SAFEARRAYBOUND(size, false);
/*    */     
/* 41 */     return OleAuto.INSTANCE.SafeArrayCreate(new WTypes.VARTYPE(12), 1, arrayOfSAFEARRAYBOUND);
/*    */   }
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
/*    */   public static void SafeArrayPutElement(OaIdl.SAFEARRAY array, long index, Variant.VARIANT arg) {
/* 56 */     long[] idx = new long[1];
/* 57 */     idx[0] = index;
/* 58 */     WinNT.HRESULT hr = OleAuto.INSTANCE.SafeArrayPutElement(array, idx, arg);
/* 59 */     COMUtils.SUCCEEDED(hr);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Variant.VARIANT SafeArrayGetElement(OaIdl.SAFEARRAY array, long index) {
/* 70 */     long[] idx = new long[1];
/* 71 */     idx[0] = index;
/* 72 */     Variant.VARIANT result = new Variant.VARIANT();
/* 73 */     WinNT.HRESULT hr = OleAuto.INSTANCE.SafeArrayGetElement(array, idx, result
/* 74 */         .getPointer());
/* 75 */     COMUtils.SUCCEEDED(hr);
/* 76 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\OleAutoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */