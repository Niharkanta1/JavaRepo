/*     */ package net.openhft.hashing;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Access<T>
/*     */   extends Object
/*     */ {
/*  83 */   public static <T> Access<T> unsafe() { return UnsafeAccess.INSTANCE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static Access<ByteBuffer> toByteBuffer() { return ByteBufferAccess.INSTANCE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static <T extends CharSequence> Access<T> toNativeCharSequence() { return CharSequenceAccess.nativeCharSequenceAccess(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static <T extends CharSequence> Access<T> toCharSequence(ByteOrder backingOrder) { return CharSequenceAccess.charSequenceAccess(backingOrder); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(T input, long offset) {
/* 157 */     if (byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 158 */       return getUnsignedInt(input, offset) | getUnsignedInt(input, offset + 4L) << 32;
/*     */     }
/* 160 */     return getUnsignedInt(input, offset + 4L) | getUnsignedInt(input, offset) << 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public long getUnsignedInt(T input, long offset) { return getInt(input, offset) & 0xFFFFFFFFL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(T input, long offset) {
/* 189 */     if (byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 190 */       return getUnsignedShort(input, offset) | getUnsignedShort(input, offset + 2L) << 16;
/*     */     }
/* 192 */     return getUnsignedShort(input, offset + 2L) | getUnsignedShort(input, offset) << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnsignedShort(T input, long offset) {
/* 207 */     if (byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 208 */       return getUnsignedByte(input, offset) | getUnsignedByte(input, offset + 1L) << 8;
/*     */     }
/* 210 */     return getUnsignedByte(input, offset + 1L) | getUnsignedByte(input, offset) << 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public int getShort(T input, long offset) { return (short)getUnsignedShort(input, offset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public int getUnsignedByte(T input, long offset) { return getByte(input, offset) & 0xFF; }
/*     */   
/*     */   public abstract int getByte(T paramT, long paramLong);
/*     */   
/*     */   public abstract ByteOrder byteOrder(T paramT);
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\Access.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */