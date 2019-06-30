/*    */ package com.sun.jna;
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
/*    */ public class StructureWriteContext
/*    */   extends ToNativeContext
/*    */ {
/*    */   private Structure struct;
/*    */   private Field field;
/*    */   
/*    */   StructureWriteContext(Structure struct, Field field) {
/* 25 */     this.struct = struct;
/* 26 */     this.field = field;
/*    */   }
/*    */   
/* 29 */   public Structure getStructure() { return this.struct; }
/*    */ 
/*    */   
/* 32 */   public Field getField() { return this.field; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\StructureWriteContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */