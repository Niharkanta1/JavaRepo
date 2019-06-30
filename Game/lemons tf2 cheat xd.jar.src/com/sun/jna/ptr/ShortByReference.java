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
/*    */ public class ShortByReference
/*    */   extends ByReference
/*    */ {
/* 18 */   public ShortByReference() { this((short)0); }
/*    */ 
/*    */   
/*    */   public ShortByReference(short value) {
/* 22 */     super(2);
/* 23 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 27 */   public void setValue(short value) { getPointer().setShort(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public short getValue() { return getPointer().getShort(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\ShortByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */