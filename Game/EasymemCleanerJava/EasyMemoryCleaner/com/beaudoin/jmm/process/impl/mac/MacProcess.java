/*    */ package com.beaudoin.jmm.process.impl.mac;
/*    */ 
/*    */ import com.beaudoin.jmm.misc.Cacheable;
/*    */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*    */ import com.beaudoin.jmm.natives.mac.mac;
/*    */ import com.beaudoin.jmm.process.Module;
/*    */ import com.beaudoin.jmm.process.NativeProcess;
/*    */ import com.sun.jna.Pointer;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MacProcess
/*    */   implements NativeProcess
/*    */ {
/*    */   private final int id;
/*    */   private final int task;
/*    */   private Map<String, Module> modules;
/*    */   
/*    */   public MacProcess(int id, int mach_task) {
/* 47 */     this.modules = new HashMap();
/*    */ 
/*    */     
/* 50 */     this.id = id;
/* 51 */     this.task = mach_task;
/* 52 */     initModules();
/*    */   }
/*    */ 
/*    */   
/* 56 */   public int task() { return this.task; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public int id() { return this.id; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initModules() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 72 */   public Module findModule(String moduleName) { return null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public MemoryBuffer read(Pointer address, int size) {
/* 77 */     MemoryBuffer buffer = Cacheable.buffer(size);
/* 78 */     if (mac.vm_read(task(), address, size, buffer, Cacheable.INT_BY_REF) != 0 || Cacheable.INT_BY_REF.getValue() != size) {
/* 79 */       throw new RuntimeException("Read memory failed at address " + Pointer.nativeValue(address) + " size " + size);
/*    */     }
/* 81 */     Pointer.nativeValue(buffer, Pointer.nativeValue(buffer.getPointer(0L)));
/* 82 */     return buffer;
/*    */   }
/*    */ 
/*    */   
/*    */   public NativeProcess write(Pointer address, MemoryBuffer buffer) {
/* 87 */     if (mac.vm_write(task(), address, buffer, buffer.size()) != 0) {
/* 88 */       throw new RuntimeException("Write memory failed at address " + Pointer.nativeValue(address) + " size " + buffer.size());
/*    */     }
/* 90 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRead(Pointer address, int size) {
/*    */     try {
/* 96 */       read(address, size);
/* 97 */       return true;
/* 98 */     } catch (Exception e) {
/* 99 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\impl\mac\MacProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */