/*    */ package com.sun.jna;
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
/*    */ public class NativeLong
/*    */   extends IntegerType
/*    */ {
/* 23 */   public static final int SIZE = Native.LONG_SIZE;
/*    */ 
/*    */ 
/*    */   
/* 27 */   public NativeLong() { this(0L); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public NativeLong(long value) { this(value, false); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public NativeLong(long value, boolean unsigned) { super(SIZE, value, unsigned); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\NativeLong.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */