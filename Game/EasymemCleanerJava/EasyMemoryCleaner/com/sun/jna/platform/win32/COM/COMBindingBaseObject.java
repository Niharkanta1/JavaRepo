/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.Kernel32;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.Ole32;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.Variant.VARIANT;
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
/*     */ public class COMBindingBaseObject
/*     */   extends COMInvoker
/*     */ {
/*  46 */   public static final WinDef.LCID LOCALE_USER_DEFAULT = Kernel32.INSTANCE
/*  47 */     .GetUserDefaultLCID();
/*     */ 
/*     */   
/*  50 */   public static final WinDef.LCID LOCALE_SYSTEM_DEFAULT = Kernel32.INSTANCE
/*  51 */     .GetSystemDefaultLCID();
/*     */   
/*     */   private IUnknown iUnknown;
/*     */   
/*     */   private IDispatch iDispatch;
/*     */   private PointerByReference pDispatch;
/*     */   private PointerByReference pUnknown;
/*     */   
/*     */   public COMBindingBaseObject(IDispatch dispatch) {
/*  60 */     this.pDispatch = new PointerByReference();
/*     */ 
/*     */     
/*  63 */     this.pUnknown = new PointerByReference();
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.iDispatch = dispatch;
/*     */   }
/*     */ 
/*     */   
/*  71 */   public COMBindingBaseObject(Guid.CLSID clsid, boolean useActiveInstance) { this(clsid, useActiveInstance, 21); }
/*     */ 
/*     */   
/*     */   public COMBindingBaseObject(Guid.CLSID clsid, boolean useActiveInstance, int dwClsContext) {
/*     */     this.pDispatch = new PointerByReference();
/*     */     this.pUnknown = new PointerByReference();
/*  77 */     WinNT.HRESULT hr = Ole32.INSTANCE.CoInitializeEx(null, 2);
/*     */     
/*  79 */     if (COMUtils.FAILED(hr)) {
/*  80 */       Ole32.INSTANCE.CoUninitialize();
/*  81 */       throw new COMException("CoInitialize() failed!");
/*     */     } 
/*     */     
/*  84 */     if (useActiveInstance) {
/*  85 */       hr = OleAuto.INSTANCE.GetActiveObject(clsid, null, this.pUnknown);
/*     */       
/*  87 */       if (COMUtils.SUCCEEDED(hr)) {
/*  88 */         this.iUnknown = new Unknown(this.pUnknown.getValue());
/*  89 */         hr = this.iUnknown.QueryInterface(new Guid.GUID.ByValue(IDispatch.IID_IDISPATCH), this.pDispatch);
/*     */       } else {
/*     */         
/*  92 */         hr = Ole32.INSTANCE.CoCreateInstance(clsid, null, dwClsContext, IDispatch.IID_IDISPATCH, this.pDispatch);
/*     */       } 
/*     */     } else {
/*     */       
/*  96 */       hr = Ole32.INSTANCE.CoCreateInstance(clsid, null, dwClsContext, IDispatch.IID_IDISPATCH, this.pDispatch);
/*     */     } 
/*     */ 
/*     */     
/* 100 */     if (COMUtils.FAILED(hr)) {
/* 101 */       throw new COMException("COM object with CLSID " + clsid
/* 102 */           .toGuidString() + " not registered properly!");
/*     */     }
/*     */     
/* 105 */     this.iDispatch = new Dispatch(this.pDispatch.getValue());
/*     */   }
/*     */   
/*     */   public COMBindingBaseObject(String progId, boolean useActiveInstance, int dwClsContext) throws COMException {
/*     */     this.pDispatch = new PointerByReference();
/*     */     this.pUnknown = new PointerByReference();
/* 111 */     WinNT.HRESULT hr = Ole32.INSTANCE.CoInitializeEx(null, 2);
/*     */     
/* 113 */     if (COMUtils.FAILED(hr)) {
/* 114 */       release();
/* 115 */       throw new COMException("CoInitialize() failed!");
/*     */     } 
/*     */ 
/*     */     
/* 119 */     Guid.CLSID.ByReference clsid = new Guid.CLSID.ByReference();
/* 120 */     hr = Ole32.INSTANCE.CLSIDFromProgID(progId, clsid);
/*     */     
/* 122 */     if (COMUtils.FAILED(hr)) {
/* 123 */       Ole32.INSTANCE.CoUninitialize();
/* 124 */       throw new COMException("CLSIDFromProgID() failed!");
/*     */     } 
/*     */     
/* 127 */     if (useActiveInstance) {
/* 128 */       hr = OleAuto.INSTANCE.GetActiveObject(clsid, null, this.pUnknown);
/*     */       
/* 130 */       if (COMUtils.SUCCEEDED(hr)) {
/* 131 */         this.iUnknown = new Unknown(this.pUnknown.getValue());
/* 132 */         hr = this.iUnknown.QueryInterface(new Guid.GUID.ByValue(IDispatch.IID_IDISPATCH), this.pDispatch);
/*     */       } else {
/*     */         
/* 135 */         hr = Ole32.INSTANCE.CoCreateInstance(clsid, null, dwClsContext, IDispatch.IID_IDISPATCH, this.pDispatch);
/*     */       } 
/*     */     } else {
/*     */       
/* 139 */       hr = Ole32.INSTANCE.CoCreateInstance(clsid, null, dwClsContext, IDispatch.IID_IDISPATCH, this.pDispatch);
/*     */     } 
/*     */ 
/*     */     
/* 143 */     if (COMUtils.FAILED(hr)) {
/* 144 */       throw new COMException("COM object with ProgID '" + progId + "' and CLSID " + clsid
/* 145 */           .toGuidString() + " not registered properly!");
/*     */     }
/*     */ 
/*     */     
/* 149 */     this.iDispatch = new Dispatch(this.pDispatch.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 154 */   public COMBindingBaseObject(String progId, boolean useActiveInstance) throws COMException { this(progId, useActiveInstance, 21); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public IDispatch getIDispatch() { return this.iDispatch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public PointerByReference getIDispatchPointer() { return this.pDispatch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public IUnknown getIUnknown() { return this.iUnknown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public PointerByReference getIUnknownPointer() { return this.pUnknown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void release() {
/* 197 */     if (this.iDispatch != null) {
/* 198 */       this.iDispatch.Release();
/*     */     }
/* 200 */     Ole32.INSTANCE.CoUninitialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, String name, VARIANT[] pArgs) throws COMException {
/* 206 */     if (pDisp == null) {
/* 207 */       throw new COMException("pDisp (IDispatch) parameter is null!");
/*     */     }
/*     */     
/* 210 */     WString[] ptName = { new WString(name) };
/* 211 */     OaIdl.DISPIDByReference pdispID = new OaIdl.DISPIDByReference();
/*     */ 
/*     */     
/* 214 */     WinNT.HRESULT hr = pDisp.GetIDsOfNames(new Guid.GUID.ByValue(Guid.IID_NULL), ptName, 1, LOCALE_USER_DEFAULT, pdispID);
/*     */ 
/*     */     
/* 217 */     COMUtils.checkRC(hr);
/*     */ 
/*     */     
/* 220 */     return oleMethod(nType, pvResult, pDisp, pdispID.getValue(), pArgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, OaIdl.DISPID dispId, VARIANT[] pArgs) throws COMException {
/* 227 */     if (pDisp == null) {
/* 228 */       throw new COMException("pDisp (IDispatch) parameter is null!");
/*     */     }
/*     */     
/* 231 */     int _argsLen = 0;
/* 232 */     Variant.VARIANT[] arrayOfVARIANT = null;
/* 233 */     OleAuto.DISPPARAMS.ByReference dp = new OleAuto.DISPPARAMS.ByReference();
/* 234 */     OaIdl.EXCEPINFO.ByReference pExcepInfo = new OaIdl.EXCEPINFO.ByReference();
/* 235 */     IntByReference puArgErr = new IntByReference();
/*     */ 
/*     */     
/* 238 */     if (pArgs != null && pArgs.length > 0) {
/* 239 */       _argsLen = pArgs.length;
/* 240 */       arrayOfVARIANT = new Variant.VARIANT[_argsLen];
/*     */       
/* 242 */       int revCount = _argsLen;
/* 243 */       for (int i = 0; i < _argsLen; i++) {
/* 244 */         arrayOfVARIANT[i] = pArgs[--revCount];
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 249 */     if (nType == 4) {
/* 250 */       dp.cNamedArgs = new WinDef.UINT(_argsLen);
/* 251 */       dp.rgdispidNamedArgs = new OaIdl.DISPIDByReference(OaIdl.DISPID_PROPERTYPUT);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 256 */     if (_argsLen > 0) {
/* 257 */       dp.cArgs = new WinDef.UINT(arrayOfVARIANT.length);
/*     */       
/* 259 */       dp.rgvarg = new Variant.VariantArg.ByReference(arrayOfVARIANT);
/*     */ 
/*     */       
/* 262 */       dp.write();
/*     */     } 
/*     */ 
/*     */     
/* 266 */     WinNT.HRESULT hr = pDisp.Invoke(dispId, new Guid.GUID.ByValue(Guid.IID_NULL), LOCALE_SYSTEM_DEFAULT, new WinDef.WORD(nType), dp, pvResult, pExcepInfo, puArgErr);
/*     */ 
/*     */     
/* 269 */     COMUtils.checkRC(hr, pExcepInfo, puArgErr);
/* 270 */     return hr;
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
/* 293 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, String name, Variant.VARIANT pArg) throws COMException { return oleMethod(nType, pvResult, pDisp, name, new Variant.VARIANT[] { pArg }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, OaIdl.DISPID dispId, Variant.VARIANT pArg) throws COMException { return oleMethod(nType, pvResult, pDisp, dispId, new Variant.VARIANT[] { pArg }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, String name) throws COMException { return oleMethod(nType, pvResult, pDisp, name, (VARIANT[])null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, OaIdl.DISPID dispId) throws COMException { return oleMethod(nType, pvResult, pDisp, dispId, (VARIANT[])null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   protected void checkFailed(WinNT.HRESULT hr) { COMUtils.checkRC(hr, null, null); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMBindingBaseObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */