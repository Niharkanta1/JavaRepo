/*     */ package com.beaudoin.jmm.process.impl.unix;
/*     */ 
/*     */ import com.beaudoin.jmm.misc.Cacheable;
/*     */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*     */ import com.beaudoin.jmm.natives.unix.unix;
/*     */ import com.beaudoin.jmm.process.Module;
/*     */ import com.beaudoin.jmm.process.NativeProcess;
/*     */ import com.sun.jna.Pointer;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.HashMap;
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
/*     */ public final class UnixProcess
/*     */   implements NativeProcess
/*     */ {
/*     */   private final int id;
/*     */   private unix.iovec local;
/*     */   private unix.iovec remote;
/*     */   private Map<String, Module> modules;
/*     */   
/*     */   public UnixProcess(int id) {
/*  46 */     this.local = new unix.iovec();
/*  47 */     this.remote = new unix.iovec();
/*  48 */     this.modules = new HashMap();
/*     */ 
/*     */     
/*  51 */     this.id = id;
/*  52 */     initModules();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public int id() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initModules() {
/*     */     try {
/*  63 */       for (String line : Files.readAllLines(Paths.get("/proc/" + id() + "/maps", new String[0]))) {
/*  64 */         String[] split = line.split(" ");
/*  65 */         String[] regionSplit = split[0].split("-");
/*     */         
/*  67 */         long start = Long.parseLong(regionSplit[0], 16);
/*  68 */         long end = Long.parseLong(regionSplit[1], 16);
/*  69 */         long offset = Long.parseLong(split[2], 16);
/*  70 */         if (offset <= 0L) {
/*     */           continue;
/*     */         }
/*  73 */         String path = "";
/*  74 */         for (int i = 5; i < split.length; i++) {
/*  75 */           String s = split[i].trim();
/*  76 */           if (!s.isEmpty()) {
/*  77 */             path = path + split[i];
/*     */           }
/*  79 */           if (s.isEmpty() && ++i > split.length)
/*     */             break; 
/*  81 */           if (s.isEmpty() && !split[i].trim().isEmpty()) {
/*  82 */             path = path + split[i];
/*     */           }
/*     */         } 
/*  85 */         String modulename = path.substring(path.lastIndexOf("/") + 1, path.length());
/*  86 */         this.modules.put(modulename, new Module(this, modulename, Pointer.createConstant(start), end - start));
/*     */       } 
/*  88 */     } catch (IOException e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public Module findModule(String moduleName) { return (Module)this.modules.get(moduleName); }
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryBuffer read(Pointer address, int size) {
/* 100 */     MemoryBuffer buffer = Cacheable.buffer(size);
/* 101 */     this.local.iov_base = buffer;
/* 102 */     this.remote.iov_base = address;
/* 103 */     this.local.iov_len = size;
/* 104 */     if (unix.process_vm_readv(this.id, this.local, 1L, this.remote, 1L, 0L) != size) {
/* 105 */       throw new RuntimeException("Read memory failed at address " + Pointer.nativeValue(address) + " size " + size);
/*     */     }
/* 107 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public NativeProcess write(Pointer address, MemoryBuffer buffer) {
/* 112 */     this.local.iov_base = buffer;
/* 113 */     this.remote.iov_base = address;
/* 114 */     this.local.iov_len = buffer.size();
/* 115 */     if (unix.process_vm_writev(this.id, this.local, 1L, this.remote, 1L, 0L) != buffer.size()) {
/* 116 */       throw new RuntimeException("Write memory failed at address " + Pointer.nativeValue(address) + " size " + buffer.size());
/*     */     }
/* 118 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canRead(Pointer address, int size) {
/*     */     try {
/* 124 */       read(address, size);
/* 125 */       return true;
/* 126 */     } catch (Exception e) {
/* 127 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\imp\\unix\UnixProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */