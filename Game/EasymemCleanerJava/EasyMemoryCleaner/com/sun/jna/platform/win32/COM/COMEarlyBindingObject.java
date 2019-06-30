/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.WString;
/*    */ import com.sun.jna.platform.win32.Guid;
/*    */ import com.sun.jna.platform.win32.OaIdl;
/*    */ import com.sun.jna.platform.win32.OleAuto;
/*    */ import com.sun.jna.platform.win32.Variant;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import com.sun.jna.ptr.PointerByReference;
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
/*    */ public class COMEarlyBindingObject
/*    */   extends COMBindingBaseObject
/*    */   implements IDispatch
/*    */ {
/* 45 */   public COMEarlyBindingObject(Guid.CLSID clsid, boolean useActiveInstance, int dwClsContext) { super(clsid, useActiveInstance, dwClsContext); }
/*    */ 
/*    */   
/*    */   protected String getStringProperty(OaIdl.DISPID dispId) {
/* 49 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 50 */     oleMethod(2, result, 
/* 51 */         getIDispatch(), dispId);
/*    */     
/* 53 */     return result.getValue().toString();
/*    */   }
/*    */ 
/*    */   
/* 57 */   protected void setProperty(OaIdl.DISPID dispId, boolean value) { oleMethod(4, null, getIDispatch(), dispId, new Variant.VARIANT(value)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public WinNT.HRESULT QueryInterface(Guid.GUID.ByValue riid, PointerByReference ppvObject) { return getIDispatch().QueryInterface(riid, ppvObject); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public int AddRef() { return getIDispatch().AddRef(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public int Release() { return getIDispatch().Release(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference pctinfo) { return getIDispatch().GetTypeInfoCount(pctinfo); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public WinNT.HRESULT GetTypeInfo(WinDef.UINT iTInfo, WinDef.LCID lcid, PointerByReference ppTInfo) { return getIDispatch().GetTypeInfo(iTInfo, lcid, ppTInfo); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   public WinNT.HRESULT GetIDsOfNames(Guid.GUID.ByValue riid, WString[] rgszNames, int cNames, WinDef.LCID lcid, OaIdl.DISPIDByReference rgDispId) { return getIDispatch().GetIDsOfNames(riid, rgszNames, cNames, lcid, rgDispId); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 99 */   public WinNT.HRESULT Invoke(OaIdl.DISPID dispIdMember, Guid.GUID.ByValue riid, WinDef.LCID lcid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams, Variant.VARIANT.ByReference pVarResult, OaIdl.EXCEPINFO.ByReference pExcepInfo, IntByReference puArgErr) { return getIDispatch().Invoke(dispIdMember, riid, lcid, wFlags, pDispParams, pVarResult, pExcepInfo, puArgErr); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMEarlyBindingObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */