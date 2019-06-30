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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Primitives
/*    */ {
/* 24 */   static long unsignedInt(int i) { return i & 0xFFFFFFFFL; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   static int unsignedShort(int s) { return s & 0xFFFF; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   static int unsignedByte(int b) { return b & 0xFF; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\openhft\hashing\Primitives.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */