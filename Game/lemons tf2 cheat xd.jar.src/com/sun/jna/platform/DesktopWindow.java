/*    */ package com.sun.jna.platform;
/*    */ 
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import java.awt.Rectangle;
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
/*    */ public class DesktopWindow
/*    */ {
/*    */   private WinDef.HWND hwnd;
/*    */   private String title;
/*    */   private String filePath;
/*    */   private Rectangle locAndSize;
/*    */   
/*    */   public DesktopWindow(WinDef.HWND hwnd, String title, String filePath, Rectangle locAndSize) {
/* 43 */     this.hwnd = hwnd;
/* 44 */     this.title = title;
/* 45 */     this.filePath = filePath;
/* 46 */     this.locAndSize = locAndSize;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public WinDef.HWND getHWND() { return this.hwnd; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public String getTitle() { return this.title; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public String getFilePath() { return this.filePath; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public Rectangle getLocAndSize() { return this.locAndSize; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\DesktopWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */