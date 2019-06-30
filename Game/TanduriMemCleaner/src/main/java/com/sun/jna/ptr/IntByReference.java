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
/*    */ 
/*    */ public class IntByReference
/*    */   extends ByReference
/*    */ {
/* 18 */   public IntByReference() { this(0); }
/*    */ 
/*    */   
/*    */   public IntByReference(int value) {
/* 22 */     super(4);
/* 23 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 27 */   public void setValue(int value) { getPointer().setInt(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public int getValue() { return getPointer().getInt(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\IntByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */