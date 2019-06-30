/*    */ package me.lemon.lemonware.memory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Memory
/*    */ {
/*    */   public List<Offset> offsets;
/*    */   
/* 15 */   public List<Offset> getOffsets() { return this.offsets; } public void setOffsets(List<Offset> offsets) { this.offsets = offsets; }
/*    */   
/*    */   public Memory() {
/* 18 */     this.offsets = new ArrayList();
/* 19 */     addOffsets();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addOffsets() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Offset getOffset(String name) {
/* 34 */     for (Offset o : getOffsets()) {
/* 35 */       if (o.getName().equalsIgnoreCase(name)) {
/* 36 */         return o;
/*    */       }
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\memory\Memory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */