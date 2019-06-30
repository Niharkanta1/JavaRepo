/*    */ package com.sun.jna.ptr;
/*    */ 
/*    */ import com.sun.jna.Memory;
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
/*    */ public abstract class ByReference
/*    */   extends PointerType
/*    */ {
/* 31 */   protected ByReference(int dataSize) { setPointer(new Memory(dataSize)); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\ptr\ByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */