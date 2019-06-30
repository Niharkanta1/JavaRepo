/*    */ package me.lemon.lemonware.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public static enum EnumBone
/*    */ {
/*  9 */   HEAD(
/* 10 */     6),
/* 11 */   PELVIS(
/* 12 */     false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int bone;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   EnumBone(int boneid) { this.bone = boneid; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int getBoneID() { return this.bone; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonwar\\utils\EnumBone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */