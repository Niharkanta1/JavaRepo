/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.ITypeInfo;
/*     */ import com.sun.jna.platform.win32.COM.TypeInfoUtil;
/*     */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TlbCoClass
/*     */   extends TlbBase
/*     */ {
/*     */   public TlbCoClass(int index, String packagename, TypeLibUtil typeLibUtil, String bindingMode) {
/*  43 */     super(index, typeLibUtil, null);
/*     */     
/*  45 */     TypeInfoUtil typeInfoUtil = typeLibUtil.getTypeInfoUtil(index);
/*     */     
/*  47 */     TypeLibUtil.TypeLibDoc typeLibDoc = this.typeLibUtil.getDocumentation(index);
/*  48 */     String docString = typeLibDoc.getDocString();
/*     */     
/*  50 */     if (typeLibDoc.getName().length() > 0) {
/*  51 */       this.name = typeLibDoc.getName();
/*     */     }
/*  53 */     logInfo("Type of kind 'CoClass' found: " + this.name);
/*     */     
/*  55 */     createPackageName(packagename);
/*  56 */     createClassName(this.name);
/*  57 */     setFilename(this.name);
/*     */     
/*  59 */     String guidStr = (this.typeLibUtil.getLibAttr()).guid.toGuidString();
/*  60 */     int majorVerNum = (this.typeLibUtil.getLibAttr()).wMajorVerNum.intValue();
/*  61 */     int minorVerNum = (this.typeLibUtil.getLibAttr()).wMinorVerNum.intValue();
/*  62 */     String version = majorVerNum + "." + minorVerNum;
/*  63 */     String clsid = (typeInfoUtil.getTypeAttr()).guid.toGuidString();
/*     */     
/*  65 */     createJavaDocHeader(guidStr, version, docString);
/*  66 */     createCLSID(clsid);
/*  67 */     createCLSIDName(this.name);
/*     */ 
/*     */     
/*  70 */     OaIdl.TYPEATTR typeAttr = typeInfoUtil.getTypeAttr();
/*  71 */     int cImplTypes = typeAttr.cImplTypes.intValue();
/*  72 */     String interfaces = "";
/*     */     
/*  74 */     for (int i = 0; i < cImplTypes; i++) {
/*  75 */       OaIdl.HREFTYPE refTypeOfImplType = typeInfoUtil.getRefTypeOfImplType(i);
/*     */       
/*  77 */       ITypeInfo refTypeInfo = typeInfoUtil.getRefTypeInfo(refTypeOfImplType);
/*  78 */       TypeInfoUtil refTypeInfoUtil = new TypeInfoUtil(refTypeInfo);
/*  79 */       createFunctions(refTypeInfoUtil, bindingMode);
/*     */       
/*  81 */       TypeInfoUtil.TypeInfoDoc documentation = refTypeInfoUtil.getDocumentation(new OaIdl.MEMBERID(-1));
/*  82 */       interfaces = interfaces + documentation.getName();
/*     */       
/*  84 */       if (i < cImplTypes - 1) {
/*  85 */         interfaces = interfaces + ", ";
/*     */       }
/*     */     } 
/*  88 */     createInterfaces(interfaces);
/*  89 */     createContent(this.content);
/*     */   }
/*     */   
/*     */   protected void createFunctions(TypeInfoUtil typeInfoUtil, String bindingMode) {
/*  93 */     OaIdl.TYPEATTR typeAttr = typeInfoUtil.getTypeAttr();
/*  94 */     int cFuncs = typeAttr.cFuncs.intValue();
/*  95 */     for (int i = 0; i < cFuncs; i++) {
/*     */       
/*  97 */       OaIdl.FUNCDESC funcDesc = typeInfoUtil.getFuncDesc(i);
/*     */       
/*  99 */       TlbAbstractMethod method = null;
/* 100 */       if (funcDesc.invkind.equals(OaIdl.INVOKEKIND.INVOKE_FUNC)) {
/* 101 */         if (isVTableMode())
/* 102 */         { method = new TlbFunctionVTable(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil); }
/*     */         else
/* 104 */         { method = new TlbFunctionDispId(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil); } 
/* 105 */       } else if (funcDesc.invkind.equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYGET)) {
/* 106 */         method = new TlbPropertyGet(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil);
/*     */       }
/* 108 */       else if (funcDesc.invkind.equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYPUT)) {
/* 109 */         method = new TlbPropertyPut(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil);
/*     */       }
/* 111 */       else if (funcDesc.invkind
/* 112 */         .equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYPUTREF)) {
/* 113 */         method = new TlbPropertyPut(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil);
/*     */       } 
/*     */ 
/*     */       
/* 117 */       if (!isReservedMethod(method.getMethodName())) {
/*     */         
/* 119 */         this.content += method.getClassBuffer();
/*     */         
/* 121 */         if (i < cFuncs - 1) {
/* 122 */           this.content += "\n";
/*     */         }
/*     */       } 
/*     */       
/* 126 */       typeInfoUtil.ReleaseFuncDesc(funcDesc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void createJavaDocHeader(String guid, String version, String helpstring) {
/* 132 */     replaceVariable("uuid", guid);
/* 133 */     replaceVariable("version", version);
/* 134 */     replaceVariable("helpstring", helpstring);
/*     */   }
/*     */ 
/*     */   
/* 138 */   protected void createCLSIDName(String clsidName) { replaceVariable("clsidname", clsidName.toUpperCase()); }
/*     */ 
/*     */ 
/*     */   
/* 142 */   protected void createCLSID(String clsid) { replaceVariable("clsid", clsid); }
/*     */ 
/*     */ 
/*     */   
/* 146 */   protected void createInterfaces(String interfaces) { replaceVariable("interfaces", interfaces); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbCoClass.template"; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbCoClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */