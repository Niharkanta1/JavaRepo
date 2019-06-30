/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OaIdl.MEMBERID;
/*     */ import com.sun.jna.platform.win32.WTypes;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
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
/*     */ public class TypeLib
/*     */   extends Unknown
/*     */   implements ITypeLib
/*     */ {
/*     */   public static class ByReference
/*     */     extends TypeLib
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public TypeLib() {}
/*     */   
/*  56 */   public TypeLib(Pointer pvInstance) { super(pvInstance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinDef.UINT GetTypeInfoCount() {
/*  65 */     return (WinDef.UINT)_invokeNativeObject(3, new Object[] {
/*  66 */           getPointer() }, WinDef.UINT.class);
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
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetTypeInfo(WinDef.UINT index, PointerByReference pTInfo) {
/*  82 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] {
/*  83 */           getPointer(), index, pTInfo }, WinNT.HRESULT.class);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetTypeInfoType(WinDef.UINT index, OaIdl.TYPEKIND.ByReference pTKind) {
/* 100 */     return (WinNT.HRESULT)_invokeNativeObject(5, new Object[] {
/* 101 */           getPointer(), index, pTKind }, WinNT.HRESULT.class);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetTypeInfoOfGuid(Guid.GUID guid, PointerByReference pTinfo) {
/* 118 */     return (WinNT.HRESULT)
/* 119 */       _invokeNativeObject(6, new Object[] { getPointer(), guid, pTinfo }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetLibAttr(PointerByReference ppTLibAttr) {
/* 133 */     return (WinNT.HRESULT)_invokeNativeObject(7, new Object[] {
/* 134 */           getPointer(), ppTLibAttr }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetTypeComp(PointerByReference pTComp) {
/* 147 */     return (WinNT.HRESULT)_invokeNativeObject(8, new Object[] {
/* 148 */           getPointer(), pTComp }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetDocumentation(int index, WTypes.BSTRByReference pBstrName, WTypes.BSTRByReference pBstrDocString, WinDef.DWORDByReference pdwHelpContext, WTypes.BSTRByReference pBstrHelpFile) {
/* 173 */     return (WinNT.HRESULT)_invokeNativeObject(9, new Object[] {
/* 174 */           getPointer(), Integer.valueOf(index), pBstrName, pBstrDocString, pdwHelpContext, pBstrHelpFile }, WinNT.HRESULT.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT IsName(WTypes.LPOLESTR szNameBuf, WinDef.ULONG lHashVal, WinDef.BOOLByReference pfName) {
/* 196 */     return (WinNT.HRESULT)
/* 197 */       _invokeNativeObject(10, new Object[] { getPointer(), szNameBuf, lHashVal, pfName }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT FindName(WTypes.BSTRByReference szNameBuf, WinDef.ULONG lHashVal, ITypeInfo[] ppTInfo, MEMBERID[] rgMemId, WinDef.USHORTByReference pcFound) {
/* 224 */     return (WinNT.HRESULT)_invokeNativeObject(11, new Object[] {
/* 225 */           getPointer(), szNameBuf, lHashVal, ppTInfo, rgMemId, pcFound }, WinNT.HRESULT.class);
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
/* 236 */   public void ReleaseTLibAttr(OaIdl.TLIBATTR pTLibAttr) { _invokeNativeObject(12, new Object[] { getPointer() }, WinNT.HRESULT.class); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\TypeLib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */