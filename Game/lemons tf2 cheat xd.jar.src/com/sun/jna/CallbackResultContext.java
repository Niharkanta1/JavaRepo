/*    */ package com.sun.jna;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class CallbackResultContext
/*    */   extends ToNativeContext {
/*    */   private Method method;
/*    */   
/*  9 */   CallbackResultContext(Method callbackMethod) { this.method = callbackMethod; }
/*    */   
/* 11 */   public Method getMethod() { return this.method; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\CallbackResultContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */