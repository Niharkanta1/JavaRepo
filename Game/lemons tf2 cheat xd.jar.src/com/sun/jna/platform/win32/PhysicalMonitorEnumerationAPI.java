/*    */ package com.sun.jna.platform.win32;
/*    */ 
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
/*    */ public interface PhysicalMonitorEnumerationAPI
/*    */ {
/*    */   public static final int PHYSICAL_MONITOR_DESCRIPTION_SIZE = 128;
/*    */   
/*    */   public static class PHYSICAL_MONITOR
/*    */     extends Structure
/*    */   {
/*    */     public WinNT.HANDLE hPhysicalMonitor;
/* 59 */     public char[] szPhysicalMonitorDescription = new char[128];
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "hPhysicalMonitor", "szPhysicalMonitorDescription" }); }
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\PhysicalMonitorEnumerationAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */