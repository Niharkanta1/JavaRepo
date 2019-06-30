/*     */ package com.sun.jna.platform.win32.COM.util;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.COM.COMException;
/*     */ import com.sun.jna.platform.win32.COM.Dispatch;
/*     */ import com.sun.jna.platform.win32.COM.DispatchListener;
/*     */ import com.sun.jna.platform.win32.COM.IDispatch;
/*     */ import com.sun.jna.platform.win32.COM.IDispatchCallback;
/*     */ import com.sun.jna.platform.win32.COM.IUnknown;
/*     */ import com.sun.jna.platform.win32.COM.Unknown;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComEventCallback;
/*     */ import com.sun.jna.platform.win32.COM.util.annotation.ComInterface;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.OleAuto;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinError;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadFactory;
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
/*     */ public class CallbackProxy
/*     */   implements IDispatchCallback
/*     */ {
/*     */   Factory factory;
/*     */   Class<?> comEventCallbackInterface;
/*     */   IComEventCallbackListener comEventCallbackListener;
/*     */   Guid.GUID.ByValue listenedToRiid;
/*     */   public DispatchListener dispatchListener;
/*     */   Map<OaIdl.DISPID, Method> dsipIdMap;
/*     */   ExecutorService executorService;
/*     */   
/*     */   public CallbackProxy(Factory factory, Class<?> comEventCallbackInterface, IComEventCallbackListener comEventCallbackListener) {
/*  63 */     this.factory = factory;
/*  64 */     this.comEventCallbackInterface = comEventCallbackInterface;
/*  65 */     this.comEventCallbackListener = comEventCallbackListener;
/*  66 */     this.listenedToRiid = createRIID(comEventCallbackInterface);
/*  67 */     this.dsipIdMap = createDispIdMap(comEventCallbackInterface);
/*  68 */     this.dispatchListener = new DispatchListener(this);
/*  69 */     this.executorService = Executors.newSingleThreadExecutor(new ThreadFactory()
/*     */         {
/*     */           public Thread newThread(Runnable r) {
/*  72 */             Thread thread = new Thread(r, "COM Event Callback executor");
/*  73 */             thread.setDaemon(true);
/*  74 */             thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
/*     */                 {
/*     */                   public void uncaughtException(Thread t, Throwable e) {
/*  77 */                     this.this$1.this$0.factory.comThread.uncaughtExceptionHandler.uncaughtException(t, e);
/*     */                   }
/*     */                 });
/*  80 */             return thread;
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
/*     */   Guid.GUID.ByValue createRIID(Class<?> comEventCallbackInterface) {
/*  94 */     ComInterface comInterfaceAnnotation = (ComInterface)comEventCallbackInterface.getAnnotation(ComInterface.class);
/*  95 */     if (null == comInterfaceAnnotation) {
/*  96 */       throw new COMException("advise: Interface must define a value for either iid via the ComInterface annotation");
/*     */     }
/*     */     
/*  99 */     String iidStr = comInterfaceAnnotation.iid();
/* 100 */     if (null == iidStr || iidStr.isEmpty()) {
/* 101 */       throw new COMException("ComInterface must define a value for iid");
/*     */     }
/* 103 */     return new Guid.GUID.ByValue((new Guid.IID(iidStr)).getPointer());
/*     */   }
/*     */   
/*     */   Map<OaIdl.DISPID, Method> createDispIdMap(Class<?> comEventCallbackInterface) {
/* 107 */     Map<OaIdl.DISPID, Method> map = new HashMap<OaIdl.DISPID, Method>();
/*     */     
/* 109 */     for (Method meth : comEventCallbackInterface.getMethods()) {
/* 110 */       ComEventCallback annotation = (ComEventCallback)meth.getAnnotation(ComEventCallback.class);
/* 111 */       if (null != annotation) {
/* 112 */         int dispId = annotation.dispid();
/* 113 */         if (-1 == dispId) {
/* 114 */           dispId = fetchDispIdFromName(annotation);
/*     */         }
/* 116 */         map.put(new OaIdl.DISPID(dispId), meth);
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 125 */   int fetchDispIdFromName(ComEventCallback annotation) { return -1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void invokeOnThread(final OaIdl.DISPID dispIdMember, Guid.GUID.ByValue riid, WinDef.LCID lcid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams) {
/* 134 */     List<Object> rjargs = new ArrayList<Object>();
/* 135 */     if (pDispParams.cArgs.intValue() > 0) {
/* 136 */       Variant.VariantArg.ByReference byReference = pDispParams.rgvarg;
/* 137 */       byReference.setArraySize(pDispParams.cArgs.intValue());
/* 138 */       for (Variant.VARIANT varg : byReference.variantArg) {
/* 139 */         Object jarg = Convert.toJavaObject(varg);
/* 140 */         if (jarg instanceof IDispatch) {
/* 141 */           IDispatch dispatch = (IDispatch)jarg;
/*     */           
/* 143 */           PointerByReference ppvObject = new PointerByReference();
/* 144 */           Guid.IID iid = IUnknown.IID_IUNKNOWN;
/* 145 */           dispatch.QueryInterface(new Guid.GUID.ByValue(iid), ppvObject);
/* 146 */           Unknown rawUnk = new Unknown(ppvObject.getValue());
/* 147 */           long unknownId = Pointer.nativeValue(rawUnk.getPointer());
/* 148 */           int n = rawUnk.Release();
/*     */           
/* 150 */           IUnknown unk = (IUnknown)this.factory.createProxy(IUnknown.class, unknownId, dispatch);
/* 151 */           rjargs.add(unk);
/*     */         } else {
/* 153 */           rjargs.add(jarg);
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     final List<Object> jargs = new ArrayList<Object>(rjargs);
/* 158 */     Runnable invokation = new Runnable()
/*     */       {
/*     */         public void run() {
/*     */           try {
/* 162 */             if (CallbackProxy.this.dsipIdMap.containsKey(dispIdMember)) {
/* 163 */               Method eventMethod = (Method)CallbackProxy.this.dsipIdMap.get(dispIdMember);
/* 164 */               if (eventMethod.getParameterTypes().length != jargs.size()) {
/* 165 */                 CallbackProxy.this.comEventCallbackListener.errorReceivingCallbackEvent("Trying to invoke method " + eventMethod + " with " + jargs
/* 166 */                     .size() + " arguments", null);
/*     */               } else {
/*     */ 
/*     */                 
/*     */                 try {
/* 171 */                   List<Object> margs = new ArrayList<Object>();
/* 172 */                   Class[] params = eventMethod.getParameterTypes();
/* 173 */                   for (int i = 0; i < eventMethod.getParameterTypes().length; i++) {
/* 174 */                     Class<?> paramType = params[i];
/* 175 */                     Object jobj = jargs.get(i);
/* 176 */                     if (jobj != null && paramType.getAnnotation(ComInterface.class) != null) {
/* 177 */                       if (jobj instanceof IUnknown) {
/* 178 */                         IUnknown unk = (IUnknown)jobj;
/* 179 */                         Object mobj = unk.queryInterface(paramType);
/* 180 */                         margs.add(mobj);
/*     */                       } else {
/* 182 */                         throw new RuntimeException("Cannot convert argument " + jobj.getClass() + " to ComInterface " + paramType);
/*     */                       } 
/*     */                     } else {
/*     */                       
/* 186 */                       margs.add(jobj);
/*     */                     } 
/*     */                   } 
/* 189 */                   eventMethod.invoke(CallbackProxy.this.comEventCallbackListener, margs.toArray());
/* 190 */                 } catch (Exception e) {
/* 191 */                   CallbackProxy.this.comEventCallbackListener.errorReceivingCallbackEvent("Exception invoking method " + eventMethod, e);
/*     */                 } 
/*     */               } 
/*     */             } else {
/*     */               
/* 196 */               CallbackProxy.this.comEventCallbackListener.errorReceivingCallbackEvent("No method found with dispId = " + dispIdMember, null);
/*     */             }
/*     */           
/* 199 */           } catch (Exception e) {
/* 200 */             CallbackProxy.this.comEventCallbackListener.errorReceivingCallbackEvent("Exception receiving callback event ", e);
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 205 */     this.executorService.execute(invokation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 210 */   public Pointer getPointer() { return this.dispatchListener.getPointer(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference pctinfo) { return new WinNT.HRESULT(-2147467263); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public WinNT.HRESULT GetTypeInfo(WinDef.UINT iTInfo, WinDef.LCID lcid, PointerByReference ppTInfo) { return new WinNT.HRESULT(-2147467263); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public WinNT.HRESULT GetIDsOfNames(Guid.GUID.ByValue riid, WString[] rgszNames, int cNames, WinDef.LCID lcid, OaIdl.DISPIDByReference rgDispId) { return new WinNT.HRESULT(-2147467263); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT Invoke(OaIdl.DISPID dispIdMember, Guid.GUID.ByValue riid, WinDef.LCID lcid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams, Variant.VARIANT.ByReference pVarResult, OaIdl.EXCEPINFO.ByReference pExcepInfo, IntByReference puArgErr) {
/* 235 */     invokeOnThread(dispIdMember, riid, lcid, wFlags, pDispParams);
/*     */     
/* 237 */     return WinError.S_OK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT QueryInterface(Guid.GUID.ByValue refid, PointerByReference ppvObject) {
/* 243 */     if (null == ppvObject) {
/* 244 */       return new WinNT.HRESULT(-2147467261);
/*     */     }
/*     */     
/* 247 */     if (refid.equals(this.listenedToRiid)) {
/* 248 */       ppvObject.setValue(getPointer());
/* 249 */       return WinError.S_OK;
/*     */     } 
/*     */     
/* 252 */     if ((new Guid.IID(refid.getPointer())).equals(Unknown.IID_IUNKNOWN)) {
/* 253 */       ppvObject.setValue(getPointer());
/* 254 */       return WinError.S_OK;
/*     */     } 
/*     */     
/* 257 */     if ((new Guid.IID(refid.getPointer())).equals(Dispatch.IID_IDISPATCH)) {
/* 258 */       ppvObject.setValue(getPointer());
/* 259 */       return WinError.S_OK;
/*     */     } 
/*     */     
/* 262 */     return new WinNT.HRESULT(-2147467262);
/*     */   }
/*     */ 
/*     */   
/* 266 */   public int AddRef() { return 0; }
/*     */ 
/*     */ 
/*     */   
/* 270 */   public int Release() { return 0; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\CallbackProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */