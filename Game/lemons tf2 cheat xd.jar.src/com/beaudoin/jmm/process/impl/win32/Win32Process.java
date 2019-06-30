/*     */ package com.beaudoin.jmm.process.impl.win32;
/*     */ 
/*     */ import com.beaudoin.jmm.misc.Cacheable;
/*     */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*     */ import com.beaudoin.jmm.natives.win32.Kernel32;
/*     */ import com.beaudoin.jmm.natives.win32.Psapi;
/*     */ import com.beaudoin.jmm.process.Module;
/*     */ import com.beaudoin.jmm.process.NativeProcess;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.platform.win32.Win32Exception;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Win32Process
/*     */   implements NativeProcess
/*     */ {
/*     */   private final int id;
/*     */   private final Pointer handle;
/*     */   private Map<String, Module> modules;
/*     */   
/*     */   public Win32Process(int id, Pointer handle) {
/*  49 */     this.id = id;
/*  50 */     this.handle = handle;
/*  51 */     initModules();
/*     */   }
/*     */ 
/*     */   
/*  55 */   public Pointer pointer() { return this.handle; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public int id() { return this.id; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void initModules() { this.modules = Psapi.getModules(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Module findModule(String moduleName) {
/*  70 */     Module module = (Module)this.modules.get(moduleName);
/*  71 */     if (module == null) {
/*  72 */       int attempts = 60;
/*  73 */       for (; attempts-- > 0 && module == null; initModules()) {
/*  74 */         module = (Module)this.modules.get(moduleName);
/*     */         try {
/*  76 */           Thread.sleep(1000L);
/*  77 */         } catch (InterruptedException e) {
/*  78 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*  81 */       if (module == null) {
/*  82 */         throw new RuntimeException(moduleName + " was not found!");
/*     */       }
/*     */     } 
/*  85 */     return (Module)this.modules.get(moduleName);
/*     */   }
/*     */ 
/*     */   
/*     */   public MemoryBuffer read(Pointer address, int size) {
/*  90 */     MemoryBuffer buffer = Cacheable.buffer(size);
/*  91 */     if (Kernel32.ReadProcessMemory(pointer(), address, buffer, size, 0) == 0L) {
/*  92 */       throw new Win32Exception(Native.getLastError());
/*     */     }
/*  94 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public NativeProcess write(Pointer address, MemoryBuffer buffer) {
/*  99 */     if (Kernel32.WriteProcessMemory(pointer(), address, buffer, buffer.size(), 0) == 0L) {
/* 100 */       throw new Win32Exception(Native.getLastError());
/*     */     }
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean canRead(Pointer address, int size) { return (Kernel32.ReadProcessMemory(pointer(), address, Cacheable.buffer(size), size, 0) != 0L); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\impl\win32\Win32Process.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */