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
/*    */ public class TlbPropertyGetStub
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbPropertyGetStub(int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 42 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 44 */     TypeInfoUtil.TypeInfoDoc typeInfoDoc = typeInfoUtil.getDocumentation(funcDesc.memid);
/* 45 */     String docStr = typeInfoDoc.getDocString();
/* 46 */     String methodname = "get" + typeInfoDoc.getName();
/*    */     
/* 48 */     replaceVariable("helpstring", docStr);
/* 49 */     replaceVariable("returntype", this.returnType);
/* 50 */     replaceVariable("methodname", methodname);
/* 51 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 52 */     replaceVariable("memberid", String.valueOf(this.memberid));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGetStub.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbPropertyGetStub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */