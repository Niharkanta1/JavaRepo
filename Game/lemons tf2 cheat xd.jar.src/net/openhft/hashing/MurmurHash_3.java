/*     */ package net.openhft.hashing;
/*     */ 
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
/*     */ class MurmurHash_3
/*     */ {
/*  29 */   private static final MurmurHash_3 INSTANCE = new MurmurHash_3(); private static final long C1 = -8663945395140668459L; private static final long C2 = 5545529020109919103L; private MurmurHash_3() {} <T> long fetch64(Access<T> access, T in, long off) { return access.getLong(in, off); } <T> int fetch32(Access<T> access, T in, long off) { return access.getInt(in, off); } long toLittleEndian(long v) { return v; }
/*     */   int toLittleEndian(int v) { return v; }
/*  31 */   private static final MurmurHash_3 NATIVE_MURMUR = LongHashFunction.NATIVE_LITTLE_ENDIAN ? INSTANCE : 
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
/* 235 */     INSTANCE; int toLittleEndianShort(int unsignedShort) { return unsignedShort; } public <T> long hash(long seed, T input, Access<T> access, long offset, long length) { long h1 = seed; long h2 = seed; long remaining = length; while (remaining >= 16L) { long k1 = fetch64(access, input, offset); long k2 = fetch64(access, input, offset + 8L); offset += 16L; remaining -= 16L; h1 ^= mixK1(k1); h1 = Long.rotateLeft(h1, 27); h1 += h2; h1 = h1 * 5L + 1390208809L; h2 ^= mixK2(k2); h2 = Long.rotateLeft(h2, 31); h2 += h1; h2 = h2 * 5L + 944331445L; }  if (remaining > 0L) { long k1 = 0L; long k2 = 0L; switch ((int)remaining) { case 15: k2 ^= access.getUnsignedByte(input, offset + 14L) << 48;case 14: k2 ^= access.getUnsignedByte(input, offset + 13L) << 40;case 13: k2 ^= access.getUnsignedByte(input, offset + 12L) << 32;case 12: k2 ^= access.getUnsignedByte(input, offset + 11L) << 24;case 11: k2 ^= access.getUnsignedByte(input, offset + 10L) << 16;case 10: k2 ^= access.getUnsignedByte(input, offset + 9L) << 8;case 9: k2 ^= access.getUnsignedByte(input, offset + 8L);case 8: k1 ^= fetch64(access, input, offset); break;case 7: k1 ^= access.getUnsignedByte(input, offset + 6L) << 48;case 6: k1 ^= access.getUnsignedByte(input, offset + 5L) << 40;case 5: k1 ^= access.getUnsignedByte(input, offset + 4L) << 32;case 4: k1 ^= Primitives.unsignedInt(fetch32(access, input, offset)); break;case 3: k1 ^= access.getUnsignedByte(input, offset + 2L) << 16;case 2: k1 ^= access.getUnsignedByte(input, offset + 1L) << 8;case 1: k1 ^= access.getUnsignedByte(input, offset); break;case 0: break;default: throw new AssertionError("Should never get here."); }  h1 ^= mixK1(k1); h2 ^= mixK2(k2); }  return finalize(length, h1, h2); } private static long finalize(long length, long h1, long h2) { h1 ^= length; h2 ^= length; h1 += h2; h2 += h1; h1 = fmix64(h1); h2 = fmix64(h2); return h2; } private static long fmix64(long k) { k ^= k >>> 33; k *= -49064778989728563L; k ^= k >>> 33; k *= -4265267296055464877L; return k >>> 33; } private static long mixK1(long k1) { k1 *= -8663945395140668459L; k1 = Long.rotateLeft(k1, 31); return 5545529020109919103L; } private static long mixK2(long k2) { k2 *= 5545529020109919103L; k2 = Long.rotateLeft(k2, 33); return -8663945395140668459L; } private static class BigEndian extends MurmurHash_3 {
/* 236 */     private static final BigEndian INSTANCE = new BigEndian();
/* 237 */     private BigEndian() { super(null); }
/*     */ 
/*     */ 
/*     */     
/* 241 */     <T> long fetch64(Access<T> access, T in, long off) { return Long.reverseBytes(super.fetch64(access, in, off)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     <T> int fetch32(Access<T> access, T in, long off) { return Integer.reverseBytes(super.fetch32(access, in, off)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     long toLittleEndian(long v) { return Long.reverseBytes(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     int toLittleEndian(int v) { return Integer.reverseBytes(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     int toLittleEndianShort(int unsignedShort) { return (unsignedShort & 0xFF) << 8 | unsignedShort >> 8; } }
/*     */   
/*     */   private static class AsLongHashFunction extends LongHashFunction {
/*     */     private AsLongHashFunction() {}
/*     */     
/* 266 */     public static final AsLongHashFunction INSTANCE = new AsLongHashFunction();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 270 */     private Object readResolve() { return INSTANCE; }
/*     */ 
/*     */ 
/*     */     
/* 274 */     long seed() { return 0L; }
/*     */ 
/*     */     
/*     */     long hashNativeLong(long nativeLong, long len) {
/* 278 */       long h1 = MurmurHash_3.mixK1(nativeLong);
/* 279 */       long h2 = 0L;
/* 280 */       return MurmurHash_3.finalize(len, h1, h2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 285 */     public long hashLong(long input) { return hashNativeLong(NATIVE_MURMUR.toLittleEndian(input), 8L); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     public long hashInt(int input) { return hashNativeLong(Primitives.unsignedInt(NATIVE_MURMUR.toLittleEndian(input)), 4L); }
/*     */ 
/*     */ 
/*     */     
/*     */     public long hashShort(short input) {
/* 295 */       return hashNativeLong(
/* 296 */           NATIVE_MURMUR.toLittleEndianShort(Primitives.unsignedShort(input)), 2L);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 301 */     public long hashChar(char input) { return hashNativeLong(NATIVE_MURMUR.toLittleEndianShort(input), 2L); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     public long hashByte(byte input) { return hashNativeLong(Primitives.unsignedByte(input), 1L); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     public long hashVoid() { return 0L; }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/* 316 */       long seed = seed();
/* 317 */       if (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 318 */         return INSTANCE.hash(seed, input, access, off, len);
/*     */       }
/* 320 */       return INSTANCE.hash(seed, input, access, off, len);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 326 */   public static LongHashFunction asLongHashFunctionWithoutSeed() { return AsLongHashFunction.INSTANCE; }
/*     */   
/*     */   private static class AsLongHashFunctionSeeded
/*     */     extends AsLongHashFunction {
/*     */     private static final long serialVersionUID = 0L;
/*     */     private final long seed;
/*     */     private long voidHash;
/*     */     
/*     */     private AsLongHashFunctionSeeded(long seed) {
/* 335 */       super(null);
/* 336 */       this.seed = seed;
/* 337 */       this.voidHash = MurmurHash_3.finalize(0L, seed, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 342 */     long seed() { return this.seed; }
/*     */ 
/*     */ 
/*     */     
/*     */     long hashNativeLong(long nativeLong, long len) {
/* 347 */       long seed = this.seed;
/* 348 */       long h1 = seed ^ MurmurHash_3.mixK1(nativeLong);
/* 349 */       long h2 = seed;
/* 350 */       return MurmurHash_3.finalize(len, h1, h2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 355 */     public long hashVoid() { return this.voidHash; }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 360 */   public static LongHashFunction asLongHashFunctionWithSeed(long seed) { return new AsLongHashFunctionSeeded(seed, null); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\MurmurHash_3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */