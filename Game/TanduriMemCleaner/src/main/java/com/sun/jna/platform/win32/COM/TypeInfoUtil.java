/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.WTypes;
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
/*     */ public class TypeInfoUtil
/*     */ {
/*  49 */   public static final OleAuto OLEAUTO = OleAuto.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ITypeInfo typeInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public TypeInfoUtil(ITypeInfo typeInfo) { this.typeInfo = typeInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.TYPEATTR getTypeAttr() {
/*  70 */     PointerByReference ppTypeAttr = new PointerByReference();
/*  71 */     WinNT.HRESULT hr = this.typeInfo.GetTypeAttr(ppTypeAttr);
/*  72 */     COMUtils.checkRC(hr);
/*     */     
/*  74 */     return new OaIdl.TYPEATTR(ppTypeAttr.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeComp getTypeComp() {
/*  83 */     PointerByReference ppTypeAttr = new PointerByReference();
/*  84 */     WinNT.HRESULT hr = this.typeInfo.GetTypeComp(ppTypeAttr);
/*  85 */     COMUtils.checkRC(hr);
/*     */     
/*  87 */     return new TypeComp(ppTypeAttr.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.FUNCDESC getFuncDesc(int index) {
/*  98 */     PointerByReference ppFuncDesc = new PointerByReference();
/*  99 */     WinNT.HRESULT hr = this.typeInfo.GetFuncDesc(new WinDef.UINT(index), ppFuncDesc);
/* 100 */     COMUtils.checkRC(hr);
/*     */     
/* 102 */     return new OaIdl.FUNCDESC(ppFuncDesc.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.VARDESC getVarDesc(int index) {
/* 113 */     PointerByReference ppVarDesc = new PointerByReference();
/* 114 */     WinNT.HRESULT hr = this.typeInfo.GetVarDesc(new WinDef.UINT(index), ppVarDesc);
/* 115 */     COMUtils.checkRC(hr);
/*     */     
/* 117 */     return new OaIdl.VARDESC(ppVarDesc.getValue());
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
/*     */   public String[] getNames(OaIdl.MEMBERID memid, int maxNames) {
/* 130 */     WTypes.BSTR[] arrayOfBSTR = new WTypes.BSTR[maxNames];
/* 131 */     WinDef.UINTByReference pcNames = new WinDef.UINTByReference();
/* 132 */     WinNT.HRESULT hr = this.typeInfo.GetNames(memid, arrayOfBSTR, new WinDef.UINT(maxNames), pcNames);
/*     */     
/* 134 */     COMUtils.checkRC(hr);
/*     */     
/* 136 */     int cNames = pcNames.getValue().intValue();
/* 137 */     String[] result = new String[cNames];
/*     */     
/* 139 */     for (int i = 0; i < result.length; i++) {
/* 140 */       result[i] = arrayOfBSTR[i].getValue();
/* 141 */       OLEAUTO.SysFreeString(arrayOfBSTR[i]);
/*     */     } 
/*     */     
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.HREFTYPE getRefTypeOfImplType(int index) {
/* 155 */     OaIdl.HREFTYPEByReference ppTInfo = new OaIdl.HREFTYPEByReference();
/* 156 */     WinNT.HRESULT hr = this.typeInfo.GetRefTypeOfImplType(new WinDef.UINT(index), ppTInfo);
/*     */     
/* 158 */     COMUtils.checkRC(hr);
/*     */     
/* 160 */     return ppTInfo.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImplTypeFlags(int index) {
/* 171 */     IntByReference pImplTypeFlags = new IntByReference();
/* 172 */     WinNT.HRESULT hr = this.typeInfo.GetImplTypeFlags(new WinDef.UINT(index), pImplTypeFlags);
/*     */     
/* 174 */     COMUtils.checkRC(hr);
/*     */     
/* 176 */     return pImplTypeFlags.getValue();
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
/*     */   public OaIdl.MEMBERID[] getIDsOfNames(LPOLESTR[] rgszNames, int cNames) {
/* 189 */     OaIdl.MEMBERID[] arrayOfMEMBERID = new OaIdl.MEMBERID[cNames];
/* 190 */     WinNT.HRESULT hr = this.typeInfo.GetIDsOfNames(rgszNames, new WinDef.UINT(cNames), arrayOfMEMBERID);
/*     */     
/* 192 */     COMUtils.checkRC(hr);
/*     */     
/* 194 */     return arrayOfMEMBERID;
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
/*     */   public Invoke Invoke(WinDef.PVOID pvInstance, OaIdl.MEMBERID memid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams) {
/* 213 */     Variant.VARIANT.ByReference pVarResult = new Variant.VARIANT.ByReference();
/* 214 */     OaIdl.EXCEPINFO.ByReference pExcepInfo = new OaIdl.EXCEPINFO.ByReference();
/* 215 */     WinDef.UINTByReference puArgErr = new WinDef.UINTByReference();
/*     */     
/* 217 */     WinNT.HRESULT hr = this.typeInfo.Invoke(pvInstance, memid, wFlags, pDispParams, pVarResult, pExcepInfo, puArgErr);
/*     */     
/* 219 */     COMUtils.checkRC(hr);
/*     */     
/* 221 */     return new Invoke(pVarResult, pExcepInfo, puArgErr.getValue()
/* 222 */         .intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Invoke
/*     */   {
/*     */     private Variant.VARIANT.ByReference pVarResult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private OaIdl.EXCEPINFO.ByReference pExcepInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int puArgErr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Invoke(Variant.VARIANT.ByReference pVarResult, OaIdl.EXCEPINFO.ByReference pExcepInfo, int puArgErr) {
/* 253 */       this.pVarResult = pVarResult;
/* 254 */       this.pExcepInfo = pExcepInfo;
/* 255 */       this.puArgErr = puArgErr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     public Variant.VARIANT.ByReference getpVarResult() { return this.pVarResult; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     public OaIdl.EXCEPINFO.ByReference getpExcepInfo() { return this.pExcepInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     public int getPuArgErr() { return this.puArgErr; }
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
/*     */   public TypeInfoDoc getDocumentation(OaIdl.MEMBERID memid) {
/* 294 */     WTypes.BSTRByReference pBstrName = new WTypes.BSTRByReference();
/* 295 */     WTypes.BSTRByReference pBstrDocString = new WTypes.BSTRByReference();
/* 296 */     WinDef.DWORDByReference pdwHelpContext = new WinDef.DWORDByReference();
/* 297 */     WTypes.BSTRByReference pBstrHelpFile = new WTypes.BSTRByReference();
/*     */     
/* 299 */     WinNT.HRESULT hr = this.typeInfo.GetDocumentation(memid, pBstrName, pBstrDocString, pdwHelpContext, pBstrHelpFile);
/*     */     
/* 301 */     COMUtils.checkRC(hr);
/*     */ 
/*     */ 
/*     */     
/* 305 */     TypeInfoDoc TypeInfoDoc = new TypeInfoDoc(pBstrName.getString(), pBstrDocString.getString(), pdwHelpContext.getValue().intValue(), pBstrHelpFile.getString());
/*     */     
/* 307 */     OLEAUTO.SysFreeString(pBstrName.getValue());
/* 308 */     OLEAUTO.SysFreeString(pBstrDocString.getValue());
/* 309 */     OLEAUTO.SysFreeString(pBstrHelpFile.getValue());
/*     */     
/* 311 */     return TypeInfoDoc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TypeInfoDoc
/*     */   {
/*     */     private String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String docString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int helpContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String helpFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public TypeInfoDoc(String name, String docString, int helpContext, String helpFile) {
/* 347 */       this.name = name;
/* 348 */       this.docString = docString;
/* 349 */       this.helpContext = helpContext;
/* 350 */       this.helpFile = helpFile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     public String getDocString() { return this.docString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 377 */     public int getHelpContext() { return this.helpContext; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     public String getHelpFile() { return this.helpFile; }
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
/*     */   public DllEntry GetDllEntry(OaIdl.MEMBERID memid, OaIdl.INVOKEKIND invKind) {
/* 400 */     WTypes.BSTRByReference pBstrDllName = new WTypes.BSTRByReference();
/* 401 */     WTypes.BSTRByReference pBstrName = new WTypes.BSTRByReference();
/* 402 */     WinDef.WORDByReference pwOrdinal = new WinDef.WORDByReference();
/*     */     
/* 404 */     WinNT.HRESULT hr = this.typeInfo.GetDllEntry(memid, invKind, pBstrDllName, pBstrName, pwOrdinal);
/*     */     
/* 406 */     COMUtils.checkRC(hr);
/*     */     
/* 408 */     OLEAUTO.SysFreeString(pBstrDllName.getValue());
/* 409 */     OLEAUTO.SysFreeString(pBstrName.getValue());
/*     */     
/* 411 */     return new DllEntry(pBstrDllName.getString(), pBstrName.getString(), pwOrdinal
/* 412 */         .getValue().intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DllEntry
/*     */   {
/*     */     private String dllName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int ordinal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DllEntry(String dllName, String name, int ordinal) {
/* 442 */       this.dllName = dllName;
/* 443 */       this.name = name;
/* 444 */       this.ordinal = ordinal;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 453 */     public String getDllName() { return this.dllName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 463 */     public void setDllName(String dllName) { this.dllName = dllName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 472 */     public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 482 */     public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     public int getOrdinal() { return this.ordinal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 501 */     public void setOrdinal(int ordinal) { this.ordinal = ordinal; }
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
/*     */   public ITypeInfo getRefTypeInfo(OaIdl.HREFTYPE hreftype) {
/* 513 */     PointerByReference ppTInfo = new PointerByReference();
/* 514 */     WinNT.HRESULT hr = this.typeInfo.GetRefTypeInfo(hreftype, ppTInfo);
/* 515 */     COMUtils.checkRC(hr);
/*     */     
/* 517 */     return new TypeInfo(ppTInfo.getValue());
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
/*     */   public PointerByReference AddressOfMember(OaIdl.MEMBERID memid, OaIdl.INVOKEKIND invKind) {
/* 530 */     PointerByReference ppv = new PointerByReference();
/* 531 */     WinNT.HRESULT hr = this.typeInfo.AddressOfMember(memid, invKind, ppv);
/* 532 */     COMUtils.checkRC(hr);
/*     */     
/* 534 */     return ppv;
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
/*     */   public PointerByReference CreateInstance(IUnknown pUnkOuter, Guid.REFIID riid) {
/* 547 */     PointerByReference ppvObj = new PointerByReference();
/* 548 */     WinNT.HRESULT hr = this.typeInfo.CreateInstance(pUnkOuter, riid, ppvObj);
/* 549 */     COMUtils.checkRC(hr);
/*     */     
/* 551 */     return ppvObj;
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
/*     */   public String GetMops(OaIdl.MEMBERID memid) {
/* 563 */     WTypes.BSTRByReference pBstrMops = new WTypes.BSTRByReference();
/* 564 */     WinNT.HRESULT hr = this.typeInfo.GetMops(memid, pBstrMops);
/* 565 */     COMUtils.checkRC(hr);
/*     */     
/* 567 */     return pBstrMops.getString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContainingTypeLib GetContainingTypeLib() {
/* 577 */     PointerByReference ppTLib = new PointerByReference();
/* 578 */     WinDef.UINTByReference pIndex = new WinDef.UINTByReference();
/*     */     
/* 580 */     WinNT.HRESULT hr = this.typeInfo.GetContainingTypeLib(ppTLib, pIndex);
/* 581 */     COMUtils.checkRC(hr);
/*     */     
/* 583 */     return new ContainingTypeLib(new TypeLib(ppTLib.getValue()), pIndex
/* 584 */         .getValue().intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ContainingTypeLib
/*     */   {
/*     */     private ITypeLib typeLib;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ContainingTypeLib(ITypeLib typeLib, int index) {
/* 609 */       this.typeLib = typeLib;
/* 610 */       this.index = index;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 619 */     public ITypeLib getTypeLib() { return this.typeLib; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 629 */     public void setTypeLib(ITypeLib typeLib) { this.typeLib = typeLib; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 638 */     public int getIndex() { return this.index; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 648 */     public void setIndex(int index) { this.index = index; }
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
/* 659 */   public void ReleaseTypeAttr(OaIdl.TYPEATTR pTypeAttr) { this.typeInfo.ReleaseTypeAttr(pTypeAttr); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 669 */   public void ReleaseFuncDesc(OaIdl.FUNCDESC pFuncDesc) { this.typeInfo.ReleaseFuncDesc(pFuncDesc); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 679 */   public void ReleaseVarDesc(OaIdl.VARDESC pVarDesc) { this.typeInfo.ReleaseVarDesc(pVarDesc); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\TypeInfoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */