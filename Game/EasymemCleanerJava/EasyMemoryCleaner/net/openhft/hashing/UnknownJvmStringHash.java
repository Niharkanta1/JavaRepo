/*    */ package net.openhft.hashing;
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
/*    */ static enum UnknownJvmStringHash
/*    */   implements StringHash
/*    */ {
/* 20 */   INSTANCE;
/*    */ 
/*    */ 
/*    */   
/* 24 */   public long longHash(String s, LongHashFunction hashFunction, int off, int len) { return hashFunction.hashNativeChars(s, off, len); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\UnknownJvmStringHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */