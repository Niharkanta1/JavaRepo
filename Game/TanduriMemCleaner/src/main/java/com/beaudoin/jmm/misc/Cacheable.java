/*    */ package com.beaudoin.jmm.misc;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
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
/*    */ public final class Cacheable
/*    */ {
/* 36 */   private static final Map<Integer, MemoryBuffer> bufferCache = new HashMap();
/* 37 */   private static final Function<Integer, MemoryBuffer> butterCreate = MemoryBuffer::new;
/*    */   
/* 39 */   private static final Map<Integer, byte[]> arrayCache = new HashMap();
/* 40 */   private static final Function<Integer, byte[]> arrayCreate = x$0 -> new byte[x$0];
/*    */   
/* 42 */   private static final Pointer cachedPointer = new Pointer(0L);
/* 43 */   public static final IntByReference INT_BY_REF = new IntByReference();
/*    */ 
/*    */   
/* 46 */   public static MemoryBuffer buffer(int size) { return (MemoryBuffer)bufferCache.computeIfAbsent(Integer.valueOf(size), butterCreate); }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public static byte[] array(int size) { return (byte[])arrayCache.computeIfAbsent(Integer.valueOf(size), arrayCreate); }
/*    */ 
/*    */   
/*    */   public static Pointer pointer(long address) {
/* 54 */     Pointer.nativeValue(cachedPointer, address);
/* 55 */     return cachedPointer;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\misc\Cacheable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */