/*    */ package me.lemon.lemonware.memory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Offset
/*    */ {
/*    */   public String name;
/*    */   private int address;
/*    */   
/* 13 */   public String getName() { return this.name; } public void setName(String name) { this.name = name; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public int getAddress() { return this.address; } public void setAddress(int address) { this.address = address; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Offset(String name, int offset) {
/* 26 */     this.name = name;
/* 27 */     this.address = offset;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\memory\Offset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */