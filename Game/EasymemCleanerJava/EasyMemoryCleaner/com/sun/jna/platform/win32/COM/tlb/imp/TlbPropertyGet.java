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
/*    */ public class TlbPropertyGet
/*    */   extends TlbAbstractMethod
/*    */ {
/*    */   public TlbPropertyGet(int count, int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/* 41 */     super(index, typeLibUtil, funcDesc, typeInfoUtil);
/*    */     
/* 43 */     this.methodName = "get" + getMethodName();
/*    */     
/* 45 */     replaceVariable("helpstring", this.docStr);
/* 46 */     replaceVariable("returntype", this.returnType);
/* 47 */     replaceVariable("methodname", this.methodName);
/* 48 */     replaceVariable("vtableid", String.valueOf(this.vtableId));
/* 49 */     replaceVariable("memberid", String.valueOf(this.memberid));
/* 50 */     replaceVariable("functionCount", String.valueOf(count));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   protected String getClassTemplate() { return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGet.template"; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbPropertyGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */