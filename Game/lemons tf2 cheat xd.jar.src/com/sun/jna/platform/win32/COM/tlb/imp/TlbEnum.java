/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.TypeInfoUtil;
/*     */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TlbEnum
/*     */   extends TlbBase
/*     */ {
/*     */   public TlbEnum(int index, String packagename, TypeLibUtil typeLibUtil) {
/*  41 */     super(index, typeLibUtil, null);
/*     */     
/*  43 */     TypeLibUtil.TypeLibDoc typeLibDoc = this.typeLibUtil.getDocumentation(index);
/*  44 */     String docString = typeLibDoc.getDocString();
/*     */     
/*  46 */     if (typeLibDoc.getName().length() > 0) {
/*  47 */       this.name = typeLibDoc.getName();
/*     */     }
/*  49 */     logInfo("Type of kind 'Enum' found: " + this.name);
/*     */     
/*  51 */     createPackageName(packagename);
/*  52 */     createClassName(this.name);
/*  53 */     setFilename(this.name);
/*     */ 
/*     */     
/*  56 */     TypeInfoUtil typeInfoUtil = typeLibUtil.getTypeInfoUtil(index);
/*  57 */     OaIdl.TYPEATTR typeAttr = typeInfoUtil.getTypeAttr();
/*     */     
/*  59 */     createJavaDocHeader(typeAttr.guid.toGuidString(), docString);
/*     */     
/*  61 */     int cVars = typeAttr.cVars.intValue();
/*  62 */     for (int i = 0; i < cVars; i++) {
/*     */       
/*  64 */       OaIdl.VARDESC varDesc = typeInfoUtil.getVarDesc(i);
/*  65 */       Variant.VARIANT.ByReference byReference = varDesc._vardesc.lpvarValue;
/*  66 */       Object value = byReference.getValue();
/*     */ 
/*     */       
/*  69 */       OaIdl.MEMBERID memberID = varDesc.memid;
/*     */ 
/*     */       
/*  72 */       TypeInfoUtil.TypeInfoDoc typeInfoDoc2 = typeInfoUtil.getDocumentation(memberID);
/*  73 */       this.content += "\t\t//" + typeInfoDoc2.getName() + "\n";
/*  74 */       this
/*  75 */         .content = this.content + "\t\tpublic static final int " + typeInfoDoc2.getName() + " = " + value.toString() + ";";
/*     */       
/*  77 */       if (i < cVars - 1) {
/*  78 */         this.content += "\n";
/*     */       }
/*     */       
/*  81 */       typeInfoUtil.ReleaseVarDesc(varDesc);
/*     */     } 
/*     */     
/*  84 */     createContent(this.content);
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
/*  96 */     replaceVariable("uuid", guid);
/*  97 */     replaceVariable("helpstring", helpstring);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbEnum.template"; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbEnum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */