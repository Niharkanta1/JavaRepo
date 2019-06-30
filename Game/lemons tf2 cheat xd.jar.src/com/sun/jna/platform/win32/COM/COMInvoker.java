/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Function;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.PointerType;
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
/*    */ public abstract class COMInvoker
/*    */   extends PointerType
/*    */ {
/*    */   protected int _invokeNativeInt(int vtableId, Object[] args) {
/* 22 */     Pointer vptr = getPointer().getPointer(0L);
/*    */ 
/*    */     
/* 25 */     Function func = Function.getFunction(vptr.getPointer((vtableId * Pointer.SIZE)));
/*    */     
/* 27 */     return func.invokeInt(args);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Object _invokeNativeObject(int vtableId, Object[] args, Class returnType) {
/* 32 */     Pointer vptr = getPointer().getPointer(0L);
/*    */ 
/*    */     
/* 35 */     Function func = Function.getFunction(vptr.getPointer((vtableId * Pointer.SIZE)));
/*    */     
/* 37 */     return func.invoke(returnType, args);
/*    */   }
/*    */   
/*    */   protected void _invokeNativeVoid(int vtableId, Object[] args) {
/* 41 */     Pointer vptr = getPointer().getPointer(0L);
/*    */ 
/*    */     
/* 44 */     Function func = Function.getFunction(vptr.getPointer((vtableId * Pointer.SIZE)));
/*    */     
/* 46 */     func.invokeVoid(args);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMInvoker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */