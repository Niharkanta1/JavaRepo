/*     */ package com.sun.jna.platform.win32.COM.util;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.COMException;
/*     */ import com.sun.jna.platform.win32.COM.COMUtils;
/*     */ import com.sun.jna.platform.win32.COM.Dispatch;
/*     */ import com.sun.jna.platform.win32.COM.IDispatch;
/*     */ import com.sun.jna.platform.win32.COM.RunningObjectTable;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComObject;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.Ole32;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Factory
/*     */ {
/*     */   ComThread comThread;
/*     */   WeakHashMap<ProxyObject, Integer> registeredObjects;
/*     */   
/*     */   public Factory() {
/*  45 */     this(new ComThread("Default Factory COM Thread", 5000L, new Thread.UncaughtExceptionHandler()
/*     */           {
/*     */             public void uncaughtException(Thread t, Throwable e) {}
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Factory(ComThread comThread) {
/*  54 */     this.comThread = comThread;
/*  55 */     this.registeredObjects = new WeakHashMap();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() {
/*     */     try {
/*  61 */       disposeAll();
/*     */     } finally {
/*  63 */       super.finalize();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public ComThread getComThread() { return this.comThread; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IRunningObjectTable getRunningObjectTable() {
/*     */     try {
/*  81 */       final PointerByReference rotPtr = new PointerByReference();
/*     */       
/*  83 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/*  86 */               return Ole32.INSTANCE.GetRunningObjectTable(new WinDef.DWORD(0L), rotPtr);
/*     */             }
/*     */           });
/*  89 */       COMUtils.checkRC(hr);
/*     */       
/*  91 */       RunningObjectTable raw = new RunningObjectTable(rotPtr.getValue());
/*  92 */       return new RunningObjectTable(raw, this);
/*     */     
/*     */     }
/*  95 */     catch (InterruptedException e) {
/*  96 */       throw new RuntimeException(e);
/*  97 */     } catch (ExecutionException e) {
/*  98 */       throw new RuntimeException(e);
/*  99 */     } catch (TimeoutException e) {
/* 100 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T createProxy(Class<T> comInterface, IDispatch dispatch) {
/* 109 */     ProxyObject jop = new ProxyObject(comInterface, dispatch, this);
/* 110 */     Object proxy = Proxy.newProxyInstance(comInterface.getClassLoader(), new Class[] { comInterface }, jop);
/* 111 */     return (T)comInterface.cast(proxy);
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
/*     */   <T> T createProxy(Class<T> comInterface, long unknownId, IDispatch dispatch) {
/* 123 */     ProxyObject jop = new ProxyObject(comInterface, unknownId, dispatch, this);
/* 124 */     Object proxy = Proxy.newProxyInstance(comInterface.getClassLoader(), new Class[] { comInterface }, jop);
/* 125 */     return (T)comInterface.cast(proxy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T createObject(Class<T> comInterface) {
/*     */     try {
/* 136 */       ComObject comObectAnnotation = (ComObject)comInterface.getAnnotation(ComObject.class);
/* 137 */       if (null == comObectAnnotation) {
/* 138 */         throw new COMException("createObject: Interface must define a value for either clsId or progId via the ComInterface annotation");
/*     */       }
/*     */       
/* 141 */       final Guid.GUID guid = discoverClsId(comObectAnnotation);
/*     */       
/* 143 */       final PointerByReference ptrDisp = new PointerByReference();
/* 144 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 147 */               return Ole32.INSTANCE.CoCreateInstance(guid, null, 21, IDispatch.IID_IDISPATCH, ptrDisp);
/*     */             }
/*     */           });
/*     */       
/* 151 */       COMUtils.checkRC(hr);
/* 152 */       Dispatch d = new Dispatch(ptrDisp.getValue());
/* 153 */       T t = (T)createProxy(comInterface, d);
/*     */ 
/*     */       
/* 156 */       int n = d.Release();
/* 157 */       return t;
/*     */     }
/* 159 */     catch (InterruptedException e) {
/* 160 */       throw new RuntimeException(e);
/* 161 */     } catch (ExecutionException e) {
/* 162 */       throw new RuntimeException(e);
/* 163 */     } catch (TimeoutException e) {
/* 164 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T fetchObject(Class<T> comInterface) {
/*     */     try {
/* 174 */       ComObject comObectAnnotation = (ComObject)comInterface.getAnnotation(ComObject.class);
/* 175 */       if (null == comObectAnnotation) {
/* 176 */         throw new COMException("createObject: Interface must define a value for either clsId or progId via the ComInterface annotation");
/*     */       }
/*     */       
/* 179 */       final Guid.GUID guid = discoverClsId(comObectAnnotation);
/*     */       
/* 181 */       final PointerByReference ptrDisp = new PointerByReference();
/* 182 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 185 */               return OleAuto.INSTANCE.GetActiveObject(guid, null, ptrDisp);
/*     */             }
/*     */           });
/* 188 */       COMUtils.checkRC(hr);
/* 189 */       Dispatch d = new Dispatch(ptrDisp.getValue());
/* 190 */       T t = (T)createProxy(comInterface, d);
/*     */ 
/*     */       
/* 193 */       d.Release();
/*     */       
/* 195 */       return t;
/* 196 */     } catch (InterruptedException e) {
/* 197 */       throw new RuntimeException(e);
/* 198 */     } catch (ExecutionException e) {
/* 199 */       throw new RuntimeException(e);
/* 200 */     } catch (TimeoutException e) {
/* 201 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   Guid.GUID discoverClsId(ComObject annotation) {
/*     */     try {
/* 207 */       String clsIdStr = annotation.clsId();
/* 208 */       final String progIdStr = annotation.progId();
/* 209 */       if (null != clsIdStr && !clsIdStr.isEmpty())
/* 210 */         return new Guid.CLSID(clsIdStr); 
/* 211 */       if (null != progIdStr && !progIdStr.isEmpty()) {
/* 212 */         final Guid.CLSID.ByReference rclsid = new Guid.CLSID.ByReference();
/*     */         
/* 214 */         WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */             {
/*     */               public WinNT.HRESULT call() throws Exception {
/* 217 */                 return Ole32.INSTANCE.CLSIDFromProgID(progIdStr, rclsid);
/*     */               }
/*     */             });
/*     */         
/* 221 */         COMUtils.checkRC(hr);
/* 222 */         return rclsid;
/*     */       } 
/* 224 */       throw new COMException("ComObject must define a value for either clsId or progId");
/*     */     }
/* 226 */     catch (InterruptedException e) {
/* 227 */       throw new RuntimeException(e);
/* 228 */     } catch (ExecutionException e) {
/* 229 */       throw new RuntimeException(e);
/* 230 */     } catch (TimeoutException e) {
/* 231 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(ProxyObject proxyObject) {
/* 239 */     synchronized (this.registeredObjects) {
/*     */ 
/*     */ 
/*     */       
/* 243 */       if (this.registeredObjects.containsKey(proxyObject)) {
/* 244 */         int r = ((Integer)this.registeredObjects.get(proxyObject)).intValue();
/* 245 */         this.registeredObjects.put(proxyObject, Integer.valueOf(r + 1));
/*     */       } else {
/* 247 */         this.registeredObjects.put(proxyObject, Integer.valueOf(1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unregister(ProxyObject proxyObject, int d) {
/* 253 */     synchronized (this.registeredObjects) {
/* 254 */       if (this.registeredObjects.containsKey(proxyObject)) {
/* 255 */         int r = ((Integer)this.registeredObjects.get(proxyObject)).intValue();
/* 256 */         if (r > 1) {
/* 257 */           this.registeredObjects.put(proxyObject, Integer.valueOf(r - d));
/*     */         } else {
/* 259 */           this.registeredObjects.remove(proxyObject);
/*     */         } 
/*     */       } else {
/* 262 */         throw new RuntimeException("Tried to dispose a ProxyObject that is not registered");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void disposeAll() {
/* 269 */     synchronized (this.registeredObjects) {
/* 270 */       Set<ProxyObject> s = new HashSet<ProxyObject>(this.registeredObjects.keySet());
/* 271 */       for (ProxyObject proxyObject : s) {
/* 272 */         int r = ((Integer)this.registeredObjects.get(proxyObject)).intValue();
/* 273 */         proxyObject.dispose(r);
/*     */       } 
/* 275 */       this.registeredObjects.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */