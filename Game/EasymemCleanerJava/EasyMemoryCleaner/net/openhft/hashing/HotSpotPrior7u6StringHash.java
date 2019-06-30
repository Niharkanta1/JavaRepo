/*    */ package net.openhft.hashing;
/*    */ 
/*    */ import java.lang.reflect.Field;
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
/*    */ static enum HotSpotPrior7u6StringHash
/*    */   implements StringHash
/*    */ {
/* 22 */   INSTANCE;
/*    */   
/*    */   private static final long offsetOffset;
/*    */   private static final long valueOffset;
/*    */   
/*    */   static  {
/*    */     try {
/* 29 */       valueField = String.class.getDeclaredField("value");
/* 30 */       valueOffset = UnsafeAccess.UNSAFE.objectFieldOffset(valueField);
/*    */       
/* 32 */       Field offsetField = String.class.getDeclaredField("offset");
/* 33 */       offsetOffset = UnsafeAccess.UNSAFE.objectFieldOffset(offsetField);
/* 34 */     } catch (NoSuchFieldException e) {
/* 35 */       throw new AssertionError(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public long longHash(String s, LongHashFunction hashFunction, int off, int len) {
/* 41 */     char[] value = (char[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
/* 42 */     int offset = UnsafeAccess.UNSAFE.getInt(s, offsetOffset);
/* 43 */     return hashFunction.hashChars(value, offset + off, len);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\HotSpotPrior7u6StringHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */