/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
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
/*    */ 
/*    */ 
/*    */ public interface Wdm
/*    */   extends StdCallLibrary
/*    */ {
/*    */   public static class KEY_BASIC_INFORMATION
/*    */     extends Structure
/*    */   {
/*    */     public long LastWriteTime;
/*    */     public int TitleIndex;
/*    */     public int NameLength;
/*    */     public char[] Name;
/*    */     
/*    */     public KEY_BASIC_INFORMATION() {}
/*    */     
/*    */     public KEY_BASIC_INFORMATION(int size) {
/* 40 */       this.NameLength = size - 16;
/* 41 */       this.Name = new char[this.NameLength];
/* 42 */       allocateMemory();
/*    */     }
/*    */     
/*    */     public KEY_BASIC_INFORMATION(Pointer memory) {
/* 46 */       super(memory);
/* 47 */       read();
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
/*    */ 
/*    */     
/* 69 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "LastWriteTime", "TitleIndex", "NameLength", "Name" }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 76 */     public String getName() { return Native.toString(this.Name); }
/*    */ 
/*    */     
/*    */     public void read() {
/* 80 */       super.read();
/* 81 */       this.Name = new char[this.NameLength / 2];
/* 82 */       readField("Name");
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract class KEY_INFORMATION_CLASS {
/*    */     public static final int KeyBasicInformation = 0;
/*    */     public static final int KeyNodeInformation = 1;
/*    */     public static final int KeyFullInformation = 2;
/*    */     public static final int KeyNameInformation = 3;
/*    */     public static final int KeyCachedInformation = 4;
/*    */     public static final int KeyVirtualizationInformation = 5;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Wdm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */