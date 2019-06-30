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
/*     */ class XxHash_r39
/*     */ {
/*  28 */   private static final XxHash_r39 INSTANCE = new XxHash_r39(); private static final long P1 = -7046029288634856825L; private static final long P2 = -4417276706812531889L; private static final long P3 = 1609587929392839161L; private static final long P4 = -8796714831421723037L; private static final long P5 = 2870177450012600261L; private XxHash_r39() {} <T> long fetch64(Access<T> access, T in, long off) { return access.getLong(in, off); }
/*  29 */   private static final XxHash_r39 NATIVE_XX = LongHashFunction.NATIVE_LITTLE_ENDIAN ? INSTANCE : 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     INSTANCE; <T> long fetch32(Access<T> access, T in, long off) { return access.getUnsignedInt(in, off); } <T> int fetch8(Access<T> access, T in, long off) { return access.getUnsignedByte(in, off); } long toLittleEndian(long v) { return v; } int toLittleEndian(int v) { return v; } short toLittleEndian(short v) { return v; } public <T> long xxHash64(long seed, T input, Access<T> access, long off, long length) { long hash, remaining = length; if (remaining >= 32L) { long v1 = seed + -7046029288634856825L + -4417276706812531889L; long v2 = seed + -4417276706812531889L; long v3 = seed; long v4 = seed - -7046029288634856825L; do { v1 += fetch64(access, input, off) * -4417276706812531889L; v1 = Long.rotateLeft(v1, 31); v1 *= -7046029288634856825L; v2 += fetch64(access, input, off + 8L) * -4417276706812531889L; v2 = Long.rotateLeft(v2, 31); v2 *= -7046029288634856825L; v3 += fetch64(access, input, off + 16L) * -4417276706812531889L; v3 = Long.rotateLeft(v3, 31); v3 *= -7046029288634856825L; v4 += fetch64(access, input, off + 24L) * -4417276706812531889L; v4 = Long.rotateLeft(v4, 31); v4 *= -7046029288634856825L; off += 32L; remaining -= 32L; } while (remaining >= 32L); hash = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18); v1 *= -4417276706812531889L; v1 = Long.rotateLeft(v1, 31); v1 *= -7046029288634856825L; hash ^= v1; hash = hash * -7046029288634856825L + -8796714831421723037L; v2 *= -4417276706812531889L; v2 = Long.rotateLeft(v2, 31); v2 *= -7046029288634856825L; hash ^= v2; hash = hash * -7046029288634856825L + -8796714831421723037L; v3 *= -4417276706812531889L; v3 = Long.rotateLeft(v3, 31); v3 *= -7046029288634856825L; hash ^= v3; hash = hash * -7046029288634856825L + -8796714831421723037L; v4 *= -4417276706812531889L; v4 = Long.rotateLeft(v4, 31); v4 *= -7046029288634856825L; hash ^= v4; hash = hash * -7046029288634856825L + -8796714831421723037L; } else { hash = seed + 2870177450012600261L; }  hash += length; while (remaining >= 8L) { long k1 = fetch64(access, input, off); k1 *= -4417276706812531889L; k1 = Long.rotateLeft(k1, 31); k1 *= -7046029288634856825L; hash ^= k1; hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L; off += 8L; remaining -= 8L; }  if (remaining >= 4L) { hash ^= fetch32(access, input, off) * -7046029288634856825L; hash = Long.rotateLeft(hash, 23) * -4417276706812531889L + 1609587929392839161L; off += 4L; remaining -= 4L; }  while (remaining != 0L) { hash ^= fetch8(access, input, off) * 2870177450012600261L; hash = Long.rotateLeft(hash, 11) * -7046029288634856825L; remaining--; off++; }  return finalize(hash); } private static long finalize(long hash) { hash ^= hash >>> 33; hash *= -4417276706812531889L; hash ^= hash >>> 29; hash *= 1609587929392839161L; return hash >>> 32; } private static class BigEndian extends XxHash_r39 {
/* 170 */     private static final BigEndian INSTANCE = new BigEndian();
/*     */     
/* 172 */     private BigEndian() { super(null); }
/*     */ 
/*     */ 
/*     */     
/* 176 */     <T> long fetch64(Access<T> access, T in, long off) { return Long.reverseBytes(super.fetch64(access, in, off)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     <T> long fetch32(Access<T> access, T in, long off) { return Integer.reverseBytes(access.getInt(in, off)) & 0xFFFFFFFFL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     long toLittleEndian(long v) { return Long.reverseBytes(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     int toLittleEndian(int v) { return Integer.reverseBytes(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     short toLittleEndian(short v) { return Short.reverseBytes(v); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 203 */   public static LongHashFunction asLongHashFunctionWithoutSeed() { return AsLongHashFunction.SEEDLESS_INSTANCE; }
/*     */   
/*     */   private static class AsLongHashFunction extends LongHashFunction { private AsLongHashFunction() {}
/*     */     
/* 207 */     public static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 211 */     private Object readResolve() { return SEEDLESS_INSTANCE; }
/*     */ 
/*     */ 
/*     */     
/* 215 */     public long seed() { return 0L; }
/*     */ 
/*     */ 
/*     */     
/*     */     public long hashLong(long input) {
/* 220 */       input = NATIVE_XX.toLittleEndian(input);
/* 221 */       long hash = seed() + 2870177450012600261L + 8L;
/* 222 */       input *= -4417276706812531889L;
/* 223 */       input = Long.rotateLeft(input, 31);
/* 224 */       input *= -7046029288634856825L;
/* 225 */       hash ^= input;
/* 226 */       hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L;
/* 227 */       return XxHash_r39.finalize(hash);
/*     */     }
/*     */ 
/*     */     
/*     */     public long hashInt(int input) {
/* 232 */       input = NATIVE_XX.toLittleEndian(input);
/* 233 */       long hash = seed() + 2870177450012600261L + 4L;
/* 234 */       hash ^= input * -7046029288634856825L;
/* 235 */       hash = Long.rotateLeft(hash, 23) * -4417276706812531889L + 1609587929392839161L;
/* 236 */       return XxHash_r39.finalize(hash);
/*     */     }
/*     */ 
/*     */     
/*     */     public long hashShort(short input) {
/* 241 */       input = NATIVE_XX.toLittleEndian(input);
/* 242 */       long hash = seed() + 2870177450012600261L + 2L;
/* 243 */       hash ^= (input & 0xFF) * 2870177450012600261L;
/* 244 */       hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
/* 245 */       hash ^= (input >> 8 & 0xFF) * 2870177450012600261L;
/* 246 */       hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
/* 247 */       return XxHash_r39.finalize(hash);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 252 */     public long hashChar(char input) { return hashShort((short)input); }
/*     */ 
/*     */ 
/*     */     
/*     */     public long hashByte(byte input) {
/* 257 */       long hash = seed() + 2870177450012600261L + 1L;
/* 258 */       hash ^= input * 2870177450012600261L;
/* 259 */       hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
/* 260 */       return XxHash_r39.finalize(hash);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     public long hashVoid() { return XxHash_r39.finalize(2870177450012600261L); }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/* 270 */       long seed = seed();
/* 271 */       if (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 272 */         return INSTANCE.xxHash64(seed, input, access, off, len);
/*     */       }
/* 274 */       return INSTANCE.xxHash64(seed, input, access, off, len);
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public static LongHashFunction asLongHashFunctionWithSeed(long seed) { return new AsLongHashFunctionSeeded(seed, null); }
/*     */   
/*     */   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
/*     */     private final long seed;
/*     */     private final long voidHash;
/*     */     
/*     */     private AsLongHashFunctionSeeded(long seed) {
/* 287 */       super(null);
/* 288 */       this.seed = seed;
/* 289 */       this.voidHash = XxHash_r39.finalize(seed + 2870177450012600261L);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 294 */     public long seed() { return this.seed; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     public long hashVoid() { return this.voidHash; }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\XxHash_r39.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */