/*     */ package com.sun.jna.platform;
/*     */ 
/*     */ import com.sun.jna.platform.mac.MacFileUtils;
/*     */ import com.sun.jna.platform.win32.W32FileUtils;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FileUtils
/*     */ {
/*  27 */   public boolean hasTrash() { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void moveToTrash(File[] paramArrayOfFile) throws IOException;
/*     */ 
/*     */   
/*     */   private static class Holder
/*     */   {
/*     */     public static final FileUtils INSTANCE;
/*     */ 
/*     */     
/*     */     static  {
/*  40 */       os = System.getProperty("os.name");
/*  41 */       if (os.startsWith("Windows")) {
/*  42 */         INSTANCE = new W32FileUtils();
/*     */       }
/*  44 */       else if (os.startsWith("Mac")) {
/*  45 */         INSTANCE = new MacFileUtils();
/*     */       } else {
/*     */         
/*  48 */         INSTANCE = new FileUtils.DefaultFileUtils(null);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  54 */   public static FileUtils getInstance() { return Holder.INSTANCE; }
/*     */   
/*     */   private static class DefaultFileUtils
/*     */     extends FileUtils
/*     */   {
/*     */     private DefaultFileUtils() {}
/*     */     
/*     */     private File getTrashDirectory() {
/*  62 */       File home = new File(System.getProperty("user.home"));
/*  63 */       File trash = new File(home, ".Trash");
/*  64 */       if (!trash.exists()) {
/*  65 */         trash = new File(home, "Trash");
/*  66 */         if (!trash.exists()) {
/*  67 */           File desktop = new File(home, "Desktop");
/*  68 */           if (desktop.exists()) {
/*  69 */             trash = new File(desktop, ".Trash");
/*  70 */             if (!trash.exists()) {
/*  71 */               trash = new File(desktop, "Trash");
/*  72 */               if (!trash.exists()) {
/*  73 */                 trash = new File(System.getProperty("fileutils.trash", "Trash"));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  79 */       return trash;
/*     */     }
/*     */ 
/*     */     
/*  83 */     public boolean hasTrash() { return getTrashDirectory().exists(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void moveToTrash(File[] files) throws IOException {
/*  90 */       File trash = getTrashDirectory();
/*  91 */       if (!trash.exists()) {
/*  92 */         throw new IOException("No trash location found (define fileutils.trash to be the path to the trash)");
/*     */       }
/*  94 */       List<File> failed = new ArrayList<File>();
/*  95 */       for (int i = 0; i < files.length; i++) {
/*  96 */         File src = files[i];
/*  97 */         File target = new File(trash, src.getName());
/*  98 */         if (!src.renameTo(target)) {
/*  99 */           failed.add(src);
/*     */         }
/*     */       } 
/* 102 */       if (failed.size() > 0)
/* 103 */         throw new IOException("The following files could not be trashed: " + failed); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */