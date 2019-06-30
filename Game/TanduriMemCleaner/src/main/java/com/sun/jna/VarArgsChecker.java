/*    */ package com.sun.jna;
/*    */ 
/*    */ import java.lang.reflect.Method;
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
/*    */ abstract class VarArgsChecker
/*    */ {
/*    */   private VarArgsChecker() {}
/*    */   
/*    */   private static final class RealVarArgsChecker
/*    */     extends VarArgsChecker
/*    */   {
/* 22 */     private RealVarArgsChecker() { super(null); }
/*    */ 
/*    */     
/* 25 */     boolean isVarArgs(Method m) { return m.isVarArgs(); }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final class NoVarArgsChecker
/*    */     extends VarArgsChecker
/*    */   {
/* 34 */     private NoVarArgsChecker() { super(null); }
/*    */ 
/*    */     
/* 37 */     boolean isVarArgs(Method m) { return false; }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static VarArgsChecker create() {
/*    */     try {
/* 50 */       isVarArgsMethod = Method.class.getMethod("isVarArgs", new Class[0]);
/* 51 */       if (isVarArgsMethod != null)
/*    */       {
/* 53 */         return new RealVarArgsChecker(null);
/*    */       }
/* 55 */       return new NoVarArgsChecker(null);
/*    */     }
/* 57 */     catch (NoSuchMethodException e) {
/* 58 */       return new NoVarArgsChecker(null);
/* 59 */     } catch (SecurityException e) {
/* 60 */       return new NoVarArgsChecker(null);
/*    */     } 
/*    */   }
/*    */   
/*    */   abstract boolean isVarArgs(Method paramMethod);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\VarArgsChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */