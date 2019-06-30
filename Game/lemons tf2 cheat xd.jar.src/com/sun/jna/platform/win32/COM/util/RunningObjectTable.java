/*    */ package com.sun.jna.platform.win32.COM.util;
/*    */ 
/*    */ import com.sun.jna.platform.win32.COM.COMException;
/*    */ import com.sun.jna.platform.win32.COM.COMUtils;
/*    */ import com.sun.jna.platform.win32.COM.EnumMoniker;
/*    */ import com.sun.jna.platform.win32.COM.RunningObjectTable;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.ptr.PointerByReference;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.ExecutionException;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunningObjectTable
/*    */   implements IRunningObjectTable
/*    */ {
/*    */   Factory factory;
/*    */   ComThread comThread;
/*    */   RunningObjectTable raw;
/*    */   
/*    */   protected RunningObjectTable(RunningObjectTable raw, Factory factory) {
/* 29 */     this.raw = raw;
/* 30 */     this.factory = factory;
/* 31 */     this.comThread = factory.getComThread();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Iterable<IDispatch> enumRunning() {
/*    */     try {
/* 43 */       final PointerByReference ppenumMoniker = new PointerByReference();
/*    */       
/* 45 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*    */           {
/*    */             public WinNT.HRESULT call() throws Exception {
/* 48 */               return RunningObjectTable.this.raw.EnumRunning(ppenumMoniker);
/*    */             }
/*    */           });
/* 51 */       COMUtils.checkRC(hr);
/*    */       
/* 53 */       EnumMoniker raw = new EnumMoniker(ppenumMoniker.getValue());
/*    */       
/* 55 */       return new EnumMoniker(raw, this.raw, this.factory);
/*    */     }
/* 57 */     catch (InterruptedException e) {
/* 58 */       throw new RuntimeException(e);
/* 59 */     } catch (ExecutionException e) {
/* 60 */       throw new RuntimeException(e);
/* 61 */     } catch (TimeoutException e) {
/* 62 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> List<T> getActiveObjectsByInterface(Class<T> comInterface) {
/* 69 */     List<T> result = new ArrayList<T>();
/*    */     
/* 71 */     for (IDispatch obj : enumRunning()) {
/*    */       try {
/* 73 */         T dobj = (T)obj.queryInterface(comInterface);
/*    */         
/* 75 */         result.add(dobj);
/* 76 */       } catch (COMException ex) {}
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 81 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\RunningObjectTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */