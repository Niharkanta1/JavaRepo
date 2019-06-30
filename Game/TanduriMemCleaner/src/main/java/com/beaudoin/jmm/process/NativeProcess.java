/*    */ package com.beaudoin.jmm.process;
/*    */ 
/*    */ import com.beaudoin.jmm.misc.Utils;
/*    */ import com.beaudoin.jmm.natives.mac.mac;
/*    */ import com.beaudoin.jmm.natives.unix.libc;
/*    */ import com.beaudoin.jmm.natives.win32.Kernel32;
/*    */ import com.beaudoin.jmm.process.impl.mac.MacProcess;
/*    */ import com.beaudoin.jmm.process.impl.unix.UnixProcess;
/*    */ import com.beaudoin.jmm.process.impl.win32.Win32Process;
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Platform;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.platform.win32.Tlhelp32;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public interface NativeProcess
/*    */   extends ReadableRegion
/*    */ {
/*    */   static NativeProcess byName(String name) {
/* 46 */     if (Platform.isWindows())
/* 47 */     { Tlhelp32.PROCESSENTRY32.ByReference entry = new Tlhelp32.PROCESSENTRY32.ByReference();
/* 48 */       snapshot = Kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL, 0);
/*    */       try {
/* 50 */         while (Kernel32.Process32Next(snapshot, entry)) {
/* 51 */           String processName = Native.toString(entry.szExeFile);
/* 52 */           if (name.equals(processName)) {
/* 53 */             return byId(entry.th32ProcessID.intValue());
/*    */           }
/*    */         } 
/*    */       } finally {
/* 57 */         Kernel32.CloseHandle(snapshot);
/*    */       }  }
/* 59 */     else { if (Platform.isMac() || Platform.isLinux()) {
/* 60 */         return byId(Utils.exec(new String[] { "bash", "-c", "ps -A | grep -m1 \"" + name + "\" | awk '{print $1}'" }));
/*    */       }
/* 62 */       throw new UnsupportedOperationException("Unknown operating system! (" + System.getProperty("os.name") + ")"); }
/*    */     
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   static NativeProcess byId(int id) {
/* 68 */     if ((Platform.isMac() || Platform.isLinux()) && !checkSudo()) {
/* 69 */       throw new RuntimeException("You need to run as root/sudo in order for functionality.");
/*    */     }
/* 71 */     if (Platform.isWindows())
/* 72 */       return new Win32Process(id, Kernel32.OpenProcess(1080, true, id)); 
/* 73 */     if (Platform.isMac()) {
/* 74 */       IntByReference out = new IntByReference();
/* 75 */       if (mac.task_for_pid(mac.mach_task_self(), id, out) != 0) {
/* 76 */         throw new IllegalStateException("Failed to find mach task port for process, ensure you are running as sudo.");
/*    */       }
/* 78 */       return new MacProcess(id, out.getValue());
/* 79 */     }  if (Platform.isLinux()) {
/* 80 */       return new UnixProcess(id);
/*    */     }
/* 82 */     throw new IllegalStateException("Process " + id + " was not found. Are you sure its running?");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 87 */   static boolean checkSudo() { return (libc.getuid() == 0); }
/*    */   
/*    */   int id();
/*    */   
/*    */   void initModules();
/*    */   
/*    */   Module findModule(String paramString);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\process\NativeProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */