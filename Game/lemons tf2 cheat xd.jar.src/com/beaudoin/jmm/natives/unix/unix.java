/*    */ package com.beaudoin.jmm.natives.unix;
/*    */ 
/*    */ import com.sun.jna.LastErrorException;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.NativeLibrary;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
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
/*    */ public final class unix
/*    */ {
/*    */   public static native long process_vm_readv(int paramInt, iovec paramiovec1, long paramLong1, iovec paramiovec2, long paramLong2, long paramLong3) throws LastErrorException;
/*    */   
/*    */   public static native long process_vm_writev(int paramInt, iovec paramiovec1, long paramLong1, iovec paramiovec2, long paramLong2, long paramLong3) throws LastErrorException;
/*    */   
/*    */   static  {
/* 38 */     Native.register(NativeLibrary.getInstance("c"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class iovec
/*    */     extends Structure
/*    */   {
/*    */     public Pointer iov_base;
/*    */ 
/*    */     
/*    */     public int iov_len;
/*    */ 
/*    */     
/* 52 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "iov_base", "iov_len" }); }
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\native\\uni\\unix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */