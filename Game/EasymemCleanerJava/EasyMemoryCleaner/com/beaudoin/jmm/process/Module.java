/*     */ package com.beaudoin.jmm.process;
/*     */ 
/*     */ import com.beaudoin.jmm.misc.Cacheable;
/*     */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*     */ import com.beaudoin.jmm.natives.win32.Kernel32;
/*     */ import com.beaudoin.jmm.process.impl.win32.Win32Process;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.platform.win32.Win32Exception;
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
/*     */ public final class Module
/*     */   implements ReadableRegion
/*     */ {
/*     */   private final NativeProcess process;
/*     */   private final String name;
/*     */   private final long address;
/*     */   private final int size;
/*     */   private final Pointer pointer;
/*     */   private MemoryBuffer data;
/*     */   
/*     */   public Module(NativeProcess process, String name, Pointer pointer, long size) {
/*  46 */     this.process = process;
/*  47 */     this.name = name;
/*  48 */     this.address = Pointer.nativeValue(pointer);
/*  49 */     this.size = (int)size;
/*  50 */     this.pointer = pointer;
/*     */   }
/*     */ 
/*     */   
/*  54 */   public NativeProcess process() { return this.process; }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public Pointer pointer() { return this.pointer; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public String name() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public int size() { return this.size; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public long address() { return this.address; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public MemoryBuffer data() { return data(false); }
/*     */ 
/*     */   
/*     */   public MemoryBuffer data(boolean forceNew) {
/*  78 */     if (forceNew || this.data == null) {
/*  79 */       this.data = process().read(pointer(), size());
/*     */     }
/*  81 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public MemoryBuffer read(Pointer offset, int size) {
/*  86 */     MemoryBuffer buffer = Cacheable.buffer(size);
/*  87 */     if (Kernel32.ReadProcessMemory(((Win32Process)process()).pointer(), Cacheable.pointer(address() + Pointer.nativeValue(offset)), buffer, size, 0) == 0L) {
/*  88 */       throw new Win32Exception(Native.getLastError());
/*     */     }
/*  90 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public NativeProcess write(Pointer offset, MemoryBuffer buffer) {
/*  95 */     if (Kernel32.WriteProcessMemory(((Win32Process)process()).pointer(), Cacheable.pointer(address() + Pointer.nativeValue(offset)), buffer, buffer.size(), 0) == 0L) {
/*  96 */       throw new Win32Exception(Native.getLastError());
/*     */     }
/*  98 */     return process();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public boolean canRead(Pointer offset, int size) { return (Kernel32.ReadProcessMemory(((Win32Process)process()).pointer(), Cacheable.pointer(address() + Pointer.nativeValue(offset)), Cacheable.buffer(size), size, 0) != 0L); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */