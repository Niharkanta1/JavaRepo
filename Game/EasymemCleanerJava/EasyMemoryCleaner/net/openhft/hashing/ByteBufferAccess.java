/*    */ package net.openhft.hashing;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
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
/*    */ final class ByteBufferAccess
/*    */   extends Access<ByteBuffer>
/*    */ {
/* 23 */   public static final ByteBufferAccess INSTANCE = new ByteBufferAccess();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public long getLong(ByteBuffer input, long offset) { return input.getLong((int)offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public long getUnsignedInt(ByteBuffer input, long offset) { return Primitives.unsignedInt(getInt(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public int getInt(ByteBuffer input, long offset) { return input.getInt((int)offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public int getUnsignedShort(ByteBuffer input, long offset) { return Primitives.unsignedShort(getShort(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int getShort(ByteBuffer input, long offset) { return input.getShort((int)offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int getUnsignedByte(ByteBuffer input, long offset) { return Primitives.unsignedByte(getByte(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public int getByte(ByteBuffer input, long offset) { return input.get((int)offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public ByteOrder byteOrder(ByteBuffer input) { return input.order(); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\ByteBufferAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */