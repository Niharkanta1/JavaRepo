/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.ptr.IntByReference;
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
/*     */ public class W32Service
/*     */ {
/*     */   Winsvc.SC_HANDLE _handle;
/*     */   
/*     */   public W32Service(Winsvc.SC_HANDLE handle) {
/*  26 */     this._handle = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     this._handle = handle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  42 */     if (this._handle != null) {
/*  43 */       if (!Advapi32.INSTANCE.CloseServiceHandle(this._handle)) {
/*  44 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*  46 */       this._handle = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Winsvc.SERVICE_STATUS_PROCESS queryStatus() {
/*  56 */     IntByReference size = new IntByReference();
/*     */     
/*  58 */     Advapi32.INSTANCE.QueryServiceStatusEx(this._handle, 0, null, 0, size);
/*     */ 
/*     */     
/*  61 */     Winsvc.SERVICE_STATUS_PROCESS status = new Winsvc.SERVICE_STATUS_PROCESS(size.getValue());
/*  62 */     if (!Advapi32.INSTANCE.QueryServiceStatusEx(this._handle, 0, status, status
/*  63 */         .size(), size)) {
/*  64 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/*  67 */     return status;
/*     */   }
/*     */   
/*     */   public void startService() {
/*  71 */     waitForNonPendingState();
/*     */     
/*  73 */     if ((queryStatus()).dwCurrentState == 4) {
/*     */       return;
/*     */     }
/*  76 */     if (!Advapi32.INSTANCE.StartService(this._handle, 0, null)) {
/*  77 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*  79 */     waitForNonPendingState();
/*  80 */     if ((queryStatus()).dwCurrentState != 4) {
/*  81 */       throw new RuntimeException("Unable to start the service");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopService() {
/*  89 */     waitForNonPendingState();
/*     */     
/*  91 */     if ((queryStatus()).dwCurrentState == 1) {
/*     */       return;
/*     */     }
/*  94 */     if (!Advapi32.INSTANCE.ControlService(this._handle, 1, new Winsvc.SERVICE_STATUS()))
/*     */     {
/*  96 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*  98 */     waitForNonPendingState();
/*  99 */     if ((queryStatus()).dwCurrentState != 1) {
/* 100 */       throw new RuntimeException("Unable to stop the service");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void continueService() {
/* 108 */     waitForNonPendingState();
/*     */     
/* 110 */     if ((queryStatus()).dwCurrentState == 4) {
/*     */       return;
/*     */     }
/* 113 */     if (!Advapi32.INSTANCE.ControlService(this._handle, 3, new Winsvc.SERVICE_STATUS()))
/*     */     {
/* 115 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 117 */     waitForNonPendingState();
/* 118 */     if ((queryStatus()).dwCurrentState != 4) {
/* 119 */       throw new RuntimeException("Unable to continue the service");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseService() {
/* 127 */     waitForNonPendingState();
/*     */     
/* 129 */     if ((queryStatus()).dwCurrentState == 7) {
/*     */       return;
/*     */     }
/* 132 */     if (!Advapi32.INSTANCE.ControlService(this._handle, 2, new Winsvc.SERVICE_STATUS()))
/*     */     {
/* 134 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 136 */     waitForNonPendingState();
/* 137 */     if ((queryStatus()).dwCurrentState != 7) {
/* 138 */       throw new RuntimeException("Unable to pause the service");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForNonPendingState() {
/* 147 */     Winsvc.SERVICE_STATUS_PROCESS status = queryStatus();
/*     */     
/* 149 */     int previousCheckPoint = status.dwCheckPoint;
/* 150 */     int checkpointStartTickCount = Kernel32.INSTANCE.GetTickCount();
/*     */     
/* 152 */     while (isPendingState(status.dwCurrentState)) {
/*     */ 
/*     */       
/* 155 */       if (status.dwCheckPoint != previousCheckPoint) {
/* 156 */         previousCheckPoint = status.dwCheckPoint;
/* 157 */         checkpointStartTickCount = Kernel32.INSTANCE.GetTickCount();
/*     */       } 
/*     */ 
/*     */       
/* 161 */       if (Kernel32.INSTANCE.GetTickCount() - checkpointStartTickCount > status.dwWaitHint) {
/* 162 */         throw new RuntimeException("Timeout waiting for service to change to a non-pending state.");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       int dwWaitTime = status.dwWaitHint / 10;
/*     */       
/* 171 */       if (dwWaitTime < 1000) {
/* 172 */         dwWaitTime = 1000;
/* 173 */       } else if (dwWaitTime > 10000) {
/* 174 */         dwWaitTime = 10000;
/*     */       } 
/*     */       try {
/* 177 */         Thread.sleep(dwWaitTime);
/* 178 */       } catch (InterruptedException e) {
/* 179 */         throw new RuntimeException(e);
/*     */       } 
/*     */       
/* 182 */       status = queryStatus();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPendingState(int state) {
/* 187 */     switch (state) {
/*     */       case 2:
/*     */       case 3:
/*     */       case 5:
/*     */       case 6:
/* 192 */         return true;
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public Winsvc.SC_HANDLE getHandle() { return this._handle; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\W32Service.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */