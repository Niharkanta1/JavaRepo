/*    */ package net.openhft.hashing;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.nio.ByteOrder;
/*    */ import sun.misc.Unsafe;
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
/*    */ final class UnsafeAccess
/*    */   extends Access<Object>
/*    */ {
/* 27 */   public static final UnsafeAccess INSTANCE = new UnsafeAccess();
/*    */   
/*    */   static final Unsafe UNSAFE;
/*    */   static final long BOOLEAN_BASE;
/*    */   static final long BYTE_BASE;
/*    */   static final long CHAR_BASE;
/*    */   static final long SHORT_BASE;
/*    */   static final long INT_BASE;
/*    */   static final long LONG_BASE;
/*    */   
/*    */   static  {
/*    */     try {
/* 39 */       theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
/* 40 */       theUnsafe.setAccessible(true);
/* 41 */       UNSAFE = (Unsafe)theUnsafe.get(null);
/* 42 */       BOOLEAN_BASE = UNSAFE.arrayBaseOffset(boolean[].class);
/* 43 */       BYTE_BASE = UNSAFE.arrayBaseOffset(byte[].class);
/* 44 */       CHAR_BASE = UNSAFE.arrayBaseOffset(char[].class);
/* 45 */       SHORT_BASE = UNSAFE.arrayBaseOffset(short[].class);
/* 46 */       INT_BASE = UNSAFE.arrayBaseOffset(int[].class);
/* 47 */       LONG_BASE = UNSAFE.arrayBaseOffset(long[].class);
/* 48 */     } catch (Exception e) {
/* 49 */       throw new AssertionError(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public long getLong(Object input, long offset) { return UNSAFE.getLong(input, offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public long getUnsignedInt(Object input, long offset) { return Primitives.unsignedInt(getInt(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public int getInt(Object input, long offset) { return UNSAFE.getInt(input, offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 72 */   public int getUnsignedShort(Object input, long offset) { return Primitives.unsignedShort(getShort(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public int getShort(Object input, long offset) { return UNSAFE.getShort(input, offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   public int getUnsignedByte(Object input, long offset) { return Primitives.unsignedByte(getByte(input, offset)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 87 */   public int getByte(Object input, long offset) { return UNSAFE.getByte(input, offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 92 */   public ByteOrder byteOrder(Object input) { return ByteOrder.nativeOrder(); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\UnsafeAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */