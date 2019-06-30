/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Secur32
/*    */   extends StdCallLibrary
/*    */ {
/* 33 */   public static final Secur32 INSTANCE = (Secur32)Native.loadLibrary("Secur32", Secur32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   
/*    */   boolean GetUserNameEx(int paramInt, char[] paramArrayOfChar, IntByReference paramIntByReference);
/*    */   
/*    */   int AcquireCredentialsHandle(String paramString1, String paramString2, int paramInt, WinNT.LUID paramLUID, Pointer paramPointer1, Pointer paramPointer2, Pointer paramPointer3, Sspi.CredHandle paramCredHandle, Sspi.TimeStamp paramTimeStamp);
/*    */   
/*    */   int InitializeSecurityContext(Sspi.CredHandle paramCredHandle, Sspi.CtxtHandle paramCtxtHandle1, String paramString, int paramInt1, int paramInt2, int paramInt3, Sspi.SecBufferDesc paramSecBufferDesc1, int paramInt4, Sspi.CtxtHandle paramCtxtHandle2, Sspi.SecBufferDesc paramSecBufferDesc2, IntByReference paramIntByReference, Sspi.TimeStamp paramTimeStamp);
/*    */   
/*    */   int DeleteSecurityContext(Sspi.CtxtHandle paramCtxtHandle);
/*    */   
/*    */   int FreeCredentialsHandle(Sspi.CredHandle paramCredHandle);
/*    */   
/*    */   int AcceptSecurityContext(Sspi.CredHandle paramCredHandle, Sspi.CtxtHandle paramCtxtHandle1, Sspi.SecBufferDesc paramSecBufferDesc1, int paramInt1, int paramInt2, Sspi.CtxtHandle paramCtxtHandle2, Sspi.SecBufferDesc paramSecBufferDesc2, IntByReference paramIntByReference, Sspi.TimeStamp paramTimeStamp);
/*    */   
/*    */   int EnumerateSecurityPackages(IntByReference paramIntByReference, Sspi.PSecPkgInfo paramPSecPkgInfo);
/*    */   
/*    */   int FreeContextBuffer(Pointer paramPointer);
/*    */   
/*    */   int QuerySecurityContextToken(Sspi.CtxtHandle paramCtxtHandle, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   int ImpersonateSecurityContext(Sspi.CtxtHandle paramCtxtHandle);
/*    */   
/*    */   int RevertSecurityContext(Sspi.CtxtHandle paramCtxtHandle);
/*    */   
/*    */   public static abstract class EXTENDED_NAME_FORMAT {
/*    */     public static final int NameUnknown = 0;
/*    */     public static final int NameFullyQualifiedDN = 1;
/*    */     public static final int NameSamCompatible = 2;
/*    */     public static final int NameDisplay = 3;
/*    */     public static final int NameUniqueId = 6;
/*    */     public static final int NameCanonical = 7;
/*    */     public static final int NameUserPrincipal = 8;
/*    */     public static final int NameCanonicalEx = 9;
/*    */     public static final int NameServicePrincipal = 10;
/*    */     public static final int NameDnsDomain = 12;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Secur32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */