/*    */ package com.sun.jna.platform.win32;
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
/*    */ public class W32ServiceManager
/*    */ {
/* 23 */   Winsvc.SC_HANDLE _handle = null;
/* 24 */   String _machineName = null;
/* 25 */   String _databaseName = null;
/*    */ 
/*    */   
/*    */   public W32ServiceManager() {}
/*    */   
/*    */   public W32ServiceManager(String machineName, String databaseName) {
/* 31 */     this._machineName = machineName;
/* 32 */     this._databaseName = databaseName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void open(int permissions) {
/* 41 */     close();
/*    */     
/* 43 */     this._handle = Advapi32.INSTANCE.OpenSCManager(this._machineName, this._databaseName, permissions);
/*    */ 
/*    */     
/* 46 */     if (this._handle == null) {
/* 47 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/* 55 */     if (this._handle != null) {
/* 56 */       if (!Advapi32.INSTANCE.CloseServiceHandle(this._handle)) {
/* 57 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */       }
/* 59 */       this._handle = null;
/*    */     } 
/*    */   }
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
/*    */   public W32Service openService(String serviceName, int permissions) {
/* 73 */     Winsvc.SC_HANDLE serviceHandle = Advapi32.INSTANCE.OpenService(this._handle, serviceName, permissions);
/*    */ 
/*    */     
/* 76 */     if (serviceHandle == null) {
/* 77 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 80 */     return new W32Service(serviceHandle);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 89 */   public Winsvc.SC_HANDLE getHandle() { return this._handle; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\W32ServiceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */