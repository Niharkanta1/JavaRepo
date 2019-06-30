/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.WTypes;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
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
/*     */ public class RecordInfo
/*     */   extends Unknown
/*     */   implements IRecordInfo
/*     */ {
/*     */   public static class ByReference
/*     */     extends RecordInfo
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public RecordInfo() {}
/*     */   
/*  51 */   public RecordInfo(Pointer pvInstance) { super(pvInstance); }
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
/*  62 */   public WinNT.HRESULT RecordInit(WinDef.PVOID pvNew) { return null; }
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
/*  74 */   public WinNT.HRESULT RecordClear(WinDef.PVOID pvExisting) { return null; }
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
/*  89 */   public WinNT.HRESULT RecordCopy(WinDef.PVOID pvExisting, WinDef.PVOID pvNew) { return null; }
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
/* 101 */   public WinNT.HRESULT GetGuid(Guid.GUID pguid) { return null; }
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
/* 113 */   public WinNT.HRESULT GetName(WTypes.BSTR pbstrName) { return null; }
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
/* 125 */   public WinNT.HRESULT GetSize(WinDef.ULONG pcbSize) { return null; }
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
/* 137 */   public WinNT.HRESULT GetTypeInfo(ITypeInfo ppTypeInfo) { return null; }
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
/* 155 */   public WinNT.HRESULT GetField(WinDef.PVOID pvData, WString szFieldName, Variant.VARIANT pvarField) { return null; }
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
/* 176 */   public WinNT.HRESULT GetFieldNoCopy(WinDef.PVOID pvData, WString szFieldName, Variant.VARIANT pvarField, WinDef.PVOID ppvDataCArray) { return null; }
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
/* 197 */   public WinNT.HRESULT PutField(WinDef.ULONG wFlags, WinDef.PVOID pvData, WString szFieldName, Variant.VARIANT pvarField) { return null; }
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
/* 218 */   public WinNT.HRESULT PutFieldNoCopy(WinDef.ULONG wFlags, WinDef.PVOID pvData, WString szFieldName, Variant.VARIANT pvarField) { return null; }
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
/* 233 */   public WinNT.HRESULT GetFieldNames(WinDef.ULONG pcNames, WTypes.BSTR rgBstrNames) { return null; }
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
/* 245 */   public WinDef.BOOL IsMatchingType(IRecordInfo pRecordInfo) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public WinDef.PVOID RecordCreate() { return null; }
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
/* 269 */   public WinNT.HRESULT RecordCreateCopy(WinDef.PVOID pvSource, WinDef.PVOID ppvDest) { return null; }
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
/* 281 */   public WinNT.HRESULT RecordDestroy(WinDef.PVOID pvRecord) { return null; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\RecordInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */