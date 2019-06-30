/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.win32.StdCallLibrary;
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
/*    */ public interface Winioctl
/*    */   extends StdCallLibrary
/*    */ {
/*    */   public static final int IOCTL_STORAGE_GET_DEVICE_NUMBER = 2953344;
/*    */   
/*    */   public static class STORAGE_DEVICE_NUMBER
/*    */     extends Structure
/*    */   {
/*    */     public int DeviceType;
/*    */     public int DeviceNumber;
/*    */     public int PartitionNumber;
/*    */     
/*    */     public static class ByReference
/*    */       extends STORAGE_DEVICE_NUMBER
/*    */       implements Structure.ByReference
/*    */     {
/*    */       public ByReference() {}
/*    */       
/* 41 */       public ByReference(Pointer memory) { super(memory); }
/*    */     }
/*    */ 
/*    */     
/*    */     public STORAGE_DEVICE_NUMBER() {}
/*    */ 
/*    */     
/*    */     public STORAGE_DEVICE_NUMBER(Pointer memory) {
/* 49 */       super(memory);
/* 50 */       read();
/*    */     }
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
/* 70 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "DeviceType", "DeviceNumber", "PartitionNumber" }); }
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Winioctl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */