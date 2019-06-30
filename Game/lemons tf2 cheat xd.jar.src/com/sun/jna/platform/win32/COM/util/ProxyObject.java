/*     */ package com.sun.jna.platform.win32.COM.util;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.COM.COMException;
/*     */ import com.sun.jna.platform.win32.COM.COMUtils;
/*     */ import com.sun.jna.platform.win32.COM.ConnectionPoint;
/*     */ import com.sun.jna.platform.win32.COM.ConnectionPointContainer;
/*     */ import com.sun.jna.platform.win32.COM.Dispatch;
/*     */ import com.sun.jna.platform.win32.COM.IDispatch;
/*     */ import com.sun.jna.platform.win32.COM.IDispatchCallback;
/*     */ import com.sun.jna.platform.win32.COM.IUnknown;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComInterface;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComMethod;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComProperty;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.Kernel32;
/*     */ import com.sun.jna.platform.win32.Kernel32Util;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.Variant.VARIANT;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProxyObject
/*     */   implements InvocationHandler, IDispatch, IRawDispatchHandle
/*     */ {
/*     */   long unknownId;
/*     */   Class<?> theInterface;
/*     */   Factory factory;
/*     */   ComThread comThread;
/*     */   IDispatch rawDispatch;
/*     */   
/*     */   public ProxyObject(Class<?> theInterface, IDispatch rawDispatch, Factory factory) {
/*  68 */     this.unknownId = -1L;
/*  69 */     this.rawDispatch = rawDispatch;
/*  70 */     this.comThread = factory.getComThread();
/*  71 */     this.theInterface = theInterface;
/*  72 */     this.factory = factory;
/*     */ 
/*     */     
/*  75 */     int n = this.rawDispatch.AddRef();
/*  76 */     getUnknownId();
/*  77 */     factory.register(this);
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
/*     */   ProxyObject(Class<?> theInterface, long unknownId, IDispatch rawDispatch, Factory factory) {
/*  90 */     this.unknownId = unknownId;
/*  91 */     this.rawDispatch = rawDispatch;
/*  92 */     this.comThread = factory.getComThread();
/*  93 */     this.theInterface = theInterface;
/*  94 */     this.factory = factory;
/*     */ 
/*     */     
/*  97 */     int n = this.rawDispatch.AddRef();
/*  98 */     factory.register(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getUnknownId() {
/* 107 */     if (-1L == this.unknownId) {
/*     */       
/*     */       try {
/* 110 */         final PointerByReference ppvObject = new PointerByReference();
/*     */         
/* 112 */         Thread current = Thread.currentThread();
/* 113 */         String tn = current.getName();
/*     */         
/* 115 */         WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */             {
/*     */               public WinNT.HRESULT call() throws Exception {
/* 118 */                 Guid.IID iid = IUnknown.IID_IUNKNOWN;
/* 119 */                 return ProxyObject.this.getRawDispatch().QueryInterface(new Guid.GUID.ByValue(iid), ppvObject);
/*     */               }
/*     */             });
/*     */         
/* 123 */         if (WinNT.S_OK.equals(hr)) {
/* 124 */           Dispatch dispatch = new Dispatch(ppvObject.getValue());
/* 125 */           this.unknownId = Pointer.nativeValue(dispatch.getPointer());
/*     */ 
/*     */ 
/*     */           
/* 129 */           int n = dispatch.Release();
/*     */         } else {
/* 131 */           String formatMessageFromHR = Kernel32Util.formatMessage(hr);
/* 132 */           throw new COMException("getUnknownId: " + formatMessageFromHR);
/*     */         } 
/* 134 */       } catch (Exception e) {
/* 135 */         throw new COMException("Error occured when trying get Unknown Id ", e);
/*     */       } 
/*     */     }
/* 138 */     return this.unknownId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 143 */   protected void finalize() throws Throwable { dispose(1); }
/*     */ 
/*     */   
/*     */   public void dispose(int r) {
/* 147 */     if (!((Dispatch)this.rawDispatch).getPointer().equals(Pointer.NULL)) {
/*     */ 
/*     */       
/* 150 */       for (int i = 0; i < r; i++) {
/*     */         
/* 152 */         int n = this.rawDispatch.Release();
/* 153 */         int n2 = n;
/*     */       } 
/* 155 */       this.factory.unregister(this, r);
/* 156 */       ((Dispatch)this.rawDispatch).setPointer(Pointer.NULL);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public IDispatch getRawDispatch() { return this.rawDispatch; }
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
/*     */   public boolean equals(Object arg) {
/* 180 */     if (null == arg)
/* 181 */       return false; 
/* 182 */     if (arg instanceof ProxyObject) {
/* 183 */       ProxyObject other = (ProxyObject)arg;
/* 184 */       return (getUnknownId() == other.getUnknownId());
/* 185 */     }  if (Proxy.isProxyClass(arg.getClass())) {
/* 186 */       InvocationHandler handler = Proxy.getInvocationHandler(arg);
/* 187 */       if (handler instanceof ProxyObject) {
/*     */         try {
/* 189 */           ProxyObject other = (ProxyObject)handler;
/* 190 */           return (getUnknownId() == other.getUnknownId());
/* 191 */         } catch (Exception e) {
/*     */ 
/*     */           
/* 194 */           return false;
/*     */         } 
/*     */       }
/* 197 */       return false;
/*     */     } 
/*     */     
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public int hashCode() { return Long.valueOf(getUnknownId()).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public String toString() { return this.theInterface.getName() + "{unk=" + hashCode() + "}"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { return invokeSynchronised(proxy, method, args); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invokeSynchronised(Object proxy, Method method, Object[] args) throws Throwable {
/* 230 */     if (method.equals(Object.class.getMethod("toString", new Class[0])))
/* 231 */       return toString(); 
/* 232 */     if (method.equals(Object.class.getMethod("equals", new Class[] { Object.class })))
/* 233 */       return Boolean.valueOf(equals(args[0])); 
/* 234 */     if (method.equals(Object.class.getMethod("hashCode", new Class[0])))
/* 235 */       return Integer.valueOf(hashCode()); 
/* 236 */     if (method.equals(IRawDispatchHandle.class.getMethod("getRawDispatch", new Class[0])))
/* 237 */       return getRawDispatch(); 
/* 238 */     if (method.equals(IUnknown.class.getMethod("queryInterface", new Class[] { Class.class })))
/* 239 */       return queryInterface((Class)args[0]); 
/* 240 */     if (method.equals(IConnectionPoint.class.getMethod("advise", new Class[] { Class.class, IComEventCallbackListener.class })))
/*     */     {
/* 242 */       return advise((Class)args[0], (IComEventCallbackListener)args[1]); } 
/* 243 */     if (method.equals(IConnectionPoint.class.getMethod("unadvise", new Class[] { Class.class, IComEventCallbackCookie.class }))) {
/*     */       
/* 245 */       unadvise((Class)args[0], (IComEventCallbackCookie)args[1]);
/* 246 */       return null;
/*     */     } 
/*     */     
/* 249 */     Class<?> returnType = method.getReturnType();
/* 250 */     boolean isVoid = void.class.equals(returnType);
/*     */     
/* 252 */     ComProperty prop = (ComProperty)method.getAnnotation(ComProperty.class);
/* 253 */     if (null != prop) {
/* 254 */       if (isVoid) {
/* 255 */         String propName = getMutatorName(method, prop);
/* 256 */         setProperty(propName, args[0]);
/* 257 */         return null;
/*     */       } 
/* 259 */       String propName = getAccessorName(method, prop);
/* 260 */       return getProperty(returnType, propName, args);
/*     */     } 
/*     */ 
/*     */     
/* 264 */     ComMethod meth = (ComMethod)method.getAnnotation(ComMethod.class);
/* 265 */     if (null != meth) {
/* 266 */       String methName = getMethodName(method, meth);
/* 267 */       return invokeMethod(returnType, methName, args);
/*     */     } 
/*     */ 
/*     */     
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ConnectionPoint fetchRawConnectionPoint(Guid.IID iid) throws InterruptedException, ExecutionException, TimeoutException {
/* 277 */     IConnectionPointContainer cpc = (IConnectionPointContainer)queryInterface(IConnectionPointContainer.class);
/* 278 */     Dispatch rawCpcDispatch = (Dispatch)cpc.getRawDispatch();
/* 279 */     final ConnectionPointContainer rawCpc = new ConnectionPointContainer(rawCpcDispatch.getPointer());
/*     */ 
/*     */     
/* 282 */     final Guid.REFIID adviseRiid = new Guid.REFIID(iid.getPointer());
/* 283 */     final PointerByReference ppCp = new PointerByReference();
/* 284 */     WinNT.HRESULT hr = (WinNT.HRESULT)this.factory.getComThread().execute(new Callable<WinNT.HRESULT>()
/*     */         {
/*     */           public WinNT.HRESULT call() throws Exception {
/* 287 */             return rawCpc.FindConnectionPoint(adviseRiid, ppCp);
/*     */           }
/*     */         });
/* 290 */     COMUtils.checkRC(hr);
/* 291 */     return new ConnectionPoint(ppCp.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IComEventCallbackCookie advise(Class<?> comEventCallbackInterface, IComEventCallbackListener comEventCallbackListener) {
/*     */     try {
/* 298 */       ComInterface comInterfaceAnnotation = (ComInterface)comEventCallbackInterface.getAnnotation(ComInterface.class);
/* 299 */       if (null == comInterfaceAnnotation) {
/* 300 */         throw new COMException("advise: Interface must define a value for either iid via the ComInterface annotation");
/*     */       }
/*     */       
/* 303 */       Guid.IID iid = getIID(comInterfaceAnnotation);
/*     */       
/* 305 */       final ConnectionPoint rawCp = fetchRawConnectionPoint(iid);
/*     */ 
/*     */       
/* 308 */       final IDispatchCallback rawListener = new CallbackProxy(this.factory, comEventCallbackInterface, comEventCallbackListener);
/*     */ 
/*     */ 
/*     */       
/* 312 */       comEventCallbackListener.setDispatchCallbackListener(rawListener);
/*     */ 
/*     */       
/* 315 */       final WinDef.DWORDByReference pdwCookie = new WinDef.DWORDByReference();
/* 316 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.factory.getComThread().execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 319 */               return rawCp.Advise(rawListener, pdwCookie);
/*     */             }
/*     */           });
/* 322 */       int n = rawCp.Release();
/*     */       
/* 324 */       COMUtils.checkRC(hr);
/*     */ 
/*     */       
/* 327 */       return new ComEventCallbackCookie(pdwCookie.getValue());
/*     */     }
/* 329 */     catch (Exception e) {
/* 330 */       throw new COMException("Error occured in advise when trying to connect the listener " + comEventCallbackListener, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unadvise(Class<?> comEventCallbackInterface, final IComEventCallbackCookie cookie) {
/*     */     try {
/* 337 */       ComInterface comInterfaceAnnotation = (ComInterface)comEventCallbackInterface.getAnnotation(ComInterface.class);
/* 338 */       if (null == comInterfaceAnnotation) {
/* 339 */         throw new COMException("unadvise: Interface must define a value for iid via the ComInterface annotation");
/*     */       }
/*     */       
/* 342 */       Guid.IID iid = getIID(comInterfaceAnnotation);
/*     */       
/* 344 */       final ConnectionPoint rawCp = fetchRawConnectionPoint(iid);
/*     */       
/* 346 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.factory.getComThread().execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 349 */               return rawCp.Unadvise(((ComEventCallbackCookie)cookie).getValue());
/*     */             }
/*     */           });
/*     */       
/* 353 */       rawCp.Release();
/* 354 */       COMUtils.checkRC(hr);
/*     */     }
/* 356 */     catch (Exception e) {
/* 357 */       throw new COMException("Error occured in unadvise when trying to disconnect the listener from " + this, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void setProperty(String name, T value) {
/* 364 */     Variant.VARIANT v = Convert.toVariant(value);
/* 365 */     WinNT.HRESULT hr = oleMethod(4, null, getRawDispatch(), name, v);
/* 366 */     COMUtils.checkRC(hr);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getProperty(Class<T> returnType, String name, Object... args) {
/*     */     Variant.VARIANT[] arrayOfVARIANT;
/* 372 */     if (null == args) {
/* 373 */       arrayOfVARIANT = new Variant.VARIANT[0];
/*     */     } else {
/* 375 */       arrayOfVARIANT = new Variant.VARIANT[args.length];
/*     */     } 
/* 377 */     for (int i = 0; i < arrayOfVARIANT.length; i++) {
/* 378 */       arrayOfVARIANT[i] = Convert.toVariant(args[i]);
/*     */     }
/* 380 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 381 */     WinNT.HRESULT hr = oleMethod(2, result, getRawDispatch(), name, arrayOfVARIANT);
/* 382 */     COMUtils.checkRC(hr);
/* 383 */     Object jobj = Convert.toJavaObject(result);
/* 384 */     if (IComEnum.class.isAssignableFrom(returnType)) {
/* 385 */       return (T)Convert.toComEnum(returnType, jobj);
/*     */     }
/* 387 */     if (jobj instanceof IDispatch) {
/* 388 */       IDispatch d = (IDispatch)jobj;
/* 389 */       T t = (T)this.factory.createProxy(returnType, d);
/*     */ 
/*     */       
/* 392 */       int n = d.Release();
/* 393 */       return t;
/*     */     } 
/* 395 */     return (T)jobj;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T invokeMethod(Class<T> returnType, String name, Object... args) {
/*     */     Variant.VARIANT[] arrayOfVARIANT;
/* 401 */     if (null == args) {
/* 402 */       arrayOfVARIANT = new Variant.VARIANT[0];
/*     */     } else {
/* 404 */       arrayOfVARIANT = new Variant.VARIANT[args.length];
/*     */     } 
/* 406 */     for (int i = 0; i < arrayOfVARIANT.length; i++) {
/* 407 */       arrayOfVARIANT[i] = Convert.toVariant(args[i]);
/*     */     }
/* 409 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 410 */     WinNT.HRESULT hr = oleMethod(1, result, getRawDispatch(), name, arrayOfVARIANT);
/* 411 */     COMUtils.checkRC(hr);
/*     */     
/* 413 */     Object jobj = Convert.toJavaObject(result);
/* 414 */     if (IComEnum.class.isAssignableFrom(returnType)) {
/* 415 */       return (T)Convert.toComEnum(returnType, jobj);
/*     */     }
/* 417 */     if (jobj instanceof IDispatch) {
/* 418 */       IDispatch d = (IDispatch)jobj;
/* 419 */       T t = (T)this.factory.createProxy(returnType, d);
/*     */ 
/*     */       
/* 422 */       int n = d.Release();
/* 423 */       return t;
/*     */     } 
/* 425 */     return (T)jobj;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T queryInterface(Class<T> comInterface) throws COMException {
/*     */     try {
/* 431 */       ComInterface comInterfaceAnnotation = (ComInterface)comInterface.getAnnotation(ComInterface.class);
/* 432 */       if (null == comInterfaceAnnotation) {
/* 433 */         throw new COMException("queryInterface: Interface must define a value for iid via the ComInterface annotation");
/*     */       }
/*     */       
/* 436 */       final Guid.IID iid = getIID(comInterfaceAnnotation);
/* 437 */       final PointerByReference ppvObject = new PointerByReference();
/*     */       
/* 439 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 442 */               return ProxyObject.this.getRawDispatch().QueryInterface(new Guid.GUID.ByValue(iid), ppvObject);
/*     */             }
/*     */           });
/*     */       
/* 446 */       if (WinNT.S_OK.equals(hr)) {
/* 447 */         Dispatch dispatch = new Dispatch(ppvObject.getValue());
/* 448 */         T t = (T)this.factory.createProxy(comInterface, dispatch);
/*     */ 
/*     */ 
/*     */         
/* 452 */         int n = dispatch.Release();
/* 453 */         return t;
/*     */       } 
/* 455 */       String formatMessageFromHR = Kernel32Util.formatMessage(hr);
/* 456 */       throw new COMException("queryInterface: " + formatMessageFromHR);
/*     */     }
/* 458 */     catch (Exception e) {
/* 459 */       throw new COMException("Error occured when trying to query for interface " + comInterface.getName(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   Guid.IID getIID(ComInterface annotation) {
/* 464 */     String iidStr = annotation.iid();
/* 465 */     if (null != iidStr && !iidStr.isEmpty()) {
/* 466 */       return new Guid.IID(iidStr);
/*     */     }
/* 468 */     throw new COMException("ComInterface must define a value for iid");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getAccessorName(Method method, ComProperty prop) {
/* 475 */     if (prop.name().isEmpty()) {
/* 476 */       String methName = method.getName();
/* 477 */       if (methName.startsWith("get")) {
/* 478 */         return methName.replaceFirst("get", "");
/*     */       }
/* 480 */       throw new RuntimeException("Property Accessor name must start with 'get', or set the anotation 'name' value");
/*     */     } 
/*     */ 
/*     */     
/* 484 */     return prop.name();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMutatorName(Method method, ComProperty prop) {
/* 489 */     if (prop.name().isEmpty()) {
/* 490 */       String methName = method.getName();
/* 491 */       if (methName.startsWith("set")) {
/* 492 */         return methName.replaceFirst("set", "");
/*     */       }
/* 494 */       throw new RuntimeException("Property Mutator name must start with 'set', or set the anotation 'name' value");
/*     */     } 
/*     */ 
/*     */     
/* 498 */     return prop.name();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMethodName(Method method, ComMethod meth) {
/* 503 */     if (meth.name().isEmpty()) {
/* 504 */       return method.getName();
/*     */     }
/*     */     
/* 507 */     return meth.name();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 512 */   public static final WinDef.LCID LOCALE_USER_DEFAULT = Kernel32.INSTANCE.GetUserDefaultLCID();
/*     */ 
/*     */   
/* 515 */   public static final WinDef.LCID LOCALE_SYSTEM_DEFAULT = Kernel32.INSTANCE.GetSystemDefaultLCID();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, String name, Variant.VARIANT pArg) throws COMException { return oleMethod(nType, pvResult, pDisp, name, new Variant.VARIANT[] { pArg }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, OaIdl.DISPID dispId, Variant.VARIANT pArg) throws COMException { return oleMethod(nType, pvResult, pDisp, dispId, new Variant.VARIANT[] { pArg }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, String name) throws COMException { return oleMethod(nType, pvResult, pDisp, name, (VARIANT[])null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, IDispatch pDisp, OaIdl.DISPID dispId) throws COMException { return oleMethod(nType, pvResult, pDisp, dispId, (VARIANT[])null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WinNT.HRESULT oleMethod(int nType, Variant.VARIANT.ByReference pvResult, final IDispatch pDisp, String name, VARIANT[] pArgs) throws COMException {
/*     */     try {
/* 556 */       if (pDisp == null) {
/* 557 */         throw new COMException("pDisp (IDispatch) parameter is null!");
/*     */       }
/*     */       
/* 560 */       final WString[] ptName = { new WString(name) };
/* 561 */       final OaIdl.DISPIDByReference pdispID = new OaIdl.DISPIDByReference();
/*     */ 
/*     */       
/* 564 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 567 */               return pDisp.GetIDsOfNames(new Guid.GUID.ByValue(Guid.IID_NULL), ptName, 1, ProxyObject.LOCALE_USER_DEFAULT, pdispID);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 572 */       COMUtils.checkRC(hr);
/*     */       
/* 574 */       return oleMethod(nType, pvResult, pDisp, pdispID.getValue(), pArgs);
/* 575 */     } catch (InterruptedException e) {
/* 576 */       throw new COMException(e);
/* 577 */     } catch (ExecutionException e) {
/* 578 */       throw new COMException(e);
/* 579 */     } catch (TimeoutException e) {
/* 580 */       throw new COMException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WinNT.HRESULT oleMethod(final int nType, final Variant.VARIANT.ByReference pvResult, final IDispatch pDisp, final OaIdl.DISPID dispId, VARIANT[] pArgs) throws COMException {
/* 590 */     if (pDisp == null) {
/* 591 */       throw new COMException("pDisp (IDispatch) parameter is null!");
/*     */     }
/*     */     
/* 594 */     int _argsLen = 0;
/* 595 */     Variant.VARIANT[] arrayOfVARIANT = null;
/* 596 */     final OleAuto.DISPPARAMS.ByReference dp = new OleAuto.DISPPARAMS.ByReference();
/* 597 */     final OaIdl.EXCEPINFO.ByReference pExcepInfo = new OaIdl.EXCEPINFO.ByReference();
/* 598 */     final IntByReference puArgErr = new IntByReference();
/*     */ 
/*     */     
/* 601 */     if (pArgs != null && pArgs.length > 0) {
/* 602 */       _argsLen = pArgs.length;
/* 603 */       arrayOfVARIANT = new Variant.VARIANT[_argsLen];
/*     */       
/* 605 */       int revCount = _argsLen;
/* 606 */       for (int i = 0; i < _argsLen; i++) {
/* 607 */         arrayOfVARIANT[i] = pArgs[--revCount];
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 612 */     if (nType == 4) {
/* 613 */       dp.cNamedArgs = new WinDef.UINT(_argsLen);
/* 614 */       dp.rgdispidNamedArgs = new OaIdl.DISPIDByReference(OaIdl.DISPID_PROPERTYPUT);
/*     */     } 
/*     */ 
/*     */     
/* 618 */     if (_argsLen > 0) {
/* 619 */       dp.cArgs = new WinDef.UINT(arrayOfVARIANT.length);
/*     */       
/* 621 */       dp.rgvarg = new Variant.VariantArg.ByReference(arrayOfVARIANT);
/*     */ 
/*     */       
/* 624 */       dp.write();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 630 */       WinNT.HRESULT hr = (WinNT.HRESULT)this.comThread.execute(new Callable<WinNT.HRESULT>()
/*     */           {
/*     */             public WinNT.HRESULT call() throws Exception {
/* 633 */               return pDisp.Invoke(dispId, new Guid.GUID.ByValue(Guid.IID_NULL), ProxyObject.LOCALE_SYSTEM_DEFAULT, new WinDef.WORD(nType), dp, pvResult, pExcepInfo, puArgErr);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 638 */       COMUtils.checkRC(hr, pExcepInfo, puArgErr);
/* 639 */       return hr;
/* 640 */     } catch (InterruptedException e) {
/* 641 */       throw new COMException(e);
/* 642 */     } catch (ExecutionException e) {
/* 643 */       throw new COMException(e);
/* 644 */     } catch (TimeoutException e) {
/* 645 */       throw new COMException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\ProxyObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */