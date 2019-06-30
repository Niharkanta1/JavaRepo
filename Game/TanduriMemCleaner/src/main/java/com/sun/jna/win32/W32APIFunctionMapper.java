/*    */ package com.sun.jna.win32;
/*    */ 
/*    */ import com.sun.jna.FunctionMapper;
/*    */ import com.sun.jna.NativeLibrary;
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
/*    */ 
/*    */ 
/*    */ public class W32APIFunctionMapper
/*    */   implements FunctionMapper
/*    */ {
/* 22 */   public static final FunctionMapper UNICODE = new W32APIFunctionMapper(true);
/* 23 */   public static final FunctionMapper ASCII = new W32APIFunctionMapper(false);
/*    */   private final String suffix;
/*    */   
/* 26 */   protected W32APIFunctionMapper(boolean unicode) { this.suffix = unicode ? "W" : "A"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFunctionName(NativeLibrary library, Method method) {
/* 31 */     String name = method.getName();
/* 32 */     if (!name.endsWith("W") && !name.endsWith("A")) {
/*    */       try {
/* 34 */         name = library.getFunction(name + this.suffix, 63).getName();
/*    */       }
/* 36 */       catch (UnsatisfiedLinkError e) {}
/*    */     }
/*    */ 
/*    */     
/* 40 */     return name;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\win32\W32APIFunctionMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */