/*    */ package com.sun.jna.win32;
/*    */ 
/*    */ import com.sun.jna.FunctionMapper;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.NativeLibrary;
/*    */ import com.sun.jna.NativeMappedConverter;
/*    */ import com.sun.jna.Pointer;
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
/*    */ public class StdCallFunctionMapper
/*    */   implements FunctionMapper
/*    */ {
/*    */   protected int getArgumentNativeStackSize(Class cls) {
/* 36 */     if (com.sun.jna.NativeMapped.class.isAssignableFrom(cls)) {
/* 37 */       cls = NativeMappedConverter.getInstance(cls).nativeType();
/*    */     }
/* 39 */     if (cls.isArray()) {
/* 40 */       return Pointer.SIZE;
/*    */     }
/*    */     try {
/* 43 */       return Native.getNativeSize(cls);
/*    */     }
/* 45 */     catch (IllegalArgumentException e) {
/* 46 */       throw new IllegalArgumentException("Unknown native stack allocation size for " + cls);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFunctionName(NativeLibrary library, Method method) {
/* 53 */     String name = method.getName();
/* 54 */     int pop = 0;
/* 55 */     Class[] argTypes = method.getParameterTypes();
/* 56 */     for (i = 0; i < argTypes.length; i++) {
/* 57 */       pop += getArgumentNativeStackSize(argTypes[i]);
/*    */     }
/* 59 */     String decorated = name + "@" + pop;
/* 60 */     int conv = 63;
/*    */     try {
/* 62 */       name = library.getFunction(decorated, conv).getName();
/*    */     
/*    */     }
/* 65 */     catch (UnsatisfiedLinkError e) {
/*    */       
/*    */       try {
/* 68 */         name = library.getFunction("_" + decorated, conv).getName();
/*    */       }
/* 70 */       catch (UnsatisfiedLinkError e2) {}
/*    */     } 
/*    */ 
/*    */     
/* 74 */     return name;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\win32\StdCallFunctionMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */