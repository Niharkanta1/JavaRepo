/*     */ package net.openhft.hashing;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import sun.nio.ch.DirectBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LongHashFunction
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*  72 */   static final boolean NATIVE_LITTLE_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
/*  73 */   static final byte TRUE_BYTE_VALUE = UnsafeAccess.UNSAFE.getByte(new boolean[] { true }, UnsafeAccess.BOOLEAN_BASE);
/*  74 */   static final byte FALSE_BYTE_VALUE = UnsafeAccess.UNSAFE.getByte(new boolean[] { false }, UnsafeAccess.BOOLEAN_BASE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static StringHash stringHash;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static LongHashFunction city_1_1() { return CityAndFarmHash_1_1.asLongHashFunctionWithoutSeed(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static LongHashFunction city_1_1(long seed) { return CityAndFarmHash_1_1.asLongHashFunctionWithSeed(seed); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static LongHashFunction city_1_1(long seed0, long seed1) { return CityAndFarmHash_1_1.asLongHashFunctionWithTwoSeeds(seed0, seed1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public static LongHashFunction farmNa() { return CityAndFarmHash_1_1.naWithoutSeeds(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static LongHashFunction farmNa(long seed) { return CityAndFarmHash_1_1.naWithSeed(seed); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static LongHashFunction farmNa(long seed0, long seed1) { return CityAndFarmHash_1_1.naWithSeeds(seed0, seed1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public static LongHashFunction farmUo() { return CityAndFarmHash_1_1.uoWithoutSeeds(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public static LongHashFunction farmUo(long seed) { return CityAndFarmHash_1_1.uoWithSeed(seed); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public static LongHashFunction farmUo(long seed0, long seed1) { return CityAndFarmHash_1_1.uoWithSeeds(seed0, seed1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public static LongHashFunction murmur_3() { return MurmurHash_3.asLongHashFunctionWithoutSeed(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public static LongHashFunction murmur_3(long seed) { return MurmurHash_3.asLongHashFunctionWithSeed(seed); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public static LongHashFunction xx_r39() { return XxHash_r39.asLongHashFunctionWithoutSeed(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public static LongHashFunction xx_r39(long seed) { return XxHash_r39.asLongHashFunctionWithSeed(seed); }
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/*     */     try {
/* 278 */       if (System.getProperty("java.vm.name").contains("HotSpot")) {
/* 279 */         javaVersion = System.getProperty("java.version");
/* 280 */         if (javaVersion.compareTo("1.7.0_06") >= 0) {
/* 281 */           if (javaVersion.compareTo("1.9") >= 0) {
/* 282 */             stringHash = UnknownJvmStringHash.INSTANCE;
/*     */           } else {
/* 284 */             stringHash = ModernHotSpotStringHash.INSTANCE;
/*     */           } 
/*     */         } else {
/* 287 */           stringHash = HotSpotPrior7u6StringHash.INSTANCE;
/*     */         } 
/*     */       } else {
/*     */         
/* 291 */         stringHash = HotSpotPrior7u6StringHash.INSTANCE;
/*     */       } 
/* 293 */     } catch (Throwable javaVersion) {
/*     */     
/*     */     } finally {
/* 296 */       if (stringHash == null)
/* 297 */         stringHash = UnknownJvmStringHash.INSTANCE; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkArrayOffs(int arrayLength, int off, int len) {
/* 302 */     if (len < 0 || off < 0 || off + len > arrayLength || off + len < 0) {
/* 303 */       throw new IndexOutOfBoundsException();
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   private long unsafeHash(Object input, long off, long len) { return hash(input, UnsafeAccess.INSTANCE, off, len); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public long hashBoolean(boolean input) { return hashByte(input ? TRUE_BYTE_VALUE : FALSE_BYTE_VALUE); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public long hashBooleans(@NotNull boolean[] input) { return unsafeHash(input, UnsafeAccess.BOOLEAN_BASE, input.length); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashBooleans(@NotNull boolean[] input, int off, int len) {
/* 418 */     checkArrayOffs(input.length, off, len);
/* 419 */     return unsafeHash(input, UnsafeAccess.BOOLEAN_BASE + off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public long hashBytes(@NotNull byte[] input) { return unsafeHash(input, UnsafeAccess.BYTE_BASE, input.length); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashBytes(@NotNull byte[] input, int off, int len) {
/* 443 */     checkArrayOffs(input.length, off, len);
/* 444 */     return unsafeHash(input, UnsafeAccess.BYTE_BASE + off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public long hashBytes(ByteBuffer input) { return hashByteBuffer(input, input.position(), input.remaining()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashBytes(@NotNull ByteBuffer input, int off, int len) {
/* 472 */     checkArrayOffs(input.capacity(), off, len);
/* 473 */     return hashByteBuffer(input, off, len);
/*     */   }
/*     */   
/*     */   private long hashByteBuffer(@NotNull ByteBuffer input, int off, int len) {
/* 477 */     if (input.hasArray())
/* 478 */       return unsafeHash(input.array(), UnsafeAccess.BYTE_BASE + input.arrayOffset() + off, len); 
/* 479 */     if (input instanceof DirectBuffer) {
/* 480 */       return unsafeHash(null, ((DirectBuffer)input).address() + off, len);
/*     */     }
/* 482 */     return hash(input, ByteBufferAccess.INSTANCE, off, len);
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
/* 497 */   public long hashMemory(long address, long len) { return unsafeHash(null, address, len); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public long hashChars(@NotNull char[] input) { return unsafeHash(input, UnsafeAccess.CHAR_BASE, input.length * 2L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashChars(@NotNull char[] input, int off, int len) {
/* 523 */     checkArrayOffs(input.length, off, len);
/* 524 */     return unsafeHash(input, UnsafeAccess.CHAR_BASE + off * 2L, len * 2L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 531 */   public long hashChars(@NotNull String input) { return stringHash.longHash(input, this, 0, input.length()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashChars(@NotNull String input, int off, int len) {
/* 550 */     checkArrayOffs(input.length(), off, len);
/* 551 */     return stringHash.longHash(input, this, off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 558 */   public long hashChars(@NotNull StringBuilder input) { return hashNativeChars(input); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashChars(@NotNull StringBuilder input, int off, int len) {
/* 577 */     checkArrayOffs(input.length(), off, len);
/* 578 */     return hashNativeChars(input, off, len);
/*     */   }
/*     */ 
/*     */   
/* 582 */   long hashNativeChars(CharSequence input) { return hashNativeChars(input, 0, input.length()); }
/*     */ 
/*     */ 
/*     */   
/* 586 */   long hashNativeChars(CharSequence input, int off, int len) { return hash(input, CharSequenceAccess.nativeCharSequenceAccess(), off * 2L, len * 2L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 593 */   public long hashShorts(@NotNull short[] input) { return unsafeHash(input, UnsafeAccess.SHORT_BASE, input.length * 2L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashShorts(@NotNull short[] input, int off, int len) {
/* 612 */     checkArrayOffs(input.length, off, len);
/* 613 */     return unsafeHash(input, UnsafeAccess.SHORT_BASE + off * 2L, len * 2L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public long hashInts(@NotNull int[] input) { return unsafeHash(input, UnsafeAccess.INT_BASE, input.length * 4L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashInts(@NotNull int[] input, int off, int len) {
/* 639 */     checkArrayOffs(input.length, off, len);
/* 640 */     return unsafeHash(input, UnsafeAccess.INT_BASE + off * 4L, len * 4L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 647 */   public long hashLongs(@NotNull long[] input) { return unsafeHash(input, UnsafeAccess.LONG_BASE, input.length * 8L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long hashLongs(@NotNull long[] input, int off, int len) {
/* 666 */     checkArrayOffs(input.length, off, len);
/* 667 */     return unsafeHash(input, UnsafeAccess.LONG_BASE + off * 8L, len * 8L);
/*     */   }
/*     */   
/*     */   public abstract long hashLong(long paramLong);
/*     */   
/*     */   public abstract long hashInt(int paramInt);
/*     */   
/*     */   public abstract long hashShort(short paramShort);
/*     */   
/*     */   public abstract long hashChar(char paramChar);
/*     */   
/*     */   public abstract long hashByte(byte paramByte);
/*     */   
/*     */   public abstract long hashVoid();
/*     */   
/*     */   public abstract <T> long hash(T paramT, Access<T> paramAccess, long paramLong1, long paramLong2);
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\LongHashFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */