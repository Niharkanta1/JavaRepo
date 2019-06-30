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
/*    */ public class TlbFunctionVTable
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbFunctionVTable(int count, int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 42 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 44 */     String[] names = typeInfoUtil.getNames(funcDesc.memid, this.paramCount + 1);
/*    */ 
/*    */     
/* 47 */     if (this.paramCount > 0) {
/* 48 */       this.methodvariables = ", ";
/*    */     }
/* 50 */     for (int i = 0; i < this.paramCount; i++) {
/* 51 */       OaIdl.ELEMDESC elemdesc = funcDesc.lprgelemdescParam.elemDescArg[i];
/* 52 */       String methodName = names[i + 1].toLowerCase();
/* 53 */       this
/* 54 */         .methodparams = this.methodparams + getType(elemdesc.tdesc) + " " + replaceJavaKeyword(methodName);
/* 55 */       this.methodvariables += methodName;
/*    */ 
/*    */       
/* 58 */       if (i < this.paramCount - 1) {
/* 59 */         this.methodparams += ", ";
/* 60 */         this.methodvariables += ", ";
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     replaceVariable("helpstring", this.docStr);
/* 65 */     replaceVariable("returntype", this.returnType);
/* 66 */     replaceVariable("methodname", this.methodName);
/* 67 */     replaceVariable("methodparams", this.methodparams);
/* 68 */     replaceVariable("methodvariables", this.methodvariables);
/* 69 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 70 */     replaceVariable("memberid", String.valueOf(this.memberid));
/* 71 */     replaceVariable("functionCount", String.valueOf(count));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 81 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionVTable.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbFunctionVTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */