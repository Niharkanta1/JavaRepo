/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.WString;
/*    */ import com.sun.jna.platform.FileUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
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
/*    */ public class W32FileUtils
/*    */   extends FileUtils
/*    */ {
/* 24 */   public boolean hasTrash() { return true; }
/*    */ 
/*    */   
/*    */   public void moveToTrash(File[] files) throws IOException {
/* 28 */     Shell32 shell = Shell32.INSTANCE;
/* 29 */     ShellAPI.SHFILEOPSTRUCT fileop = new ShellAPI.SHFILEOPSTRUCT();
/* 30 */     fileop.wFunc = 3;
/* 31 */     String[] paths = new String[files.length];
/* 32 */     for (i = 0; i < paths.length; i++) {
/* 33 */       paths[i] = files[i].getAbsolutePath();
/*    */     }
/* 35 */     fileop.pFrom = new WString(fileop.encodePaths(paths));
/* 36 */     fileop.fFlags = 1620;
/* 37 */     int ret = shell.SHFileOperation(fileop);
/* 38 */     if (ret != 0) {
/* 39 */       throw new IOException("Move to trash failed: " + fileop.pFrom + ": " + 
/* 40 */           Kernel32Util.formatMessageFromLastErrorCode(ret));
/*    */     }
/* 42 */     if (fileop.fAnyOperationsAborted)
/* 43 */       throw new IOException("Move to trash aborted"); 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\W32FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */