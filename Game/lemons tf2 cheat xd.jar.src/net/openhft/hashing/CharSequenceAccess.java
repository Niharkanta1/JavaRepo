/*     */ package net.openhft.hashing;
/*     */ abstract class CharSequenceAccess extends Access<CharSequence> { public static CharSequenceAccess nativeCharSequenceAccess() { return charSequenceAccess(ByteOrder.nativeOrder()); }
/*     */   private static int ix(long offset) { return (int)(offset >> true); }
/*     */   static long getLong(CharSequence input, long offset, int char0Off, int char1Off, int char2Off, int char3Off) {
/*     */     int base = ix(offset);
/*     */     long char0 = input.charAt(base + char0Off);
/*     */     long char1 = input.charAt(base + char1Off);
/*     */     long char2 = input.charAt(base + char2Off);
/*     */     long char3 = input.charAt(base + char3Off);
/*     */     return char0 | char1 << 16 | char2 << 32 | char3 << 48;
/*     */   }
/*     */   static long getUnsignedInt(CharSequence input, long offset, int char0Off, int char1Off) {
/*     */     int base = ix(offset);
/*     */     long char0 = input.charAt(base + char0Off);
/*     */     long char1 = input.charAt(base + char1Off);
/*     */     return char0 | char1 << 16;
/*     */   }
/*     */   private CharSequenceAccess() {}
/*     */   public int getInt(CharSequence input, long offset) { return (int)getUnsignedInt(input, offset); }
/*     */   
/*     */   public int getUnsignedShort(CharSequence input, long offset) { return input.charAt(ix(offset)); }
/*     */   
/*     */   public int getShort(CharSequence input, long offset) { return (short)input.charAt(ix(offset)); }
/*     */   
/*     */   static int getUnsignedByte(CharSequence input, long offset, int shift) { return Primitives.unsignedByte(input.charAt(ix(offset)) >> shift); }
/*     */   
/*  27 */   public static CharSequenceAccess charSequenceAccess(ByteOrder order) { return (order == ByteOrder.LITTLE_ENDIAN) ? 
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
/*  84 */       INSTANCE : 
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
/* 110 */       INSTANCE; } public int getByte(CharSequence input, long offset) { return (byte)getUnsignedByte(input, offset); } private static class LittleEndianCharSequenceAccess extends CharSequenceAccess {
/* 111 */     private static final CharSequenceAccess INSTANCE = new LittleEndianCharSequenceAccess(); private LittleEndianCharSequenceAccess() { super(null); } public long getLong(CharSequence input, long offset) { return getLong(input, offset, 0, 1, 2, 3); } public long getUnsignedInt(CharSequence input, long offset) { return getUnsignedInt(input, offset, 0, 1); } public int getUnsignedByte(CharSequence input, long offset) { return getUnsignedByte(input, offset, ((int)offset & true) << 3); } public ByteOrder byteOrder(CharSequence input) { return ByteOrder.LITTLE_ENDIAN; } } private static class BigEndianCharSequenceAccess extends CharSequenceAccess { private static final CharSequenceAccess INSTANCE = new BigEndianCharSequenceAccess();
/*     */     
/* 113 */     private BigEndianCharSequenceAccess() { super(null); }
/*     */ 
/*     */ 
/*     */     
/* 117 */     public long getLong(CharSequence input, long offset) { return getLong(input, offset, 3, 2, 1, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     public long getUnsignedInt(CharSequence input, long offset) { return getUnsignedInt(input, offset, 1, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     public int getUnsignedByte(CharSequence input, long offset) { return getUnsignedByte(input, offset, ((int)offset & true ^ true) << 3); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     public ByteOrder byteOrder(CharSequence input) { return ByteOrder.BIG_ENDIAN; } }
/*     */    }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\CharSequenceAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */