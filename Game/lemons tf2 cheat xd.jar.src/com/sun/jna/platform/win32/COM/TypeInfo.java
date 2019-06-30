/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OaIdl.MEMBERID;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.WTypes;
/*     */ import com.sun.jna.platform.win32.WTypes.BSTR;
/*     */ import com.sun.jna.platform.win32.WTypes.LPOLESTR;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.IntByReference;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TypeInfo
/*     */   extends Unknown
/*     */   implements ITypeInfo
/*     */ {
/*     */   public static class ByReference
/*     */     extends TypeInfo
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public TypeInfo() {}
/*     */   
/*  66 */   public TypeInfo(Pointer pvInstance) { super(pvInstance); }
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
/*     */   public WinNT.HRESULT GetTypeAttr(PointerByReference ppTypeAttr) {
/*  79 */     return (WinNT.HRESULT)_invokeNativeObject(3, new Object[] {
/*  80 */           getPointer(), ppTypeAttr }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetTypeComp(PointerByReference ppTComp) {
/*  93 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] {
/*  94 */           getPointer(), ppTComp }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetFuncDesc(WinDef.UINT index, PointerByReference ppFuncDesc) {
/* 110 */     return (WinNT.HRESULT)_invokeNativeObject(5, new Object[] {
/* 111 */           getPointer(), index, ppFuncDesc }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetVarDesc(WinDef.UINT index, PointerByReference ppVarDesc) {
/* 128 */     return (WinNT.HRESULT)_invokeNativeObject(6, new Object[] {
/* 129 */           getPointer(), index, ppVarDesc }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetNames(OaIdl.MEMBERID memid, BSTR[] rgBstrNames, WinDef.UINT cMaxNames, WinDef.UINTByReference pcNames) {
/* 152 */     return (WinNT.HRESULT)_invokeNativeObject(7, new Object[] {
/* 153 */           getPointer(), memid, rgBstrNames, cMaxNames, pcNames }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetRefTypeOfImplType(WinDef.UINT index, OaIdl.HREFTYPEByReference pRefType) {
/* 170 */     return (WinNT.HRESULT)_invokeNativeObject(8, new Object[] {
/* 171 */           getPointer(), index, pRefType }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetImplTypeFlags(WinDef.UINT index, IntByReference pImplTypeFlags) {
/* 188 */     return (WinNT.HRESULT)_invokeNativeObject(9, new Object[] {
/* 189 */           getPointer(), index, pImplTypeFlags }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetIDsOfNames(LPOLESTR[] rgszNames, WinDef.UINT cNames, MEMBERID[] pMemId) {
/* 209 */     return (WinNT.HRESULT)_invokeNativeObject(10, new Object[] {
/* 210 */           getPointer(), rgszNames, cNames, pMemId }, WinNT.HRESULT.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT Invoke(WinDef.PVOID pvInstance, OaIdl.MEMBERID memid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams, Variant.VARIANT.ByReference pVarResult, OaIdl.EXCEPINFO.ByReference pExcepInfo, WinDef.UINTByReference puArgErr) {
/* 242 */     return (WinNT.HRESULT)_invokeNativeObject(11, new Object[] {
/* 243 */           getPointer(), pvInstance, memid, wFlags, pDispParams, pVarResult, pExcepInfo, puArgErr }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetDocumentation(OaIdl.MEMBERID memid, WTypes.BSTRByReference pBstrName, WTypes.BSTRByReference pBstrDocString, WinDef.DWORDByReference pdwHelpContext, WTypes.BSTRByReference pBstrHelpFile) {
/* 270 */     return (WinNT.HRESULT)_invokeNativeObject(12, new Object[] {
/* 271 */           getPointer(), memid, pBstrName, pBstrDocString, pdwHelpContext, pBstrHelpFile }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetDllEntry(OaIdl.MEMBERID memid, OaIdl.INVOKEKIND invKind, WTypes.BSTRByReference pBstrDllName, WTypes.BSTRByReference pBstrName, WinDef.WORDByReference pwOrdinal) {
/* 298 */     return (WinNT.HRESULT)_invokeNativeObject(13, new Object[] {
/* 299 */           getPointer(), memid, invKind, pBstrDllName, pBstrName, pwOrdinal }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetRefTypeInfo(OaIdl.HREFTYPE hRefType, PointerByReference ppTInfo) {
/* 316 */     return (WinNT.HRESULT)_invokeNativeObject(14, new Object[] {
/* 317 */           getPointer(), hRefType, ppTInfo }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT AddressOfMember(OaIdl.MEMBERID memid, OaIdl.INVOKEKIND invKind, PointerByReference ppv) {
/* 337 */     return (WinNT.HRESULT)_invokeNativeObject(15, new Object[] {
/* 338 */           getPointer(), memid, invKind, ppv }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT CreateInstance(IUnknown pUnkOuter, Guid.REFIID riid, PointerByReference ppvObj) {
/* 358 */     return (WinNT.HRESULT)_invokeNativeObject(16, new Object[] {
/* 359 */           getPointer(), pUnkOuter, riid, ppvObj }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetMops(OaIdl.MEMBERID memid, WTypes.BSTRByReference pBstrMops) {
/* 376 */     return (WinNT.HRESULT)_invokeNativeObject(17, new Object[] {
/* 377 */           getPointer(), memid, pBstrMops }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT GetContainingTypeLib(PointerByReference ppTLib, WinDef.UINTByReference pIndex) {
/* 394 */     return (WinNT.HRESULT)_invokeNativeObject(18, new Object[] {
/* 395 */           getPointer(), ppTLib, pIndex }, WinNT.HRESULT.class);
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
/* 407 */   public void ReleaseTypeAttr(OaIdl.TYPEATTR pTypeAttr) { _invokeNativeVoid(19, new Object[] { getPointer(), pTypeAttr }); }
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
/* 418 */   public void ReleaseFuncDesc(OaIdl.FUNCDESC pFuncDesc) { _invokeNativeVoid(20, new Object[] { getPointer(), pFuncDesc }); }
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
/* 429 */   public void ReleaseVarDesc(OaIdl.VARDESC pVarDesc) { _invokeNativeVoid(21, new Object[] { getPointer(), pVarDesc }); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\TypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */