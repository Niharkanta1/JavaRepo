/*    */ package com.sun.jna.ptr;
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
/*    */ public class FloatByReference
/*    */   extends ByReference
/*    */ {
/* 17 */   public FloatByReference() { this(0.0F); }
/*    */ 
/*    */   
/*    */   public FloatByReference(float value) {
/* 21 */     super(4);
/* 22 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 26 */   public void setValue(float value) { getPointer().setFloat(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public float getValue() { return getPointer().getFloat(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\FloatByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */