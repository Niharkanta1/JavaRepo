/*    */ package com.sun.jna.ptr;
/*    */ 
/*    */ import com.sun.jna.NativeLong;
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
/*    */ public class NativeLongByReference
/*    */   extends ByReference
/*    */ {
/* 19 */   public NativeLongByReference() { this(new NativeLong(0L)); }
/*    */ 
/*    */   
/*    */   public NativeLongByReference(NativeLong value) {
/* 23 */     super(NativeLong.SIZE);
/* 24 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 28 */   public void setValue(NativeLong value) { getPointer().setNativeLong(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public NativeLong getValue() { return getPointer().getNativeLong(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\NativeLongByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */