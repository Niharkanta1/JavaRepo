/*     */ package com.sun.jna.platform.win32.COM.util;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.COMUtils;
/*     */ import com.sun.jna.platform.win32.COM.Dispatch;
/*     */ import com.sun.jna.platform.win32.COM.IEnumMoniker;
/*     */ import com.sun.jna.platform.win32.COM.IRunningObjectTable;
/*     */ import com.sun.jna.platform.win32.COM.Moniker;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
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
/*     */ public class EnumMoniker
/*     */   extends Object
/*     */   implements Iterable<IDispatch>
/*     */ {
/*     */   ComThread comThread;
/*     */   Factory factory;
/*     */   IRunningObjectTable rawRot;
/*     */   IEnumMoniker raw;
/*     */   Moniker rawNext;
/*     */   
/*     */   protected EnumMoniker(IEnumMoniker raw, IRunningObjectTable rawRot, Factory factory) {
/*  40 */     this.rawRot = rawRot;
/*  41 */     this.raw = raw;
/*  42 */     this.factory = factory;
/*  43 */     this.comThread = factory.getComThread();
/*     */     
/*     */     try {
/*  46 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/*  49 */               return EnumMoniker.this.raw.Reset();
/*     */             }
/*     */           });
/*  52 */       COMUtils.checkRC(hr);
/*  53 */     } catch (InterruptedException e) {
/*  54 */       throw new RuntimeException(e);
/*  55 */     } catch (ExecutionException e) {
/*  56 */       throw new RuntimeException(e);
/*  57 */     } catch (TimeoutException e) {
/*  58 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/*  61 */     cacheNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void cacheNext() {
/*     */     try {
/*  72 */       final PointerByReference rgelt = new PointerByReference();
/*  73 */       final WinDef.ULONGByReference pceltFetched = new WinDef.ULONGByReference();
/*     */       
/*  75 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/*  78 */               return EnumMoniker.this.raw.Next(new WinDef.ULONG(1L), rgelt, pceltFetched);
/*     */             }
/*     */           });
/*     */       
/*  82 */       if (WinNT.S_OK.equals(hr) && pceltFetched.getValue().intValue() > 0) {
/*  83 */         this.rawNext = new Moniker(rgelt.getValue());
/*     */       } else {
/*  85 */         if (!WinNT.S_FALSE.equals(hr)) {
/*  86 */           COMUtils.checkRC(hr);
/*     */         }
/*  88 */         this.rawNext = null;
/*     */       }
/*     */     
/*  91 */     } catch (InterruptedException e) {
/*  92 */       throw new RuntimeException(e);
/*  93 */     } catch (ExecutionException e) {
/*  94 */       throw new RuntimeException(e);
/*  95 */     } catch (TimeoutException e) {
/*  96 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<IDispatch> iterator() {
/* 102 */     return new Iterator<IDispatch>()
/*     */       {
/*     */         public boolean hasNext()
/*     */         {
/* 106 */           return (null != EnumMoniker.this.rawNext);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public IDispatch next() {
/*     */           try {
/* 113 */             final Moniker moniker = EnumMoniker.this.rawNext;
/* 114 */             final PointerByReference ppunkObject = new PointerByReference();
/* 115 */             WinNT.HRESULT hr = (WinNT.HRESULT)EnumMoniker.this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */                 {
/*     */                   public WinNT.HRESULT call() throws Exception {
/* 118 */                     return EnumMoniker.this.rawRot.GetObject(moniker.getPointer(), ppunkObject);
/*     */                   }
/*     */                 });
/* 121 */             COMUtils.checkRC(hr);
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
/*     */             
/* 141 */             Dispatch dispatch = new Dispatch(ppunkObject.getValue());
/* 142 */             EnumMoniker.this.cacheNext();
/* 143 */             IDispatch d = (IDispatch)EnumMoniker.this.factory.createProxy(IDispatch.class, dispatch);
/*     */             
/* 145 */             int n = dispatch.Release();
/* 146 */             return d;
/*     */           }
/* 148 */           catch (InterruptedException e) {
/* 149 */             throw new RuntimeException(e);
/* 150 */           } catch (ExecutionException e) {
/* 151 */             throw new RuntimeException(e);
/* 152 */           } catch (TimeoutException e) {
/* 153 */             throw new RuntimeException(e);
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 160 */         public void remove() { throw new UnsupportedOperationException("remove"); }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\EnumMoniker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */