/*    */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*    */ 
/*    */ import com.sun.jna.platform.win32.COM.TypeInfoUtil;
/*    */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*    */ import com.sun.jna.platform.win32.OaIdl;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TlbFunctionStub
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbFunctionStub(int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 43 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 45 */     TypeInfoUtil.TypeInfoDoc typeInfoDoc = typeInfoUtil.getDocumentation(funcDesc.memid);
/* 46 */     String methodname = typeInfoDoc.getName();
/* 47 */     String docStr = typeInfoDoc.getDocString();
/* 48 */     String[] names = typeInfoUtil.getNames(funcDesc.memid, this.paramCount + 1);
/*    */ 
/*    */     
/* 51 */     if (this.paramCount > 0) {
/* 52 */       this.methodvariables = ", ";
/*    */     }
/* 54 */     for (int i = 0; i < this.paramCount; i++) {
/* 55 */       OaIdl.ELEMDESC elemdesc = funcDesc.lprgelemdescParam.elemDescArg[i];
/* 56 */       String methodName = names[i + 1].toLowerCase();
/* 57 */       this
/* 58 */         .methodparams = this.methodparams + getType(elemdesc.tdesc) + " " + replaceJavaKeyword(methodName);
/* 59 */       this.methodvariables += methodName;
/*    */ 
/*    */       
/* 62 */       if (i < this.paramCount - 1) {
/* 63 */         this.methodparams += ", ";
/* 64 */         this.methodvariables += ", ";
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     replaceVariable("helpstring", docStr);
/* 69 */     replaceVariable("returntype", this.returnType);
/* 70 */     replaceVariable("methodname", methodname);
/* 71 */     replaceVariable("methodparams", this.methodparams);
/* 72 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 73 */     replaceVariable("memberid", String.valueOf(this.memberid));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionStub.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbFunctionStub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */