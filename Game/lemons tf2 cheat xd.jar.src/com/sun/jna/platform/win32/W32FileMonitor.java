/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.platform.FileMonitor;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
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
/*     */ public class W32FileMonitor
/*     */   extends FileMonitor
/*     */ {
/*     */   private static final int BUFFER_SIZE = 4096;
/*     */   private Thread watcher;
/*     */   private WinNT.HANDLE port;
/*     */   
/*     */   private class FileInfo
/*     */   {
/*     */     public final File file;
/*     */     public final WinNT.HANDLE handle;
/*     */     public final int notifyMask;
/*     */     public final boolean recursive;
/*     */     public final WinNT.FILE_NOTIFY_INFORMATION info;
/*     */     public final IntByReference infoLength;
/*     */     public final WinBase.OVERLAPPED overlapped;
/*     */     
/*     */     public FileInfo(File f, WinNT.HANDLE h, int mask, boolean recurse) {
/*  38 */       this.info = new WinNT.FILE_NOTIFY_INFORMATION('က');
/*  39 */       this.infoLength = new IntByReference();
/*  40 */       this.overlapped = new WinBase.OVERLAPPED();
/*     */       
/*  42 */       this.file = f;
/*  43 */       this.handle = h;
/*  44 */       this.notifyMask = mask;
/*  45 */       this.recursive = recurse;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  50 */   private final Map<File, FileInfo> fileMap = new HashMap();
/*  51 */   private final Map<WinNT.HANDLE, FileInfo> handleMap = new HashMap();
/*     */   private boolean disposing = false;
/*     */   
/*     */   private void handleChanges(FileInfo finfo) throws IOException {
/*  55 */     Kernel32 klib = Kernel32.INSTANCE;
/*  56 */     WinNT.FILE_NOTIFY_INFORMATION fni = finfo.info;
/*     */     
/*  58 */     fni.read();
/*     */     do {
/*  60 */       FileMonitor.FileEvent event = null;
/*  61 */       File file = new File(finfo.file, fni.getFilename());
/*  62 */       switch (fni.Action) {
/*     */         case 0:
/*     */           break;
/*     */         case 3:
/*  66 */           event = new FileMonitor.FileEvent(this, file, 4);
/*     */           break;
/*     */         case 1:
/*  69 */           event = new FileMonitor.FileEvent(this, file, true);
/*     */           break;
/*     */         case 2:
/*  72 */           event = new FileMonitor.FileEvent(this, file, 2);
/*     */           break;
/*     */         case 4:
/*  75 */           event = new FileMonitor.FileEvent(this, file, 16);
/*     */           break;
/*     */         case 5:
/*  78 */           event = new FileMonitor.FileEvent(this, file, 32);
/*     */           break;
/*     */         
/*     */         default:
/*  82 */           System.err.println("Unrecognized file action '" + fni.Action + "'");
/*     */           break;
/*     */       } 
/*  85 */       if (event != null) {
/*  86 */         notify(event);
/*     */       }
/*     */       
/*  89 */       fni = fni.next();
/*  90 */     } while (fni != null);
/*     */ 
/*     */     
/*  93 */     if (!finfo.file.exists()) {
/*  94 */       unwatch(finfo.file);
/*     */       
/*     */       return;
/*     */     } 
/*  98 */     if (!klib.ReadDirectoryChangesW(finfo.handle, finfo.info, finfo.info
/*  99 */         .size(), finfo.recursive, finfo.notifyMask, finfo.infoLength, finfo.overlapped, null))
/*     */     {
/* 101 */       if (!this.disposing) {
/* 102 */         int err = klib.GetLastError();
/* 103 */         throw new IOException("ReadDirectoryChangesW failed on " + finfo.file + ": '" + 
/*     */             
/* 105 */             Kernel32Util.formatMessageFromLastErrorCode(err) + "' (" + err + ")");
/*     */       }  } 
/*     */   }
/*     */   
/*     */   private static int watcherThreadID;
/*     */   
/*     */   private FileInfo waitForChange() {
/* 112 */     IntByReference rcount = new IntByReference();
/* 113 */     BaseTSD.ULONG_PTRByReference rkey = new BaseTSD.ULONG_PTRByReference();
/* 114 */     PointerByReference roverlap = new PointerByReference();
/* 115 */     if (!Kernel32.INSTANCE.GetQueuedCompletionStatus(this.port, rcount, rkey, roverlap, -1)) {
/* 116 */       return null;
/*     */     }
/* 118 */     synchronized (this) {
/* 119 */       return (FileInfo)this.handleMap.get(new WinNT.HANDLE(rkey.getValue().toPointer()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private int convertMask(int mask) {
/* 124 */     int result = 0;
/* 125 */     if ((mask & true) != 0) {
/* 126 */       result |= 0x40;
/*     */     }
/* 128 */     if ((mask & 0x2) != 0) {
/* 129 */       result |= 0x3;
/*     */     }
/* 131 */     if ((mask & 0x4) != 0) {
/* 132 */       result |= 0x10;
/*     */     }
/* 134 */     if ((mask & 0x30) != 0) {
/* 135 */       result |= 0x3;
/*     */     }
/* 137 */     if ((mask & 0x40) != 0) {
/* 138 */       result |= 0x8;
/*     */     }
/* 140 */     if ((mask & 0x8) != 0) {
/* 141 */       result |= 0x20;
/*     */     }
/* 143 */     if ((mask & 0x80) != 0) {
/* 144 */       result |= 0x4;
/*     */     }
/* 146 */     if ((mask & 0x100) != 0) {
/* 147 */       result |= 0x100;
/*     */     }
/* 149 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void watch(File file, int eventMask, boolean recursive) throws IOException {
/* 155 */     File dir = file;
/* 156 */     if (!dir.isDirectory()) {
/* 157 */       recursive = false;
/* 158 */       dir = file.getParentFile();
/*     */     } 
/* 160 */     while (dir != null && !dir.exists()) {
/* 161 */       recursive = true;
/* 162 */       dir = dir.getParentFile();
/*     */     } 
/* 164 */     if (dir == null) {
/* 165 */       throw new FileNotFoundException("No ancestor found for " + file);
/*     */     }
/* 167 */     Kernel32 klib = Kernel32.INSTANCE;
/* 168 */     int mask = 7;
/*     */     
/* 170 */     int flags = 1107296256;
/*     */     
/* 172 */     WinNT.HANDLE handle = klib.CreateFile(file.getAbsolutePath(), 1, mask, null, 3, flags, null);
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (WinBase.INVALID_HANDLE_VALUE.equals(handle)) {
/* 177 */       throw new IOException("Unable to open " + file + " (" + klib
/* 178 */           .GetLastError() + ")");
/*     */     }
/* 180 */     int notifyMask = convertMask(eventMask);
/* 181 */     FileInfo finfo = new FileInfo(file, handle, notifyMask, recursive);
/* 182 */     this.fileMap.put(file, finfo);
/* 183 */     this.handleMap.put(handle, finfo);
/*     */     
/* 185 */     this.port = klib.CreateIoCompletionPort(handle, this.port, handle.getPointer(), 0);
/* 186 */     if (WinBase.INVALID_HANDLE_VALUE.equals(this.port)) {
/* 187 */       throw new IOException("Unable to create/use I/O Completion port for " + file + " (" + klib
/*     */           
/* 189 */           .GetLastError() + ")");
/*     */     }
/*     */ 
/*     */     
/* 193 */     if (!klib.ReadDirectoryChangesW(handle, finfo.info, finfo.info.size(), recursive, notifyMask, finfo.infoLength, finfo.overlapped, null)) {
/*     */ 
/*     */       
/* 196 */       int err = klib.GetLastError();
/* 197 */       throw new IOException("ReadDirectoryChangesW failed on " + finfo.file + ", handle " + handle + ": '" + 
/*     */           
/* 199 */           Kernel32Util.formatMessageFromLastErrorCode(err) + "' (" + err + ")");
/*     */     } 
/*     */     
/* 202 */     if (this.watcher == null) {
/* 203 */       this.watcher = new Thread("W32 File Monitor-" + watcherThreadID++)
/*     */         {
/*     */           public void run() {
/*     */             while (true) {
/* 207 */               W32FileMonitor.FileInfo finfo = W32FileMonitor.this.waitForChange();
/* 208 */               if (finfo == null) {
/* 209 */                 synchronized (W32FileMonitor.this) {
/* 210 */                   if (W32FileMonitor.this.fileMap.isEmpty()) {
/* 211 */                     W32FileMonitor.this.watcher = null;
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                   continue;
/*     */                 } 
/*     */               }
/*     */               try {
/* 219 */                 W32FileMonitor.this.handleChanges(finfo);
/*     */               }
/* 221 */               catch (IOException e) {
/*     */                 
/* 223 */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/*     */           }
/*     */         };
/* 228 */       this.watcher.setDaemon(true);
/* 229 */       this.watcher.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void unwatch(File file) {
/* 234 */     FileInfo finfo = (FileInfo)this.fileMap.remove(file);
/* 235 */     if (finfo != null) {
/* 236 */       this.handleMap.remove(finfo.handle);
/* 237 */       Kernel32 klib = Kernel32.INSTANCE;
/*     */       
/* 239 */       klib.CloseHandle(finfo.handle);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 244 */     this.disposing = true;
/*     */ 
/*     */     
/* 247 */     int i = 0;
/* 248 */     for (Object[] keys = this.fileMap.keySet().toArray(); !this.fileMap.isEmpty();) {
/* 249 */       unwatch((File)keys[i++]);
/*     */     }
/*     */     
/* 252 */     Kernel32 klib = Kernel32.INSTANCE;
/* 253 */     klib.PostQueuedCompletionStatus(this.port, 0, null, null);
/* 254 */     klib.CloseHandle(this.port);
/* 255 */     this.port = null;
/* 256 */     this.watcher = null;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\W32FileMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */