/*    */ package com.beaudoin.jmm.natives.mac;
/*    */ 
/*    */ import com.sun.jna.LastErrorException;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.NativeLibrary;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import com.sun.jna.ptr.PointerByReference;
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
/*    */ public final class mac
/*    */ {
/*    */   public static native int task_for_pid(int paramInt1, int paramInt2, IntByReference paramIntByReference);
/*    */   
/*    */   public static native int mach_task_self();
/*    */   
/*    */   public static native int vm_write(int paramInt1, Pointer paramPointer1, Pointer paramPointer2, int paramInt2);
/*    */   
/*    */   public static native int vm_read(int paramInt1, Pointer paramPointer1, int paramInt2, Pointer paramPointer2, IntByReference paramIntByReference);
/*    */   
/*    */   public static native int vm_read(int paramInt1, Pointer paramPointer, int paramInt2, PointerByReference paramPointerByReference, IntByReference paramIntByReference);
/*    */   
/*    */   public static native String mach_error_string(int paramInt) throws LastErrorException;
/*    */   
/*    */   static  {
/* 40 */     Native.register(NativeLibrary.getInstance("c"));
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\natives\mac\mac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */