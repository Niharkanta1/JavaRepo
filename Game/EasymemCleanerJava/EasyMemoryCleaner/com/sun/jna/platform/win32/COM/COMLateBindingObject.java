/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.Variant.VARIANT;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ public class COMLateBindingObject
/*     */   extends COMBindingBaseObject
/*     */ {
/*  39 */   public COMLateBindingObject(IDispatch iDispatch) { super(iDispatch); }
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
/*  51 */   public COMLateBindingObject(Guid.CLSID clsid, boolean useActiveInstance) { super(clsid, useActiveInstance); }
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
/*  66 */   public COMLateBindingObject(String progId, boolean useActiveInstance) throws COMException { super(progId, useActiveInstance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IDispatch getAutomationProperty(String propertyName) {
/*  77 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/*  78 */     oleMethod(2, result, 
/*  79 */         getIDispatch(), propertyName);
/*     */     
/*  81 */     return (IDispatch)result.getValue();
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
/*     */   
/*     */   protected IDispatch getAutomationProperty(String propertyName, COMLateBindingObject comObject) {
/*  95 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/*  96 */     oleMethod(2, result, comObject
/*  97 */         .getIDispatch(), propertyName);
/*     */     
/*  99 */     return (IDispatch)result.getValue();
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected IDispatch getAutomationProperty(String propertyName, COMLateBindingObject comObject, Variant.VARIANT value) {
/* 115 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 116 */     oleMethod(2, result, comObject
/* 117 */         .getIDispatch(), propertyName, value);
/*     */     
/* 119 */     return (IDispatch)result.getValue();
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
/*     */   
/*     */   protected IDispatch getAutomationProperty(String propertyName, IDispatch iDispatch) {
/* 133 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 134 */     oleMethod(2, result, 
/* 135 */         getIDispatch(), propertyName);
/*     */     
/* 137 */     return (IDispatch)result.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean getBooleanProperty(String propertyName) {
/* 148 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 149 */     oleMethod(2, result, 
/* 150 */         getIDispatch(), propertyName);
/*     */     
/* 152 */     return (((OaIdl.VARIANT_BOOL)result.getValue()).intValue() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Date getDateProperty(String propertyName) {
/* 163 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 164 */     oleMethod(2, result, 
/* 165 */         getIDispatch(), propertyName);
/*     */     
/* 167 */     return result.dateValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getIntProperty(String propertyName) {
/* 178 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 179 */     oleMethod(2, result, 
/* 180 */         getIDispatch(), propertyName);
/*     */     
/* 182 */     return ((WinDef.LONG)result.getValue()).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short getShortProperty(String propertyName) {
/* 193 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 194 */     oleMethod(2, result, 
/* 195 */         getIDispatch(), propertyName);
/*     */     
/* 197 */     return ((WinDef.SHORT)result.getValue()).shortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getStringProperty(String propertyName) {
/* 208 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 209 */     oleMethod(2, result, 
/* 210 */         getIDispatch(), propertyName);
/*     */     
/* 212 */     return result.getValue().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Variant.VARIANT invoke(String methodName) {
/* 223 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 224 */     oleMethod(1, result, getIDispatch(), methodName);
/*     */ 
/*     */     
/* 227 */     return result;
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
/*     */   protected Variant.VARIANT invoke(String methodName, Variant.VARIANT arg) {
/* 240 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 241 */     oleMethod(1, result, getIDispatch(), methodName, arg);
/*     */ 
/*     */     
/* 244 */     return result;
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
/*     */   protected Variant.VARIANT invoke(String methodName, VARIANT[] args) {
/* 257 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 258 */     oleMethod(1, result, getIDispatch(), methodName, args);
/*     */ 
/*     */     
/* 261 */     return result;
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
/*     */ 
/*     */ 
/*     */   
/* 276 */   protected Variant.VARIANT invoke(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2) { return invoke(methodName, new Variant.VARIANT[] { arg1, arg2 }); }
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
/* 294 */   protected Variant.VARIANT invoke(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2, Variant.VARIANT arg3) { return invoke(methodName, new Variant.VARIANT[] { arg1, arg2, arg3 }); }
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
/* 314 */   protected Variant.VARIANT invoke(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2, Variant.VARIANT arg3, Variant.VARIANT arg4) { return invoke(methodName, new Variant.VARIANT[] { arg1, arg2, arg3, arg4 }); }
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
/* 326 */   protected void invokeNoReply(String methodName, IDispatch dispatch) { oleMethod(1, null, dispatch, methodName); }
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
/* 339 */   protected void invokeNoReply(String methodName, COMLateBindingObject comObject) { oleMethod(1, null, comObject.getIDispatch(), methodName); }
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
/* 355 */   protected void invokeNoReply(String methodName, IDispatch dispatch, Variant.VARIANT arg) { oleMethod(1, null, dispatch, methodName, arg); }
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
/* 372 */   protected void invokeNoReply(String methodName, IDispatch dispatch, Variant.VARIANT arg1, Variant.VARIANT arg2) { oleMethod(1, null, dispatch, methodName, new Variant.VARIANT[] { arg1, arg2 }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   protected void invokeNoReply(String methodName, COMLateBindingObject comObject, Variant.VARIANT arg1, Variant.VARIANT arg2) { oleMethod(1, null, comObject.getIDispatch(), methodName, new Variant.VARIANT[] { arg1, arg2 }); }
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
/* 394 */   protected void invokeNoReply(String methodName, COMLateBindingObject comObject, Variant.VARIANT arg) { oleMethod(1, null, comObject.getIDispatch(), methodName, arg); }
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
/* 410 */   protected void invokeNoReply(String methodName, IDispatch dispatch, VARIANT[] args) { oleMethod(1, null, dispatch, methodName, args); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void invokeNoReply(String methodName) {
/* 421 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 422 */     oleMethod(1, result, getIDispatch(), methodName);
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
/*     */   protected void invokeNoReply(String methodName, Variant.VARIANT arg) {
/* 435 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 436 */     oleMethod(1, result, getIDispatch(), methodName, arg);
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
/*     */   protected void invokeNoReply(String methodName, VARIANT[] args) {
/* 449 */     Variant.VARIANT.ByReference result = new Variant.VARIANT.ByReference();
/* 450 */     oleMethod(1, result, getIDispatch(), methodName, args);
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
/*     */ 
/*     */ 
/*     */   
/* 465 */   protected void invokeNoReply(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2) { invokeNoReply(methodName, new Variant.VARIANT[] { arg1, arg2 }); }
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
/* 482 */   protected void invokeNoReply(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2, Variant.VARIANT arg3) { invokeNoReply(methodName, new Variant.VARIANT[] { arg1, arg2, arg3 }); }
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
/* 501 */   protected void invokeNoReply(String methodName, Variant.VARIANT arg1, Variant.VARIANT arg2, Variant.VARIANT arg3, Variant.VARIANT arg4) { invokeNoReply(methodName, new Variant.VARIANT[] { arg1, arg2, arg3, arg4 }); }
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
/* 513 */   protected void setProperty(String propertyName, boolean value) throws COMException { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 526 */   protected void setProperty(String propertyName, Date value) { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 539 */   protected void setProperty(String propertyName, IDispatch value) { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 552 */   protected void setProperty(String propertyName, int value) { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 565 */   protected void setProperty(String propertyName, short value) { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 578 */   protected void setProperty(String propertyName, String value) { oleMethod(4, null, getIDispatch(), propertyName, new Variant.VARIANT(value)); }
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
/* 594 */   protected void setProperty(String propertyName, IDispatch iDispatch, Variant.VARIANT value) { oleMethod(4, null, iDispatch, propertyName, value); }
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
/*     */   protected void setProperty(String propertyName, COMLateBindingObject comObject, Variant.VARIANT value) {
/* 610 */     oleMethod(4, null, comObject
/* 611 */         .getIDispatch(), propertyName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public Variant.VARIANT toVariant() { return new Variant.VARIANT(getIDispatch()); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMLateBindingObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */