/*     */ package com.sun.jna.platform.win32.COM.util;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.COMUtils;
/*     */ import com.sun.jna.platform.win32.Ole32;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
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
/*     */ public class ComThread
/*     */ {
/*     */   ExecutorService executor;
/*     */   Runnable firstTask;
/*     */   boolean requiresInitialisation;
/*     */   long timeoutMilliseconds;
/*     */   Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
/*     */   
/*  37 */   public ComThread(String threadName, long timeoutMilliseconds, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) { this(threadName, timeoutMilliseconds, uncaughtExceptionHandler, 0); }
/*     */ 
/*     */   
/*     */   public ComThread(final String threadName, long timeoutMilliseconds, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, final int coinitialiseExFlag) {
/*  41 */     this.requiresInitialisation = true;
/*  42 */     this.timeoutMilliseconds = timeoutMilliseconds;
/*  43 */     this.uncaughtExceptionHandler = uncaughtExceptionHandler;
/*  44 */     this.firstTask = new Runnable()
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*     */         public void run() throws Throwable
/*     */         {
/*     */           try {
/*  52 */             WinNT.HRESULT hr = Ole32.INSTANCE.CoInitializeEx(null, coinitialiseExFlag);
/*  53 */             COMUtils.checkRC(hr);
/*  54 */             ComThread.this.requiresInitialisation = false;
/*  55 */           } catch (Throwable t) {
/*  56 */             ComThread.this.uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), t);
/*     */           } 
/*     */         }
/*     */       };
/*  60 */     this.executor = Executors.newSingleThreadExecutor(new ThreadFactory()
/*     */         {
/*     */           public Thread newThread(Runnable r)
/*     */           {
/*  64 */             if (!ComThread.this.requiresInitialisation)
/*     */             {
/*  66 */               throw new RuntimeException("ComThread executor has a problem.");
/*     */             }
/*  68 */             Thread thread = new Thread(r, threadName);
/*     */ 
/*     */             
/*  71 */             thread.setDaemon(true);
/*     */             
/*  73 */             thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
/*     */                 {
/*     */                   public void uncaughtException(Thread t, Throwable e) {
/*  76 */                     ComThread.this.requiresInitialisation = true;
/*  77 */                     ComThread.this.uncaughtExceptionHandler.uncaughtException(t, e);
/*     */                   }
/*     */                 });
/*     */             
/*  81 */             return thread;
/*     */           }
/*     */         });
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
/*     */   
/*     */   public void terminate(long timeoutMilliseconds) {
/*     */     try {
/*  97 */       this.executor.submit(new Runnable()
/*     */           {
/*     */             public void run() throws Throwable {
/* 100 */               Ole32.INSTANCE.CoUninitialize();
/*     */             }
/* 102 */           }).get(timeoutMilliseconds, TimeUnit.MILLISECONDS);
/*     */       
/* 104 */       this.executor.shutdown();
/*     */     }
/* 106 */     catch (InterruptedException e) {
/* 107 */       e.printStackTrace();
/* 108 */     } catch (ExecutionException e) {
/* 109 */       e.printStackTrace();
/* 110 */     } catch (TimeoutException e) {
/* 111 */       this.executor.shutdownNow();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 117 */     if (!this.executor.isShutdown()) {
/* 118 */       terminate(100L);
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> T execute(Callable<T> task) throws TimeoutException, InterruptedException, ExecutionException {
/* 123 */     if (this.requiresInitialisation) {
/* 124 */       this.executor.execute(this.firstTask);
/*     */     }
/* 126 */     return (T)this.executor.submit(task).get(this.timeoutMilliseconds, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\ComThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */