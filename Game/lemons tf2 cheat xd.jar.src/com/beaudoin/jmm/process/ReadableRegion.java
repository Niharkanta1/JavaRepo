/*    */ package com.beaudoin.jmm.process;
/*    */ 
/*    */ import com.beaudoin.jmm.misc.Cacheable;
/*    */ import com.beaudoin.jmm.misc.MemoryBuffer;
/*    */ import com.beaudoin.jmm.misc.Strings;
/*    */ import com.sun.jna.Pointer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ReadableRegion
/*    */ {
/*    */   MemoryBuffer read(Pointer paramPointer, int paramInt);
/*    */   
/*    */   NativeProcess write(Pointer paramPointer, MemoryBuffer paramMemoryBuffer);
/*    */   
/*    */   boolean canRead(Pointer paramPointer, int paramInt);
/*    */   
/* 22 */   default boolean readBoolean(long address) { return read(address, 1).getBoolean(); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   default int readByte(long address) { return read(address, 1).getByte(); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   default int readShort(long address) { return read(address, 2).getShort(); }
/*    */ 
/*    */ 
/*    */   
/* 34 */   default int readInt(long address) { return read(address, 4).getInt(); }
/*    */ 
/*    */ 
/*    */   
/* 38 */   default long readUnsignedInt(long address) { return Integer.toUnsignedLong(read(address, 4).getInt()); }
/*    */ 
/*    */ 
/*    */   
/* 42 */   default long readLong(long address) { return read(address, 8).getLong(); }
/*    */ 
/*    */ 
/*    */   
/* 46 */   default float readFloat(long address) { return read(address, 4).getFloat(); }
/*    */ 
/*    */ 
/*    */   
/* 50 */   default double readDouble(long address) { return read(address, 8).getDouble(); }
/*    */ 
/*    */   
/*    */   default String readString(long address, int length) {
/* 54 */     byte[] bytes = Cacheable.array(length);
/* 55 */     read(address, bytes.length).get(bytes);
/* 56 */     return Strings.transform(bytes);
/*    */   }
/*    */ 
/*    */   
/* 60 */   default MemoryBuffer read(long address, int size) { return read(Cacheable.pointer(address), size); }
/*    */ 
/*    */ 
/*    */   
/* 64 */   default NativeProcess writeBoolean(long address, boolean value) { return write(Cacheable.pointer(address), Cacheable.buffer(1).putBoolean(value)); }
/*    */ 
/*    */ 
/*    */   
/* 68 */   default NativeProcess writeByte(long address, int value) { return write(Cacheable.pointer(address), Cacheable.buffer(1).putByte(value)); }
/*    */ 
/*    */ 
/*    */   
/* 72 */   default NativeProcess writeShort(long address, int value) { return write(Cacheable.pointer(address), Cacheable.buffer(2).putShort(value)); }
/*    */ 
/*    */ 
/*    */   
/* 76 */   default NativeProcess writeInt(long address, int value) { return write(Cacheable.pointer(address), Cacheable.buffer(4).putInt(value)); }
/*    */ 
/*    */ 
/*    */   
/* 80 */   default NativeProcess writeLong(long address, long value) { return write(Cacheable.pointer(address), Cacheable.buffer(8).putLong(value)); }
/*    */ 
/*    */ 
/*    */   
/* 84 */   default NativeProcess writeFloat(long address, float value) { return write(Cacheable.pointer(address), Cacheable.buffer(4).putFloat(value)); }
/*    */ 
/*    */ 
/*    */   
/* 88 */   default NativeProcess writeDouble(long address, double value) { return write(Cacheable.pointer(address), Cacheable.buffer(8).putDouble(value)); }
/*    */ 
/*    */ 
/*    */   
/* 92 */   default boolean canRead(long address, int size) { return canRead(Cacheable.pointer(address), size); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\ReadableRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */