/*     */ package com.beaudoin.jmm.misc;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
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
/*     */ public final class MemoryBuffer
/*     */   extends Pointer
/*     */ {
/*     */   private int size;
/*     */   
/*     */   public MemoryBuffer(int size) {
/*  38 */     super(Native.malloc(size));
/*  39 */     this.size = size;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putBoolean(boolean value) {
/*  43 */     setByte(0L, (byte)(value ? 1 : 0));
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putByte(int value) {
/*  48 */     setByte(0L, (byte)value);
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putShort(int value) {
/*  53 */     setShort(0L, (short)value);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putInt(int value) {
/*  58 */     setInt(0L, value);
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putLong(long value) {
/*  63 */     setLong(0L, value);
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putFloat(float value) {
/*  68 */     setFloat(0L, value);
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public MemoryBuffer putDouble(double value) {
/*  73 */     setDouble(0L, value);
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */   
/*  78 */   public void get(byte[] dest) { read(0L, dest, 0, dest.length); }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public boolean getBoolean() { return (getByte() == 1); }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int getByte() { return getByte(0L); }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getShort() { return getShort(0L); }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int getInt() { return getInt(0L); }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public long getLong() { return getLong(0L); }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public float getFloat() { return getFloat(0L); }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public double getDouble() { return getDouble(0L); }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public int size() { return this.size; }
/*     */ 
/*     */   
/*     */   public byte[] array() {
/* 114 */     byte[] data = Cacheable.array(this.size);
/* 115 */     get(data);
/* 116 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\misc\MemoryBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */