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
/*    */ public class TlbPropertyPutStub
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbPropertyPutStub(int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 43 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 45 */     TypeInfoUtil.TypeInfoDoc typeInfoDoc = typeInfoUtil.getDocumentation(funcDesc.memid);
/* 46 */     String docStr = typeInfoDoc.getDocString();
/* 47 */     String methodname = "set" + typeInfoDoc.getName();
/* 48 */     String[] names = typeInfoUtil.getNames(funcDesc.memid, this.paramCount + 1);
/*    */     
/* 50 */     for (int i = 0; i < this.paramCount; i++) {
/* 51 */       OaIdl.ELEMDESC elemdesc = funcDesc.lprgelemdescParam.elemDescArg[i];
/* 52 */       String varType = getType(elemdesc);
/* 53 */       this
/* 54 */         .methodparams = this.methodparams + varType + " " + replaceJavaKeyword(names[i].toLowerCase());
/*    */ 
/*    */       
/* 57 */       if (i < this.paramCount - 1) {
/* 58 */         this.methodparams += ", ";
/*    */       }
/*    */     } 
/*    */     
/* 62 */     replaceVariable("helpstring", docStr);
/* 63 */     replaceVariable("methodname", methodname);
/* 64 */     replaceVariable("methodparams", this.methodparams);
/* 65 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 66 */     replaceVariable("memberid", String.valueOf(this.memberid));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPutStub.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbPropertyPutStub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */