/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.Kernel32;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OaIdl.MEMBERID;
/*     */ import com.sun.jna.platform.win32.Ole32;
/*     */ import com.sun.jna.platform.win32.OleAuto;
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
/*     */ public class TypeLibUtil
/*     */ {
/*  43 */   public static final OleAuto OLEAUTO = OleAuto.INSTANCE;
/*     */ 
/*     */   
/*     */   private ITypeLib typelib;
/*     */ 
/*     */   
/*  49 */   private WinDef.LCID lcid = Kernel32.INSTANCE.GetUserDefaultLCID();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String docString;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int helpContext;
/*     */ 
/*     */ 
/*     */   
/*     */   private String helpFile;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeLibUtil(String clsidStr, int wVerMajor, int wVerMinor) {
/*  74 */     Guid.CLSID.ByReference clsid = new Guid.CLSID.ByReference();
/*     */     
/*  76 */     WinNT.HRESULT hr = Ole32.INSTANCE.CLSIDFromString(new WString(clsidStr), clsid);
/*     */     
/*  78 */     COMUtils.checkRC(hr);
/*     */ 
/*     */     
/*  81 */     PointerByReference pTypeLib = new PointerByReference();
/*  82 */     hr = OleAuto.INSTANCE.LoadRegTypeLib(clsid, wVerMajor, wVerMinor, this.lcid, pTypeLib);
/*     */     
/*  84 */     COMUtils.checkRC(hr);
/*     */ 
/*     */     
/*  87 */     this.typelib = new TypeLib(pTypeLib.getValue());
/*     */     
/*  89 */     initTypeLibInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeLibUtil(String file) {
/*  94 */     PointerByReference pTypeLib = new PointerByReference();
/*  95 */     WinNT.HRESULT hr = OleAuto.INSTANCE.LoadTypeLib(new WString(file), pTypeLib);
/*  96 */     COMUtils.checkRC(hr);
/*     */ 
/*     */     
/*  99 */     this.typelib = new TypeLib(pTypeLib.getValue());
/*     */     
/* 101 */     initTypeLibInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initTypeLibInfo() {
/* 108 */     TypeLibDoc documentation = getDocumentation(-1);
/* 109 */     this.name = documentation.getName();
/* 110 */     this.docString = documentation.getDocString();
/* 111 */     this.helpContext = documentation.getHelpContext();
/* 112 */     this.helpFile = documentation.getHelpFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int getTypeInfoCount() { return this.typelib.GetTypeInfoCount().intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.TYPEKIND getTypeInfoType(int index) {
/* 132 */     OaIdl.TYPEKIND.ByReference typekind = new OaIdl.TYPEKIND.ByReference();
/* 133 */     WinNT.HRESULT hr = this.typelib.GetTypeInfoType(new WinDef.UINT(index), typekind);
/* 134 */     COMUtils.checkRC(hr);
/* 135 */     return typekind;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ITypeInfo getTypeInfo(int index) {
/* 146 */     PointerByReference ppTInfo = new PointerByReference();
/* 147 */     WinNT.HRESULT hr = this.typelib.GetTypeInfo(new WinDef.UINT(index), ppTInfo);
/* 148 */     COMUtils.checkRC(hr);
/* 149 */     return new TypeInfo(ppTInfo.getValue());
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
/* 160 */   public TypeInfoUtil getTypeInfoUtil(int index) { return new TypeInfoUtil(getTypeInfo(index)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OaIdl.TLIBATTR getLibAttr() {
/* 169 */     PointerByReference ppTLibAttr = new PointerByReference();
/* 170 */     WinNT.HRESULT hr = this.typelib.GetLibAttr(ppTLibAttr);
/* 171 */     COMUtils.checkRC(hr);
/*     */     
/* 173 */     return new OaIdl.TLIBATTR(ppTLibAttr.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeComp GetTypeComp() {
/* 182 */     PointerByReference ppTComp = new PointerByReference();
/* 183 */     WinNT.HRESULT hr = this.typelib.GetTypeComp(ppTComp);
/* 184 */     COMUtils.checkRC(hr);
/*     */     
/* 186 */     return new TypeComp(ppTComp.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeLibDoc getDocumentation(int index) {
/* 197 */     WTypes.BSTRByReference pBstrName = new WTypes.BSTRByReference();
/* 198 */     WTypes.BSTRByReference pBstrDocString = new WTypes.BSTRByReference();
/* 199 */     WinDef.DWORDByReference pdwHelpContext = new WinDef.DWORDByReference();
/* 200 */     WTypes.BSTRByReference pBstrHelpFile = new WTypes.BSTRByReference();
/*     */     
/* 202 */     WinNT.HRESULT hr = this.typelib.GetDocumentation(index, pBstrName, pBstrDocString, pdwHelpContext, pBstrHelpFile);
/*     */     
/* 204 */     COMUtils.checkRC(hr);
/*     */ 
/*     */ 
/*     */     
/* 208 */     TypeLibDoc typeLibDoc = new TypeLibDoc(pBstrName.getString(), pBstrDocString.getString(), pdwHelpContext.getValue().intValue(), pBstrHelpFile.getString());
/*     */     
/* 210 */     OLEAUTO.SysFreeString(pBstrName.getValue());
/* 211 */     OLEAUTO.SysFreeString(pBstrDocString.getValue());
/* 212 */     OLEAUTO.SysFreeString(pBstrHelpFile.getValue());
/*     */     
/* 214 */     return typeLibDoc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TypeLibDoc
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
/*     */     public TypeLibDoc(String name, String docString, int helpContext, String helpFile) {
/* 250 */       this.name = name;
/* 251 */       this.docString = docString;
/* 252 */       this.helpContext = helpContext;
/* 253 */       this.helpFile = helpFile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     public String getDocString() { return this.docString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     public int getHelpContext() { return this.helpContext; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     public String getHelpFile() { return this.helpFile; }
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
/*     */   public IsName IsName(String nameBuf, int hashVal) {
/* 304 */     WTypes.LPOLESTR szNameBuf = new WTypes.LPOLESTR(nameBuf);
/* 305 */     WinDef.ULONG lHashVal = new WinDef.ULONG(hashVal);
/* 306 */     WinDef.BOOLByReference pfName = new WinDef.BOOLByReference();
/*     */     
/* 308 */     WinNT.HRESULT hr = this.typelib.IsName(szNameBuf, lHashVal, pfName);
/* 309 */     COMUtils.checkRC(hr);
/*     */     
/* 311 */     return new IsName(szNameBuf.getValue(), pfName.getValue()
/* 312 */         .booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class IsName
/*     */   {
/*     */     private String nameBuf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public IsName(String nameBuf, boolean name) {
/* 337 */       this.nameBuf = nameBuf;
/* 338 */       this.name = name;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     public String getNameBuf() { return this.nameBuf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 356 */     public boolean isName() { return this.name; }
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
/*     */   public FindName FindName(String name, int hashVal, short found) {
/* 374 */     WTypes.BSTRByReference szNameBuf = new WTypes.BSTRByReference(OleAuto.INSTANCE.SysAllocString(name));
/* 375 */     WinDef.ULONG lHashVal = new WinDef.ULONG(hashVal);
/* 376 */     WinDef.USHORTByReference pcFound = new WinDef.USHORTByReference(found);
/*     */     
/* 378 */     WinNT.HRESULT hr = this.typelib.FindName(szNameBuf, lHashVal, null, null, pcFound);
/*     */     
/* 380 */     COMUtils.checkRC(hr);
/*     */     
/* 382 */     found = pcFound.getValue().shortValue();
/* 383 */     ITypeInfo[] ppTInfo = new ITypeInfo[found];
/* 384 */     OaIdl.MEMBERID[] arrayOfMEMBERID = new OaIdl.MEMBERID[found];
/* 385 */     hr = this.typelib.FindName(szNameBuf, lHashVal, ppTInfo, arrayOfMEMBERID, pcFound);
/*     */     
/* 387 */     COMUtils.checkRC(hr);
/*     */     
/* 389 */     FindName findName = new FindName(szNameBuf.getString(), ppTInfo, arrayOfMEMBERID, found);
/*     */     
/* 391 */     OLEAUTO.SysFreeString(szNameBuf.getValue());
/*     */     
/* 393 */     return findName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class FindName
/*     */   {
/*     */     private String nameBuf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ITypeInfo[] pTInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private OaIdl.MEMBERID[] rgMemId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private short pcFound;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FindName(String nameBuf, ITypeInfo[] pTInfo, MEMBERID[] rgMemId, short pcFound) {
/* 429 */       this.nameBuf = nameBuf;
/* 430 */       this.pTInfo = pTInfo;
/* 431 */       this.rgMemId = rgMemId;
/* 432 */       this.pcFound = pcFound;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 441 */     public String getNameBuf() { return this.nameBuf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 450 */     public ITypeInfo[] getTInfo() { return this.pTInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 459 */     public OaIdl.MEMBERID[] getMemId() { return this.rgMemId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 468 */     public short getFound() { return this.pcFound; }
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
/* 479 */   public void ReleaseTLibAttr(OaIdl.TLIBATTR pTLibAttr) { this.typelib.ReleaseTLibAttr(pTLibAttr); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 488 */   public WinDef.LCID getLcid() { return this.lcid; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 497 */   public ITypeLib getTypelib() { return this.typelib; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 506 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 515 */   public String getDocString() { return this.docString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 524 */   public long getHelpContext() { return this.helpContext; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 533 */   public String getHelpFile() { return this.helpFile; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\TypeLibUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */