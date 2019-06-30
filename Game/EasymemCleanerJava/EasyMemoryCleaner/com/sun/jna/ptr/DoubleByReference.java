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
/*    */ public class DoubleByReference
/*    */   extends ByReference
/*    */ {
/* 17 */   public DoubleByReference() { this(0.0D); }
/*    */ 
/*    */   
/*    */   public DoubleByReference(double value) {
/* 21 */     super(8);
/* 22 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 26 */   public void setValue(double value) { getPointer().setDouble(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public double getValue() { return getPointer().getDouble(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\DoubleByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */