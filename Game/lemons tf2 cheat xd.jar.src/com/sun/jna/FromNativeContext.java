/*    */ package com.sun.jna;
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
/*    */ public class FromNativeContext
/*    */ {
/*    */   private Class type;
/*    */   
/* 19 */   FromNativeContext(Class javaType) { this.type = javaType; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public Class getTargetType() { return this.type; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\FromNativeContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */