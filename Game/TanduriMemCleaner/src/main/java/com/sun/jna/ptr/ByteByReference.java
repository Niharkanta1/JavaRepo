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
/*    */ public class ByteByReference
/*    */   extends ByReference
/*    */ {
/* 18 */   public ByteByReference() { this((byte)0); }
/*    */ 
/*    */   
/*    */   public ByteByReference(byte value) {
/* 22 */     super(1);
/* 23 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 27 */   public void setValue(byte value) { getPointer().setByte(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public byte getValue() { return getPointer().getByte(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\ByteByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */