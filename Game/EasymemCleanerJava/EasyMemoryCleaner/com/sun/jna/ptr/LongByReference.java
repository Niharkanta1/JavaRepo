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
/*    */ public class LongByReference
/*    */   extends ByReference
/*    */ {
/* 17 */   public LongByReference() { this(0L); }
/*    */ 
/*    */   
/*    */   public LongByReference(long value) {
/* 21 */     super(8);
/* 22 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 26 */   public void setValue(long value) { getPointer().setLong(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public long getValue() { return getPointer().getLong(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\LongByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */