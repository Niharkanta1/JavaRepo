/*     */ package com.sun.jna.platform;
/*     */ 
/*     */ import com.sun.jna.platform.win32.W32FileMonitor;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EventObject;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public abstract class FileMonitor
/*     */ {
/*     */   public static final int FILE_CREATED = 1;
/*     */   public static final int FILE_DELETED = 2;
/*     */   public static final int FILE_MODIFIED = 4;
/*     */   public static final int FILE_ACCESSED = 8;
/*     */   public static final int FILE_NAME_CHANGED_OLD = 16;
/*     */   public static final int FILE_NAME_CHANGED_NEW = 32;
/*     */   public static final int FILE_RENAMED = 48;
/*     */   public static final int FILE_SIZE_CHANGED = 64;
/*     */   public static final int FILE_ATTRIBUTES_CHANGED = 128;
/*     */   public static final int FILE_SECURITY_CHANGED = 256;
/*     */   public static final int FILE_ANY = 511;
/*     */   
/*     */   public static interface FileListener
/*     */   {
/*     */     void fileChanged(FileMonitor.FileEvent param1FileEvent);
/*     */   }
/*     */   
/*     */   public class FileEvent
/*     */     extends EventObject
/*     */   {
/*     */     private final File file;
/*     */     private final int type;
/*     */     
/*     */     public FileEvent(File file, int type) {
/*  55 */       super(FileMonitor.this);
/*  56 */       this.file = file;
/*  57 */       this.type = type;
/*     */     }
/*  59 */     public File getFile() { return this.file; }
/*  60 */     public int getType() { return this.type; }
/*     */     
/*  62 */     public String toString() { return "FileEvent: " + this.file + ":" + this.type; }
/*     */   }
/*     */ 
/*     */   
/*  66 */   private final Map<File, Integer> watched = new HashMap();
/*  67 */   private List<FileListener> listeners = new ArrayList();
/*     */   protected abstract void watch(File paramFile, int paramInt, boolean paramBoolean) throws IOException;
/*     */   
/*     */   protected abstract void unwatch(File paramFile);
/*     */   
/*     */   public abstract void dispose();
/*     */   
/*  74 */   public void addWatch(File dir) { addWatch(dir, 511); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void addWatch(File dir, int mask) throws IOException { addWatch(dir, mask, dir.isDirectory()); }
/*     */ 
/*     */   
/*     */   public void addWatch(File dir, int mask, boolean recursive) throws IOException {
/*  82 */     this.watched.put(dir, new Integer(mask));
/*  83 */     watch(dir, mask, recursive);
/*     */   }
/*     */   
/*     */   public void removeWatch(File file) {
/*  87 */     if (this.watched.remove(file) != null) {
/*  88 */       unwatch(file);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void notify(FileEvent e) {
/*  93 */     for (FileListener listener : this.listeners) {
/*  94 */       listener.fileChanged(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addFileListener(FileListener listener) {
/*  99 */     List<FileListener> list = new ArrayList<FileListener>(this.listeners);
/* 100 */     list.add(listener);
/* 101 */     this.listeners = list;
/*     */   }
/*     */   
/*     */   public void removeFileListener(FileListener x) {
/* 105 */     List<FileListener> list = new ArrayList<FileListener>(this.listeners);
/* 106 */     list.remove(x);
/* 107 */     this.listeners = list;
/*     */   }
/*     */   
/*     */   protected void finalize() {
/* 111 */     for (File watchedFile : this.watched.keySet()) {
/* 112 */       removeWatch(watchedFile);
/*     */     }
/*     */     
/* 115 */     dispose();
/*     */   }
/*     */   
/*     */   private static class Holder {
/*     */     public static final FileMonitor INSTANCE;
/*     */     
/*     */     static  {
/* 122 */       os = System.getProperty("os.name");
/* 123 */       if (os.startsWith("Windows")) {
/* 124 */         INSTANCE = new W32FileMonitor();
/*     */       } else {
/*     */         
/* 127 */         throw new Error("FileMonitor not implemented for " + os);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 133 */   public static FileMonitor getInstance() { return Holder.INSTANCE; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\FileMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */