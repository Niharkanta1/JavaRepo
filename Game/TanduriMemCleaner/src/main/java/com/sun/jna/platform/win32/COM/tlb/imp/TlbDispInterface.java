/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TlbDispInterface
/*     */   extends TlbBase
/*     */ {
/*     */   public TlbDispInterface(int index, String packagename, TypeLibUtil typeLibUtil) {
/*  45 */     super(index, typeLibUtil, null);
/*     */     
/*  47 */     TypeLibUtil.TypeLibDoc typeLibDoc = this.typeLibUtil.getDocumentation(index);
/*  48 */     String docString = typeLibDoc.getDocString();
/*     */     
/*  50 */     if (typeLibDoc.getName().length() > 0) {
/*  51 */       this.name = typeLibDoc.getName();
/*     */     }
/*  53 */     logInfo("Type of kind 'DispInterface' found: " + this.name);
/*     */     
/*  55 */     createPackageName(packagename);
/*  56 */     createClassName(this.name);
/*  57 */     setFilename(this.name);
/*     */ 
/*     */     
/*  60 */     TypeInfoUtil typeInfoUtil = typeLibUtil.getTypeInfoUtil(index);
/*  61 */     OaIdl.TYPEATTR typeAttr = typeInfoUtil.getTypeAttr();
/*     */     
/*  63 */     createJavaDocHeader(typeAttr.guid.toGuidString(), docString);
/*     */     
/*  65 */     int cFuncs = typeAttr.cFuncs.intValue();
/*  66 */     for (int i = 0; i < cFuncs; i++) {
/*     */       
/*  68 */       OaIdl.FUNCDESC funcDesc = typeInfoUtil.getFuncDesc(i);
/*     */ 
/*     */       
/*  71 */       OaIdl.MEMBERID memberID = funcDesc.memid;
/*     */ 
/*     */       
/*  74 */       TypeInfoUtil.TypeInfoDoc typeInfoDoc2 = typeInfoUtil.getDocumentation(memberID);
/*  75 */       String methodName = typeInfoDoc2.getName();
/*  76 */       TlbAbstractMethod method = null;
/*     */       
/*  78 */       if (!isReservedMethod(methodName)) {
/*  79 */         if (funcDesc.invkind.equals(OaIdl.INVOKEKIND.INVOKE_FUNC)) {
/*  80 */           method = new TlbFunctionStub(index, typeLibUtil, funcDesc, typeInfoUtil);
/*     */         }
/*  82 */         else if (funcDesc.invkind
/*  83 */           .equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYGET)) {
/*  84 */           method = new TlbPropertyGetStub(index, typeLibUtil, funcDesc, typeInfoUtil);
/*     */         }
/*  86 */         else if (funcDesc.invkind
/*  87 */           .equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYPUT)) {
/*  88 */           method = new TlbPropertyPutStub(index, typeLibUtil, funcDesc, typeInfoUtil);
/*     */         }
/*  90 */         else if (funcDesc.invkind
/*  91 */           .equals(OaIdl.INVOKEKIND.INVOKE_PROPERTYPUTREF)) {
/*  92 */           method = new TlbPropertyPutStub(index, typeLibUtil, funcDesc, typeInfoUtil);
/*     */         } 
/*     */ 
/*     */         
/*  96 */         this.content += method.getClassBuffer();
/*     */         
/*  98 */         if (i < cFuncs - 1) {
/*  99 */           this.content += "\n";
/*     */         }
/*     */       } 
/*     */       
/* 103 */       typeInfoUtil.ReleaseFuncDesc(funcDesc);
/*     */     } 
/*     */     
/* 106 */     createContent(this.content);
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
/*     */   protected void createJavaDocHeader(String guid, String helpstring) {
/* 118 */     replaceVariable("uuid", guid);
/* 119 */     replaceVariable("helpstring", helpstring);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbDispInterface.template"; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbDispInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */