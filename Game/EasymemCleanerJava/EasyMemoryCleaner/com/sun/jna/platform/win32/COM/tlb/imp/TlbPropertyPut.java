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
/*    */ public class TlbPropertyPut
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbPropertyPut(int count, int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 42 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 44 */     this.methodName = "set" + getMethodName();
/* 45 */     String[] names = typeInfoUtil.getNames(funcDesc.memid, this.paramCount + 1);
/*    */     
/* 47 */     if (this.paramCount > 0) {
/* 48 */       this.methodvariables += ", ";
/*    */     }
/* 50 */     for (int i = 0; i < this.paramCount; i++) {
/* 51 */       OaIdl.ELEMDESC elemdesc = funcDesc.lprgelemdescParam.elemDescArg[i];
/* 52 */       String varType = getType(elemdesc);
/* 53 */       this
/* 54 */         .methodparams = this.methodparams + varType + " " + replaceJavaKeyword(names[i].toLowerCase());
/* 55 */       this.methodvariables += replaceJavaKeyword(names[i].toLowerCase());
/*    */ 
/*    */       
/* 58 */       if (i < this.paramCount - 1) {
/* 59 */         this.methodparams += ", ";
/* 60 */         this.methodvariables += ", ";
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     replaceVariable("helpstring", this.docStr);
/* 65 */     replaceVariable("methodname", this.methodName);
/* 66 */     replaceVariable("methodparams", this.methodparams);
/* 67 */     replaceVariable("methodvariables", this.methodvariables);
/* 68 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 69 */     replaceVariable("memberid", String.valueOf(this.memberid));
/* 70 */     replaceVariable("functionCount", String.valueOf(count));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPut.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbPropertyPut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */