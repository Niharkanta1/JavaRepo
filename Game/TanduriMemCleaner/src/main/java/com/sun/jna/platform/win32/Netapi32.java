/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public interface Netapi32
/*    */   extends StdCallLibrary
/*    */ {
/* 32 */   public static final Netapi32 INSTANCE = (Netapi32)Native.loadLibrary("Netapi32", Netapi32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   
/*    */   int NetGetJoinInformation(String paramString, PointerByReference paramPointerByReference, IntByReference paramIntByReference);
/*    */   
/*    */   int NetApiBufferFree(Pointer paramPointer);
/*    */   
/*    */   int NetLocalGroupEnum(String paramString, int paramInt1, PointerByReference paramPointerByReference, int paramInt2, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   int NetGetDCName(String paramString1, String paramString2, PointerByReference paramPointerByReference);
/*    */   
/*    */   int NetGroupEnum(String paramString, int paramInt1, PointerByReference paramPointerByReference, int paramInt2, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   int NetUserEnum(String paramString, int paramInt1, int paramInt2, PointerByReference paramPointerByReference, int paramInt3, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   int NetUserGetGroups(String paramString1, String paramString2, int paramInt1, PointerByReference paramPointerByReference, int paramInt2, IntByReference paramIntByReference1, IntByReference paramIntByReference2);
/*    */   
/*    */   int NetUserGetLocalGroups(String paramString1, String paramString2, int paramInt1, int paramInt2, PointerByReference paramPointerByReference, int paramInt3, IntByReference paramIntByReference1, IntByReference paramIntByReference2);
/*    */   
/*    */   int NetUserAdd(String paramString, int paramInt, Structure paramStructure, IntByReference paramIntByReference);
/*    */   
/*    */   int NetUserDel(String paramString1, String paramString2);
/*    */   
/*    */   int NetUserChangePassword(String paramString1, String paramString2, String paramString3, String paramString4);
/*    */   
/*    */   int DsGetDcName(String paramString1, String paramString2, Guid.GUID paramGUID, String paramString3, int paramInt, DsGetDC.PDOMAIN_CONTROLLER_INFO paramPDOMAIN_CONTROLLER_INFO);
/*    */   
/*    */   int DsGetForestTrustInformation(String paramString1, String paramString2, int paramInt, NTSecApi.PLSA_FOREST_TRUST_INFORMATION paramPLSA_FOREST_TRUST_INFORMATION);
/*    */   
/*    */   int DsEnumerateDomainTrusts(String paramString, int paramInt, PointerByReference paramPointerByReference, IntByReference paramIntByReference);
/*    */   
/*    */   int NetUserGetInfo(String paramString1, String paramString2, int paramInt, PointerByReference paramPointerByReference);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Netapi32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */