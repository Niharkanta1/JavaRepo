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
/*    */ public class TlbFunctionDispId
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbFunctionDispId(int count, int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 42 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 44 */     String[] names = typeInfoUtil.getNames(funcDesc.memid, this.paramCount + 1);
/*    */     
/* 46 */     for (int i = 0; i < this.paramCount; i++) {
/* 47 */       OaIdl.ELEMDESC elemdesc = funcDesc.lprgelemdescParam.elemDescArg[i];
/* 48 */       String methodName = names[i + 1].toLowerCase();
/* 49 */       String type = getType(elemdesc.tdesc);
/* 50 */       String _methodName = replaceJavaKeyword(methodName);
/* 51 */       this.methodparams += type + " " + _methodName;
/*    */ 
/*    */       
/* 54 */       if (type.equals("VARIANT")) {
/* 55 */         this.methodvariables += _methodName;
/*    */       } else {
/* 57 */         this.methodvariables += "new VARIANT(" + _methodName + ")";
/*    */       } 
/*    */       
/* 60 */       if (i < this.paramCount - 1) {
/* 61 */         this.methodparams += ", ";
/* 62 */         this.methodvariables += ", ";
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 67 */     if (this.returnType.equalsIgnoreCase("VARIANT")) {
/* 68 */       returnValue = "pResult";
/*    */     } else {
/* 70 */       returnValue = "((" + this.returnType + ") pResult.getValue())";
/*    */     } 
/* 72 */     replaceVariable("helpstring", this.docStr);
/* 73 */     replaceVariable("returntype", this.returnType);
/* 74 */     replaceVariable("returnvalue", returnValue);
/* 75 */     replaceVariable("methodname", this.methodName);
/* 76 */     replaceVariable("methodparams", this.methodparams);
/* 77 */     replaceVariable("methodvariables", this.methodvariables);
/* 78 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 79 */     replaceVariable("memberid", String.valueOf(this.memberid));
/* 80 */     replaceVariable("functionCount", String.valueOf(count));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionDispId.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbFunctionDispId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */