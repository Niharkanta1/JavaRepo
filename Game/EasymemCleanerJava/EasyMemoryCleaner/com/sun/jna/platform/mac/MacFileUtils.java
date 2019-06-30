/*    */ package com.sun.jna.platform.mac;
/*    */ 
/*    */ import com.sun.jna.Library;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.platform.FileUtils;
/*    */ import com.sun.jna.ptr.ByteByReference;
/*    */ import com.sun.jna.ptr.PointerByReference;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
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
/*    */ public class MacFileUtils
/*    */   extends FileUtils
/*    */ {
/* 31 */   public boolean hasTrash() { return true; }
/*    */   public static interface FileManager extends Library { public static final int kFSFileOperationDefaultOptions = 0;
/*    */     public static final int kFSFileOperationsOverwrite = 1;
/*    */     public static final int kFSFileOperationsSkipSourcePermissionErrors = 2;
/* 35 */     public static final FileManager INSTANCE = (FileManager)Native.loadLibrary("CoreServices", FileManager.class); public static final int kFSFileOperationsDoNotMoveAcrossVolumes = 4; public static final int kFSFileOperationsSkipPreflight = 8; public static final int kFSPathDefaultOptions = 0; public static final int kFSPathMakeRefDoNotFollowLeafSymlink = 1;
/*    */     
/*    */     int FSRefMakePath(FSRef param1FSRef, byte[] param1ArrayOfByte, int param1Int);
/*    */     
/*    */     int FSPathMakeRef(String param1String, int param1Int, ByteByReference param1ByteByReference);
/*    */     
/*    */     int FSPathMakeRefWithOptions(String param1String, int param1Int, FSRef param1FSRef, ByteByReference param1ByteByReference);
/*    */     
/*    */     int FSPathMoveObjectToTrashSync(String param1String, PointerByReference param1PointerByReference, int param1Int);
/*    */     
/*    */     int FSMoveObjectToTrashSync(FSRef param1FSRef1, FSRef param1FSRef2, int param1Int);
/*    */     
/* 47 */     public static class FSRef extends Structure { public byte[] hidden = new byte[80];
/* 48 */       protected List getFieldOrder() { return Arrays.asList(new String[] { "hidden" }); } } } public static class FSRef extends Structure { protected List getFieldOrder() { return Arrays.asList(new String[] { "hidden" }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public byte[] hidden = new byte[80]; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void moveToTrash(File[] files) throws IOException {
/* 61 */     File home = new File(System.getProperty("user.home"));
/* 62 */     File trash = new File(home, ".Trash");
/* 63 */     if (!trash.exists()) {
/* 64 */       throw new IOException("The Trash was not found in its expected location (" + trash + ")");
/*    */     }
/* 66 */     List<String> failed = new ArrayList<String>();
/* 67 */     for (int i = 0; i < files.length; i++) {
/* 68 */       File src = files[i];
/* 69 */       FileManager.FSRef fsref = new FileManager.FSRef();
/* 70 */       int status = FileManager.INSTANCE.FSPathMakeRefWithOptions(src.getAbsolutePath(), 1, fsref, null);
/*    */ 
/*    */       
/* 73 */       if (status != 0) {
/* 74 */         failed.add(src + " (FSRef: " + status + ")");
/*    */       } else {
/*    */         
/* 77 */         status = FileManager.INSTANCE.FSMoveObjectToTrashSync(fsref, null, 0);
/* 78 */         if (status != 0)
/* 79 */           failed.add(src + " (" + status + ")"); 
/*    */       } 
/*    */     } 
/* 82 */     if (failed.size() > 0)
/* 83 */       throw new IOException("The following files could not be trashed: " + failed); 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\mac\MacFileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */