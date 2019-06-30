/*    */ package com.sun.jna.ptr;
/*    */ 
/*    */ import com.sun.jna.Pointer;
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
/*    */ public class PointerByReference
/*    */   extends ByReference
/*    */ {
/* 24 */   public PointerByReference() { this(null); }
/*    */ 
/*    */   
/*    */   public PointerByReference(Pointer value) {
/* 28 */     super(Pointer.SIZE);
/* 29 */     setValue(value);
/*    */   }
/*    */ 
/*    */   
/* 33 */   public void setValue(Pointer value) { getPointer().setPointer(0L, value); }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Pointer getValue() { return getPointer().getPointer(0L); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\PointerByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */