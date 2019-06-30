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
/*    */ static enum ModernHotSpotStringHash
/*    */   implements StringHash
/*    */ {
/* 22 */   INSTANCE;
/*    */   
/*    */   private static final long valueOffset;
/*    */   
/*    */   static  {
/*    */     try {
/* 28 */       valueField = String.class.getDeclaredField("value");
/* 29 */       valueOffset = UnsafeAccess.UNSAFE.objectFieldOffset(valueField);
/* 30 */     } catch (NoSuchFieldException e) {
/* 31 */       throw new AssertionError(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public long longHash(String s, LongHashFunction hashFunction, int off, int len) {
/* 37 */     char[] value = (char[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
/* 38 */     return hashFunction.hashChars(value, off, len);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\ModernHotSpotStringHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */