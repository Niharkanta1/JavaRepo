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
/*     */ class CityAndFarmHash_1_1
/*     */ {
/*  29 */   private static final CityAndFarmHash_1_1 INSTANCE = new CityAndFarmHash_1_1();
/*     */   static final long K0 = -4348849565147123417L;
/*  31 */   static final long K1 = -5435081209227447693L; static final long K2 = -7286425919675154353L; private static final long K_MUL = -7070675565921424023L; static long shiftMix(long val) { return val ^ val >>> 47; } static long hashLen16(long u, long v) { return hashLen16(u, v, -7070675565921424023L); } static long hashLen16(long u, long v, long mul) { long a = shiftMix((u ^ v) * mul); return shiftMix((v ^ a) * mul) * mul; } static long mul(long len) { return -7286425919675154353L + (len << true); } static long hash1To3Bytes(int len, int firstByte, int midOrLastByte, int lastByte) { int y = firstByte + (midOrLastByte << 8); int z = len + (lastByte << 2); return shiftMix(y * -7286425919675154353L ^ z * -4348849565147123417L) * -7286425919675154353L; } static long hash4To7Bytes(long len, long first4Bytes, long last4Bytes) { long mul = mul(len); return hashLen16(len + (first4Bytes << 3), last4Bytes, mul); } static final CityAndFarmHash_1_1 NATIVE_CITY = LongHashFunction.NATIVE_LITTLE_ENDIAN ? INSTANCE : 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     INSTANCE; static long hash8To16Bytes(long len, long first8Bytes, long last8Bytes) { long mul = mul(len); long a = first8Bytes + -7286425919675154353L; long c = Long.rotateRight(last8Bytes, 37) * mul + a; long d = (Long.rotateRight(a, 25) + last8Bytes) * mul; return hashLen16(c, d, mul); } <T> long fetch64(Access<T> access, T in, long off) { return access.getLong(in, off); } <T> int fetch32(Access<T> access, T in, long off) { return access.getInt(in, off); } long toLittleEndian(long v) { return v; } int toLittleEndian(int v) { return v; } <T> long hashLen0To16(Access<T> access, T in, long off, long len) { if (len >= 8L) { long a = fetch64(access, in, off); long b = fetch64(access, in, off + len - 8L); return hash8To16Bytes(len, a, b); }  if (len >= 4L) { long a = Primitives.unsignedInt(fetch32(access, in, off)); long b = Primitives.unsignedInt(fetch32(access, in, off + len - 4L)); return hash4To7Bytes(len, a, b); }  if (len > 0L) { int a = access.getUnsignedByte(in, off); int b = access.getUnsignedByte(in, off + (len >> true)); int c = access.getUnsignedByte(in, off + len - 1L); return hash1To3Bytes((int)len, a, b, c); }  return -7286425919675154353L; } <T> long hashLen17To32(Access<T> access, T in, long off, long len) { long mul = mul(len); long a = fetch64(access, in, off) * -5435081209227447693L; long b = fetch64(access, in, off + 8L); long c = fetch64(access, in, off + len - 8L) * mul; long d = fetch64(access, in, off + len - 16L) * -7286425919675154353L; return hashLen16(Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d, a + Long.rotateRight(b + -7286425919675154353L, 18) + c, mul); } private <T> long cityHashLen33To64(Access<T> access, T in, long off, long len) { long mul = mul(len); long a = fetch64(access, in, off) * -7286425919675154353L; long b = fetch64(access, in, off + 8L); long c = fetch64(access, in, off + len - 24L); long d = fetch64(access, in, off + len - 32L); long e = fetch64(access, in, off + 16L) * -7286425919675154353L; long f = fetch64(access, in, off + 24L) * 9L; long g = fetch64(access, in, off + len - 8L); long h = fetch64(access, in, off + len - 16L) * mul; long u = Long.rotateRight(a + g, 43) + (Long.rotateRight(b, 30) + c) * 9L; long v = (a + g ^ d) + f + 1L; long w = Long.reverseBytes((u + v) * mul) + h; long x = Long.rotateRight(e + f, 42) + c; long y = (Long.reverseBytes((v + w) * mul) + g) * mul; long z = e + f + c; a = Long.reverseBytes((x + z) * mul + y) + b; b = shiftMix((z + a) * mul + d + h) * mul; return b + x; } <T> long cityHash64(Access<T> access, T in, long off, long len) { if (len <= 32L) { if (len <= 16L) return hashLen0To16(access, in, off, len);  return hashLen17To32(access, in, off, len); }  if (len <= 64L) return cityHashLen33To64(access, in, off, len);  long x = fetch64(access, in, off + len - 40L); long y = fetch64(access, in, off + len - 16L) + fetch64(access, in, off + len - 56L); long z = hashLen16(fetch64(access, in, off + len - 48L) + len, fetch64(access, in, off + len - 24L)); long a3 = len; long b3 = z; long w4 = fetch64(access, in, off + len - 64L); long x4 = fetch64(access, in, off + len - 64L + 8L); long y4 = fetch64(access, in, off + len - 64L + 16L); long z4 = fetch64(access, in, off + len - 64L + 24L); a3 += w4; b3 = Long.rotateRight(b3 + a3 + z4, 21); long c3 = a3; a3 += x4 + y4; b3 += Long.rotateRight(a3, 44); long vFirst = a3 + z4; long vSecond = b3 + c3; long a2 = y + -5435081209227447693L; long b2 = x; long w3 = fetch64(access, in, off + len - 32L); long x3 = fetch64(access, in, off + len - 32L + 8L); long y3 = fetch64(access, in, off + len - 32L + 16L); long z3 = fetch64(access, in, off + len - 32L + 24L); a2 += w3; b2 = Long.rotateRight(b2 + a2 + z3, 21); long c2 = a2; a2 += x3 + y3; b2 += Long.rotateRight(a2, 44); long wFirst = a2 + z3; long wSecond = b2 + c2; x = x * -5435081209227447693L + fetch64(access, in, off); len = len - 1L & 0xFFFFFFFFFFFFFFC0L; do { x = Long.rotateRight(x + y + vFirst + fetch64(access, in, off + 8L), 37) * -5435081209227447693L; y = Long.rotateRight(y + vSecond + fetch64(access, in, off + 48L), 42) * -5435081209227447693L; x ^= wSecond; y += vFirst + fetch64(access, in, off + 40L); z = Long.rotateRight(z + wFirst, 33) * -5435081209227447693L; long a1 = vSecond * -5435081209227447693L; long b1 = x + wFirst; long w2 = fetch64(access, in, off); long x2 = fetch64(access, in, off + 8L); long y2 = fetch64(access, in, off + 16L); long z2 = fetch64(access, in, off + 24L); a1 += w2; b1 = Long.rotateRight(b1 + a1 + z2, 21); long c1 = a1; a1 += x2 + y2; b1 += Long.rotateRight(a1, 44); vFirst = a1 + z2; vSecond = b1 + c1; long a = z + wSecond; long b = y + fetch64(access, in, off + 16L); long w1 = fetch64(access, in, off + 32L); long x1 = fetch64(access, in, off + 32L + 8L); long y1 = fetch64(access, in, off + 32L + 16L); long z1 = fetch64(access, in, off + 32L + 24L); a += w1; b = Long.rotateRight(b + a + z1, 21); long c = a; a += x1 + y1; b += Long.rotateRight(a, 44); wFirst = a + z1; wSecond = b + c; long tmp = x; x = z; z = tmp; len -= 64L; off += 64L; } while (len != 0L); return hashLen16(hashLen16(vFirst, wFirst) + shiftMix(y) * -5435081209227447693L + z, hashLen16(vSecond, wSecond) + x); } private static class BigEndian extends CityAndFarmHash_1_1 {
/* 245 */     private static final BigEndian INSTANCE = new BigEndian();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     <T> long fetch64(Access<T> access, T in, long off) { return Long.reverseBytes(super.fetch64(access, in, off)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     <T> int fetch32(Access<T> access, T in, long off) { return Integer.reverseBytes(super.fetch32(access, in, off)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     long toLittleEndian(long v) { return Long.reverseBytes(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     int toLittleEndian(int v) { return Integer.reverseBytes(v); }
/*     */   }
/*     */   
/*     */   static class AsLongHashFunction
/*     */     extends LongHashFunction {
/* 270 */     public static final AsLongHashFunction INSTANCE = new AsLongHashFunction();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 274 */     private Object readResolve() { return INSTANCE; }
/*     */ 
/*     */ 
/*     */     
/*     */     public long hashLong(long input) {
/* 279 */       input = CityAndFarmHash_1_1.NATIVE_CITY.toLittleEndian(input);
/* 280 */       long hash = CityAndFarmHash_1_1.hash8To16Bytes(8L, input, input);
/* 281 */       return finalize(hash);
/*     */     }
/*     */ 
/*     */     
/*     */     public long hashInt(int input) {
/* 286 */       input = CityAndFarmHash_1_1.NATIVE_CITY.toLittleEndian(input);
/* 287 */       long unsignedInt = Primitives.unsignedInt(input);
/* 288 */       long hash = CityAndFarmHash_1_1.hash4To7Bytes(4L, unsignedInt, unsignedInt);
/* 289 */       return finalize(hash);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 294 */     public long hashShort(short input) { return hashChar((char)input); }
/*     */ 
/*     */     
/* 297 */     private static final int FIRST_SHORT_BYTE_SHIFT = NATIVE_LITTLE_ENDIAN ? 0 : 8;
/*     */     
/* 299 */     private static final int FIRST_SHORT_BYTE_MASK = NATIVE_LITTLE_ENDIAN ? 255 : -1;
/* 300 */     private static final int SECOND_SHORT_BYTE_SHIFT = 8 - FIRST_SHORT_BYTE_SHIFT;
/* 301 */     private static final int SECOND_SHORT_BYTE_MASK = NATIVE_LITTLE_ENDIAN ? -1 : 255;
/*     */ 
/*     */     
/*     */     public long hashChar(char input) {
/* 305 */       int i = input;
/* 306 */       int firstByte = i >> FIRST_SHORT_BYTE_SHIFT & FIRST_SHORT_BYTE_MASK;
/* 307 */       int secondByte = i >> SECOND_SHORT_BYTE_SHIFT & SECOND_SHORT_BYTE_MASK;
/* 308 */       long hash = CityAndFarmHash_1_1.hash1To3Bytes(2, firstByte, secondByte, secondByte);
/* 309 */       return finalize(hash);
/*     */     }
/*     */ 
/*     */     
/*     */     public long hashByte(byte input) {
/* 314 */       int unsignedByte = Primitives.unsignedByte(input);
/* 315 */       long hash = CityAndFarmHash_1_1.hash1To3Bytes(1, unsignedByte, unsignedByte, unsignedByte);
/* 316 */       return finalize(hash);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 321 */     public long hashVoid() { return -7286425919675154353L; }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/*     */       long hash;
/* 327 */       if (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 328 */         hash = INSTANCE.cityHash64(access, input, off, len);
/*     */       } else {
/* 330 */         hash = INSTANCE.cityHash64(access, input, off, len);
/*     */       } 
/* 332 */       return finalize(hash);
/*     */     }
/*     */ 
/*     */     
/* 336 */     long finalize(long hash) { return hash; }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 341 */   public static LongHashFunction asLongHashFunctionWithoutSeed() { return AsLongHashFunction.INSTANCE; }
/*     */   
/*     */   private static class AsLongHashFunctionSeeded
/*     */     extends AsLongHashFunction {
/*     */     private static final long serialVersionUID = 0L;
/*     */     final long seed0;
/*     */     final long seed1;
/*     */     private long voidHash;
/*     */     
/*     */     private AsLongHashFunctionSeeded(long seed0, long seed1) {
/* 351 */       this.seed0 = seed0;
/* 352 */       this.seed1 = seed1;
/* 353 */       this.voidHash = finalize(-7286425919675154353L);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 358 */     public long hashVoid() { return this.voidHash; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 363 */     protected long finalize(long hash) { return CityAndFarmHash_1_1.hashLen16(hash - this.seed0, this.seed1); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 368 */   public static LongHashFunction asLongHashFunctionWithSeed(long seed) { return new AsLongHashFunctionSeeded(-7286425919675154353L, seed, null); }
/*     */ 
/*     */ 
/*     */   
/* 372 */   public static LongHashFunction asLongHashFunctionWithTwoSeeds(long seed0, long seed1) { return new AsLongHashFunctionSeeded(seed0, seed1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> long naHashLen33To64(Access<T> access, T in, long off, long len) {
/* 379 */     long mul = mul(len);
/* 380 */     long a = fetch64(access, in, off) * -7286425919675154353L;
/* 381 */     long b = fetch64(access, in, off + 8L);
/* 382 */     long c = fetch64(access, in, off + len - 8L) * mul;
/* 383 */     long d = fetch64(access, in, off + len - 16L) * -7286425919675154353L;
/* 384 */     long y = Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d;
/* 385 */     long z = hashLen16(y, a + Long.rotateRight(b + -7286425919675154353L, 18) + c, mul);
/* 386 */     long e = fetch64(access, in, off + 16L) * mul;
/* 387 */     long f = fetch64(access, in, off + 24L);
/* 388 */     long g = (y + fetch64(access, in, off + len - 32L)) * mul;
/* 389 */     long h = (z + fetch64(access, in, off + len - 24L)) * mul;
/* 390 */     return hashLen16(Long.rotateRight(e + f, 43) + Long.rotateRight(g, 30) + h, e + 
/* 391 */         Long.rotateRight(f + a, 18) + g, mul);
/*     */   }
/*     */   
/*     */   <T> long naHash64(Access<T> access, T in, long off, long len) {
/* 395 */     long t, c1, z2, b1, a1, c, z1, b, a, seed = 81L;
/* 396 */     if (len <= 32L) {
/* 397 */       if (len <= 16L) {
/* 398 */         return hashLen0To16(access, in, off, len);
/*     */       }
/* 400 */       return hashLen17To32(access, in, off, len);
/*     */     } 
/* 402 */     if (len <= 64L) {
/* 403 */       return naHashLen33To64(access, in, off, len);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 408 */     long x = 81L;
/*     */ 
/*     */ 
/*     */     
/* 412 */     long y = 2480279821605975764L;
/* 413 */     long z = shiftMix(y * -7286425919675154353L + 113L) * -7286425919675154353L;
/* 414 */     long v1 = 0L, v2 = 0L;
/* 415 */     long w1 = 0L, w2 = 0L;
/* 416 */     x = x * -7286425919675154353L + fetch64(access, in, off);
/*     */ 
/*     */     
/* 419 */     long end = off + (len - 1L >> 6) * 64L;
/* 420 */     long last64 = end + (len - 1L & 0x3FL) - 63L;
/*     */     
/*     */     do {
/* 423 */       x = Long.rotateRight(x + y + v1 + fetch64(access, in, off + 8L), 37) * -5435081209227447693L;
/* 424 */       y = Long.rotateRight(y + v2 + fetch64(access, in, off + 48L), 42) * -5435081209227447693L;
/* 425 */       x ^= w2;
/* 426 */       y += v1 + fetch64(access, in, off + 40L);
/* 427 */       z = Long.rotateRight(z + w1, 33) * -5435081209227447693L;
/* 428 */       a = v2 * -5435081209227447693L;
/* 429 */       b = x + w1;
/* 430 */       z1 = fetch64(access, in, off + 24L);
/* 431 */       a += fetch64(access, in, off);
/* 432 */       b = Long.rotateRight(b + a + z1, 21);
/* 433 */       c = a;
/* 434 */       a += fetch64(access, in, off + 8L);
/* 435 */       a += fetch64(access, in, off + 16L);
/* 436 */       b += Long.rotateRight(a, 44);
/* 437 */       v1 = a + z1;
/* 438 */       v2 = b + c;
/* 439 */       a1 = z + w2;
/* 440 */       b1 = y + fetch64(access, in, off + 16L);
/* 441 */       z2 = fetch64(access, in, off + 32L + 24L);
/* 442 */       a1 += fetch64(access, in, off + 32L);
/* 443 */       b1 = Long.rotateRight(b1 + a1 + z2, 21);
/* 444 */       c1 = a1;
/* 445 */       a1 += fetch64(access, in, off + 32L + 8L);
/* 446 */       a1 += fetch64(access, in, off + 32L + 16L);
/* 447 */       b1 += Long.rotateRight(a1, 44);
/* 448 */       w1 = a1 + z2;
/* 449 */       w2 = b1 + c1;
/* 450 */       t = z;
/* 451 */       z = x;
/* 452 */       x = t;
/* 453 */     } while (off += 64L != end);
/*     */     
/* 455 */     off = last64;
/*     */     
/* 457 */     long mul = -5435081209227447693L + ((z & 0xFFL) << true);
/*     */ 
/*     */     
/* 460 */     w1 += (len - 1L & 0x3FL);
/* 461 */     v1 += w1;
/* 462 */     w1 += v1;
/* 463 */     x = Long.rotateRight(x + y + v1 + fetch64(access, in, off + 8L), 37) * mul;
/* 464 */     y = Long.rotateRight(y + v2 + fetch64(access, in, off + 48L), 42) * mul;
/* 465 */     x ^= w2 * 9L;
/* 466 */     y += v1 * 9L + fetch64(access, in, off + 40L);
/* 467 */     z = Long.rotateRight(z + w1, 33) * mul;
/* 468 */     long a = v2 * mul;
/* 469 */     long b = x + w1;
/* 470 */     long z1 = fetch64(access, in, off + 24L);
/* 471 */     a += fetch64(access, in, off);
/* 472 */     b = Long.rotateRight(b + a + z1, 21);
/* 473 */     long c = a;
/* 474 */     a += fetch64(access, in, off + 8L);
/* 475 */     a += fetch64(access, in, off + 16L);
/* 476 */     b += Long.rotateRight(a, 44);
/* 477 */     v1 = a + z1;
/* 478 */     v2 = b + c;
/* 479 */     long a1 = z + w2;
/* 480 */     long b1 = y + fetch64(access, in, off + 16L);
/* 481 */     long z2 = fetch64(access, in, off + 32L + 24L);
/* 482 */     a1 += fetch64(access, in, off + 32L);
/* 483 */     b1 = Long.rotateRight(b1 + a1 + z2, 21);
/* 484 */     long c1 = a1;
/* 485 */     a1 += fetch64(access, in, off + 32L + 8L);
/* 486 */     a1 += fetch64(access, in, off + 32L + 16L);
/* 487 */     b1 += Long.rotateRight(a1, 44);
/* 488 */     w1 = a1 + z2;
/* 489 */     w2 = b1 + c1;
/* 490 */     long t = z;
/* 491 */     z = x;
/* 492 */     x = t;
/* 493 */     return hashLen16(hashLen16(v1, w1, mul) + shiftMix(y) * -4348849565147123417L + z, 
/* 494 */         hashLen16(v2, w2, mul) + x, mul);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 499 */   <T> long naHash64WithSeeds(Access<T> access, T in, long off, long len, long seed0, long seed1) { return hashLen16(naHash64(access, in, off, len) - seed0, seed1); }
/*     */ 
/*     */   
/*     */   long uoH(long x, long y, long mul, int r) {
/* 503 */     long a = (x ^ y) * mul;
/* 504 */     a = shiftMix(a);
/* 505 */     long b = (y ^ a) * mul;
/* 506 */     return Long.rotateRight(b, r) * mul;
/*     */   }
/*     */   <T> long uoHash64WithSeeds(Access<T> access, T in, long off, long len, long seed0, long seed1) {
/*     */     long t, a7, a6, a5, a4, a3, a2, a1, a0;
/* 510 */     if (len <= 64L) {
/* 511 */       return naHash64WithSeeds(access, in, off, len, seed0, seed1);
/*     */     }
/*     */     
/* 514 */     long x = seed0;
/* 515 */     long y = seed1 * -7286425919675154353L + 113L;
/* 516 */     long z = shiftMix(y * -7286425919675154353L) * -7286425919675154353L;
/* 517 */     long v0 = seed0;
/* 518 */     long v1 = seed1;
/* 519 */     long w0 = 0L;
/* 520 */     long w1 = 0L;
/* 521 */     long u = x - z;
/* 522 */     x *= -7286425919675154353L;
/* 523 */     long mul = -7286425919675154353L + (u & 0x82L);
/*     */     
/* 525 */     long end = off + (len - 1L >> 6) * 64L;
/* 526 */     long last64 = end + (len - 1L & 0x3FL) - 63L;
/*     */     
/*     */     do {
/* 529 */       a0 = fetch64(access, in, off);
/* 530 */       a1 = fetch64(access, in, off + 8L);
/* 531 */       a2 = fetch64(access, in, off + 16L);
/* 532 */       a3 = fetch64(access, in, off + 24L);
/* 533 */       a4 = fetch64(access, in, off + 32L);
/* 534 */       a5 = fetch64(access, in, off + 40L);
/* 535 */       a6 = fetch64(access, in, off + 48L);
/* 536 */       a7 = fetch64(access, in, off + 56L);
/* 537 */       x += a0 + a1;
/* 538 */       y += a2;
/* 539 */       z += a3;
/* 540 */       v0 += a4;
/* 541 */       v1 += a5 + a1;
/* 542 */       w0 += a6;
/* 543 */       w1 += a7;
/*     */       
/* 545 */       x = Long.rotateRight(x, 26);
/* 546 */       x *= 9L;
/* 547 */       y = Long.rotateRight(y, 29);
/* 548 */       z *= mul;
/* 549 */       v0 = Long.rotateRight(v0, 33);
/* 550 */       v1 = Long.rotateRight(v1, 30);
/* 551 */       w0 ^= x;
/* 552 */       w0 *= 9L;
/* 553 */       z = Long.rotateRight(z, 32);
/* 554 */       z += w1;
/* 555 */       w1 += z;
/* 556 */       z *= 9L;
/*     */       
/* 558 */       t = u;
/* 559 */       u = y;
/* 560 */       y = t;
/*     */       
/* 562 */       z += a0 + a6;
/* 563 */       v0 += a2;
/* 564 */       v1 += a3;
/* 565 */       w0 += a4;
/* 566 */       w1 += a5 + a6;
/* 567 */       x += a1;
/* 568 */       y += a7;
/*     */       
/* 570 */       y += v0;
/* 571 */       v0 += x - y;
/* 572 */       v1 += w0;
/* 573 */       w0 += v1;
/* 574 */       w1 += x - y;
/* 575 */       x += w1;
/* 576 */       w1 = Long.rotateRight(w1, 34);
/*     */       
/* 578 */       t = u;
/* 579 */       u = z;
/* 580 */       z = t;
/*     */     }
/* 582 */     while (off += 64L != end);
/*     */     
/* 584 */     off = last64;
/*     */     
/* 586 */     u *= 9L;
/* 587 */     v1 = Long.rotateRight(v1, 28);
/* 588 */     v0 = Long.rotateRight(v0, 20);
/* 589 */     w0 += (len - 1L & 0x3FL);
/* 590 */     u += y;
/* 591 */     y += u;
/* 592 */     x = Long.rotateRight(y - x + v0 + fetch64(access, in, off + 8L), 37) * mul;
/* 593 */     y = Long.rotateRight(y ^ v1 ^ fetch64(access, in, off + 48L), 42) * mul;
/* 594 */     x ^= w1 * 9L;
/* 595 */     y += v0 + fetch64(access, in, off + 40L);
/* 596 */     z = Long.rotateRight(z + w0, 33) * mul;
/*     */     
/* 598 */     long a = v1 * mul;
/* 599 */     long b = x + w0;
/* 600 */     long z1 = fetch64(access, in, off + 24L);
/* 601 */     a += fetch64(access, in, off);
/* 602 */     b = Long.rotateRight(b + a + z1, 21);
/* 603 */     long c = a;
/* 604 */     a += fetch64(access, in, off + 8L);
/* 605 */     a += fetch64(access, in, off + 16L);
/* 606 */     b += Long.rotateRight(a, 44);
/* 607 */     v0 = a + z1;
/* 608 */     v1 = b + c;
/*     */     
/* 610 */     long a1 = z + w1;
/* 611 */     long b1 = y + fetch64(access, in, off + 16L);
/* 612 */     long z2 = fetch64(access, in, off + 32L + 24L);
/* 613 */     a1 += fetch64(access, in, off + 32L);
/* 614 */     b1 = Long.rotateRight(b1 + a1 + z2, 21);
/* 615 */     long c1 = a1;
/* 616 */     a1 += fetch64(access, in, off + 32L + 8L);
/* 617 */     a1 += fetch64(access, in, off + 32L + 16L);
/* 618 */     b1 += Long.rotateRight(a1, 44);
/* 619 */     w0 = a1 + z2;
/* 620 */     w1 = b1 + c1;
/* 621 */     return uoH(hashLen16(v0 + x, w0 ^ y, mul) + z - u, 
/* 622 */         uoH(v1 + y, w1 + z, -7286425919675154353L, 30) ^ x, -7286425919675154353L, 31);
/*     */   }
/*     */   
/*     */   private static class Na extends AsLongHashFunction {
/*     */     private Na() {}
/*     */     
/* 628 */     public static final Na INSTANCE = new Na();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 632 */     private Object readResolve() { return INSTANCE; }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/*     */       long hash;
/* 638 */       if (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 639 */         hash = INSTANCE.naHash64(access, input, off, len);
/*     */       } else {
/* 641 */         hash = INSTANCE.naHash64(access, input, off, len);
/*     */       } 
/* 643 */       return finalize(hash);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 648 */   public static LongHashFunction naWithoutSeeds() { return Na.INSTANCE; }
/*     */   
/*     */   private static class NaSeeded extends Na {
/*     */     private static final long serialVersionUID = 0L;
/*     */     final long seed0;
/*     */     final long seed1;
/*     */     private long voidHash;
/*     */     
/*     */     private NaSeeded(long seed0, long seed1) {
/* 657 */       super(null);
/* 658 */       this.seed0 = seed0;
/* 659 */       this.seed1 = seed1;
/* 660 */       this.voidHash = finalize(-7286425919675154353L);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 665 */     public long hashVoid() { return this.voidHash; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 670 */     protected long finalize(long hash) { return CityAndFarmHash_1_1.hashLen16(hash - this.seed0, this.seed1); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 675 */   public static LongHashFunction naWithSeed(long seed) { return new NaSeeded(-7286425919675154353L, seed, null); }
/*     */ 
/*     */ 
/*     */   
/* 679 */   public static LongHashFunction naWithSeeds(long seed0, long seed1) { return new NaSeeded(seed0, seed1, null); }
/*     */   
/*     */   private static final class Uo
/*     */     extends AsLongHashFunction {
/* 683 */     public static final Uo INSTANCE = new Uo();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 687 */     private Object readResolve() { return INSTANCE; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/* 693 */       CityAndFarmHash_1_1 instance = (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) ? INSTANCE : INSTANCE;
/* 694 */       if (len <= 64L) {
/* 695 */         return instance.naHash64(access, input, off, len);
/*     */       }
/* 697 */       return instance.uoHash64WithSeeds(access, input, off, len, 81L, 0L);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 702 */   public static LongHashFunction uoWithoutSeeds() { return Uo.INSTANCE; }
/*     */   
/*     */   private static final class UoWithOneSeed
/*     */     extends AsLongHashFunctionSeeded
/*     */   {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 709 */     private UoWithOneSeed(long seed) { super(-7286425919675154353L, seed, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/* 715 */       CityAndFarmHash_1_1 instance = (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) ? INSTANCE : INSTANCE;
/* 716 */       if (len <= 64L) {
/* 717 */         return finalize(instance.naHash64(access, input, off, len));
/*     */       }
/* 719 */       return instance.uoHash64WithSeeds(access, input, off, len, 0L, this.seed1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 724 */   public static LongHashFunction uoWithSeed(long seed) { return new UoWithOneSeed(seed, null); }
/*     */   
/*     */   private static class UoSeeded
/*     */     extends AsLongHashFunctionSeeded
/*     */   {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/* 731 */     private UoSeeded(long seed0, long seed1) { super(seed0, seed1, null); }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> long hash(T input, Access<T> access, long off, long len) {
/* 736 */       if (access.byteOrder(input) == ByteOrder.LITTLE_ENDIAN) {
/* 737 */         return INSTANCE.uoHash64WithSeeds(access, input, off, len, this.seed0, this.seed1);
/*     */       }
/*     */       
/* 740 */       return INSTANCE.uoHash64WithSeeds(access, input, off, len, this.seed0, this.seed1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 746 */   public static LongHashFunction uoWithSeeds(long seed0, long seed1) { return new UoSeeded(seed0, seed1, null); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\CityAndFarmHash_1_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */